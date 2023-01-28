package com.example.homebar

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HomeBarApplication: Application() {
}

@GlideModule
class AppGlideModule : AppGlideModule()