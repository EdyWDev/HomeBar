package com.example.homebar.view.viewModel

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.homebar.model.PresenceOfAlcoholCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {


}



