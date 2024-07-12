package com.sahil.demo.views.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.sahil.demo.R
import com.sahil.demo.databinding.ActivityMainBinding
import com.sahil.demo.databinding.ProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Profile:Fragment(R.layout.profile) {

    private lateinit var binding: ProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=ProfileBinding.bind(view)

    }
}