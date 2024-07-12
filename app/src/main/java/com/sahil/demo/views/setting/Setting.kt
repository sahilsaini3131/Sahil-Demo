package com.sahil.demo.views.setting

import android.Manifest.permission.CAMERA
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.sahil.demo.R
import com.sahil.demo.databinding.SettingBinding
import com.sahil.demo.views.utils.Data
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@AndroidEntryPoint
class Setting : Fragment(R.layout.setting) {
    lateinit var binding: SettingBinding

    private val viewModel: SettingVM by viewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SettingBinding.bind(view)
        binding.rvpic.adapter = viewModel.userAdapter
        binding.btn.setOnClickListener {
            requestPermissions()
        }
    }


    private fun requestPermissions() {
        Dexter.withContext(requireContext()).withPermissions(
                CAMERA
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                        showImageSourceDialog()
                    } else if (report.isAnyPermissionPermanentlyDenied) {
                        showSettingsDialog()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?, token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }
            }).check()
    }

    private fun showImageSourceDialog() {
        val options = arrayOf("Camera", "Gallery")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Select Image Source")
        builder.setItems(options) { _, position ->
            when (position) {
                0 -> openCamera()
                1 -> openGallery()
            }
        }
        builder.show()
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncher.launch(intent)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        resultLauncher.launch(intent)
    }

    private fun showSettingsDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Need Permissions")
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.")
        builder.setPositiveButton("GOTO SETTINGS") { dialog, which ->
            dialog.cancel()
            val intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", requireActivity().packageName, null)
            intent.data = uri
            startActivityForResult(intent, 101)
        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }
        builder.show()
    }

    var resultLauncher =

        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                try {
                    val selectedImageUri = result?.data?.data
                    if (selectedImageUri != null) {
                        val data = Data(image = selectedImageUri)
                        viewModel.userAdapter.addItems(listOf(data))
                    } else {
                        // Handle camera capture result
                        val imageBitmap = result.data?.extras?.get("data") as Bitmap?
                        if (imageBitmap != null) {
                            // Convert the bitmap to a URI if needed
                            val imageUri = getImageUriFromBitmap(requireContext(), imageBitmap)
                            val data = Data(image = imageUri)
                            viewModel.userAdapter.addItems(listOf(data))
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }


            }
        }

    private fun getImageUriFromBitmap(context: Context, bitmap: Bitmap): Uri? {
        var uri: Uri? = null
        try {
            val filesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val imageFile = File(filesDir, "image.png")

            val outputStream = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()

            uri = FileProvider.getUriForFile(
                context, context.packageName + ".provider", imageFile
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return uri
    }

}

//fun main() {
//    /**
//     * quick sort
//     */
//
//    fun quicksort(arr: MutableList<Int>, lowValue:Int, highValue:Int) {
//        if (arr.size <= 1) {
//            return
//        }
//
//        val pi = arr[arr.size / 2]
//        val less = arr.filter { it < pi }.toMutableList()
//        val equal = arr.filter { it == pi }.toMutableList()
//        val greater = arr.filter { it > pi }.toMutableList()
//
//
//        // high low,
//        //array linklist
//
//        quicksort(less)
//        quicksort(greater)
//
//        arr.clear()
//        arr.addAll(less)
//        arr.addAll(equal)
//        arr.addAll(greater)
//    }
//
//
//
//
//    val number = mutableListOf<Int>(2, 5, 3, 7, 9, 1)
//
//    print("old list" + number)
//    quicksort(number)
//
//    println("sorted list" + number)
//
//
//}