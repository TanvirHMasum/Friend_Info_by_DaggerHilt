package com.example.friendsinfo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.friendsinfo.model.FriendData
import com.example.friendsinfo.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel
@Inject
constructor(private val repository: ImageRepository) : ViewModel() {

    private val response_api = MutableLiveData<FriendData>()

    val responseImages: LiveData<FriendData>
        get() = response_api


    init {
        getAllImages()
    }

    private fun getAllImages() = viewModelScope.launch {

        repository.getAllImages().let { response ->

            if (response.isSuccessful) {
                response_api.postValue(response.body())
            }else{
                Log.d("your tag",
                    "getAllImages Error: ${response.errorBody()}")
            }

        }

    }
}
