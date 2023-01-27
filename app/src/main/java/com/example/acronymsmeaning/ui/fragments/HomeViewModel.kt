package com.example.acronymsmeaning.ui.fragments

import android.util.Log
import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acronymsmeaning.data.AppRepository
import com.example.acronymsmeaning.data.base.LiveDataResource
import com.example.acronymsmeaning.data.remote.models.AcronymResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {

    val typedText = MutableLiveData<String>()

    private val _resultData: MutableLiveData<LiveDataResource<List<AcronymResponseItem?>>> =
        MutableLiveData()
    val weatherData = _resultData

    fun getAcronymResults() {
        if (typedText.value.toString().isEmpty()) return
        viewModelScope.launch {
            _resultData.value = LiveDataResource.loading(null)
            val response1 = withContext(Dispatchers.IO) {
                appRepository.getAcronymDetails(typedText.value.toString())
            }
            if (response1.isSuccessful) {
                response1.body()?.let { _resultData.value = LiveDataResource.success(it) }
            }
        }
    }
}