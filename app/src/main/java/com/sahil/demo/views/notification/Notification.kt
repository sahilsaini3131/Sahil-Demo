package com.sahil.demo.views.notification

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.sahil.demo.R
import com.sahil.demo.databinding.NotificationBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class Notification : Fragment(R.layout.notification) {
    lateinit var binding: NotificationBinding
    private val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    private val handler = Handler(Looper.getMainLooper())
    private val updateTimeDelay = 1000L // Update every 1 second
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = NotificationBinding.bind(view)

        // Initialize the time display
        updateCurrentTime()

        // Schedule a Runnable to update the seconds part of the time
        handler.postDelayed(updateTimeRunnable, updateTimeDelay)

        //click
        binding.btTabLayout.setOnClickListener {
            view.findNavController().navigate(R.id.whatsApp)
        }
    }


    private fun updateCurrentTime() {
        val calendar = Calendar.getInstance()
        val currentTime = dateFormat.format(calendar.time)
        binding.time.text = "Current Time: $currentTime"
    }

    private val updateTimeRunnable = object : Runnable {
        override fun run() {
            // Update the seconds part of the time
            val calendar = Calendar.getInstance()
            val seconds = SimpleDateFormat("ss", Locale.getDefault()).format(calendar.time)
            binding.time.text = "Current Time: ${dateFormat.format(calendar.time)}:$seconds"

            // Schedule the next update
            handler.postDelayed(this, updateTimeDelay)
        }
    }
}