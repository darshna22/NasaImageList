package com.example.obviouscompanyassignment.nasaimagedetail.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.obviouscompanyassignment.base.network.DataResult
import com.example.obviouscompanyassignment.nasalist.data.apimodel.NasaImageResponseItem
import com.example.obviouscompanyassignment.nasalist.repository.NasaImageRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NasaDetailImageViewModel @Inject constructor(private val nasaImageRepository: NasaImageRepository) :
    ViewModel() {
    fun fetchNasaImageListData() {
        viewModelScope.launch {
            nasaImageRepository.fetchNasaImageData()
        }
    }

    fun nasaImageListData(): LiveData<DataResult<List<NasaImageResponseItem>>> {
        return nasaImageRepository.nasaImageDataList
    }

}