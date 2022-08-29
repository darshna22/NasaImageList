package com.example.obviouscompanyassignment.nasalist.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.obviouscompanyassignment.base.network.DataResult
import com.example.obviouscompanyassignment.nasalist.data.apimodel.NasaImageResponseItem
import com.example.obviouscompanyassignment.nasalist.repository.NasaImageRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NasaImageViewModel @Inject constructor(private val nasaImageRepository: NasaImageRepository) :
    ViewModel() {
    private var _ImageList = MutableLiveData<DataResult<List<NasaImageResponseItem>>>()
    val imageList: LiveData<DataResult<List<NasaImageResponseItem>>>
        get() = _ImageList


    fun fetchNasaImageListData() {
        viewModelScope.launch {
            try {

                _ImageList.value = DataResult.Loading
                val list = nasaImageRepository.fetchNasaImageData()
                _ImageList.value = DataResult.Success(list)
            } catch (e: Exception) {
                _ImageList.value = DataResult.Error(e.message)

            }
        }
    }

}