package com.sahil.demo.views.setting

import androidx.lifecycle.ViewModel
import com.sahil.demo.views.adapter.ImageAdapter
import com.sahil.demo.views.adapter.SimpleAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingVM @Inject constructor():ViewModel() {
    val userAdapter by lazy { ImageAdapter() }

}