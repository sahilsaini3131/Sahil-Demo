package com.sahil.demo.views.tabLayout

import android.os.Bundle
import android.telecom.Call
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sahil.demo.R

import com.sahil.demo.databinding.WhatsAppBinding
import com.sahil.demo.views.tabLayout.Calls.Calls
import com.sahil.demo.views.tabLayout.Chat.Chats
import com.sahil.demo.views.tabLayout.Group.Group
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WhatsApp : Fragment(R.layout.whats_app) {
    private lateinit var binding: WhatsAppBinding
    val viewModel: WhatsAppVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = WhatsAppBinding.bind(view)
        val adapter = ViewPagerAdapter(childFragmentManager) // Use childFragmentManager here
        // add fragment to the list
        adapter.addFragment(Chats(), "Chats")
        adapter.addFragment(Calls(), "Calls")
        adapter.addFragment(Group(), "Groups")

        // Adding the Adapter to the ViewPager
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

    }
}