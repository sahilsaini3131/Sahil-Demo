package com.sahil.demo.views.adapter

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.provider.Settings
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.sahil.demo.views.setting.Setting
import java.io.File
import java.io.FileOutputStream


/**
 * Check Permissions
 * */
fun Context.checkPermissions(vararg permission: String, returnData: (Boolean) -> Unit) = try {
    Dexter.withContext(this).withPermissions(*permission)
        .withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                report?.let {
                    if (report.areAllPermissionsGranted()) {
                        returnData(true)
                    } else if (report.isAnyPermissionPermanentlyDenied) {
                        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                            data = Uri.fromParts("package", packageName, null)
                            startActivity(this)
                        }
                    }
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<com.karumi.dexter.listener.PermissionRequest>?, p1: PermissionToken?
            ) {
                p1?.continuePermissionRequest()
            }
        }).check()
} catch (e: Exception) {
    e.printStackTrace()
}

//private val startForResult =
//    registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            val selectedImageUri = result.data?.data
//            if (selectedImageUri != null) {
//                Log.e("TAG", "onViewCreated: setting image")
//
//                // Load and display the selected image in your ImageView
//                binding.image.setImageURI(selectedImageUri)
//            }
//        }
//    }
//
//
//// Request permission to access the gallery
//// Request permission to access the gallery
//private fun requestGalleryPermission() {
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//        Log.e("TAG", "requestGalleryPermission: version check success")
//
//        if (ContextCompat.checkSelfPermission(
//                requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            Log.e("TAG", "requestGalleryPermission: requesting permission")
//            requestPermissions(
//                arrayOf(
//                    Manifest.permission.READ_EXTERNAL_STORAGE,
//                    Manifest.permission.READ_MEDIA_IMAGES
//                ),
//                Setting.REQUEST_CODE_GALLERY_PERMISSION
//            )
//        } else {
//            // Permission already granted
//            openGallery()
//        }
//    } else {
//        // Permission is automatically granted on versions below M
//        openGallery()
//    }
//}
//
//
//// Open the gallery to select an image
//private fun openGallery() {
//    Log.e("TAG", "onViewCreated: opening gallery")
//
//    val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//    startForResult.launch(intent)
//}
//
//// Check if the app has permission to access the gallery
//private fun hasGalleryPermission(): Boolean {
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//        return Environment.isExternalStorageManager()
//    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//        return ContextCompat.checkSelfPermission(
//            requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE
//        ) == PackageManager.PERMISSION_GRANTED
//    }
//    // Permission is automatically granted on versions below M
//    return true
//}

//override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//    super.onActivityResult(requestCode, resultCode, data)
//    if (resultCode === Activity.RESULT_OK) {
//        // compare the resultCode with the
//        // constant
//        if (requestCode === 1) {
//            // Get the url of the image from data
//            val selectedImageUri: Uri = data?.data!!
//            binding.image.setImageURI(selectedImageUri)
//        }
//    }
//}

