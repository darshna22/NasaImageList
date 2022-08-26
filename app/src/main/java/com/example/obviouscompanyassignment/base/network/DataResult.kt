package com.example.obviouscompanyassignment.base.network

sealed class DataResult<out R>{
    data class Success<T>(val data:T): DataResult<T>()
    data class Error(val message:String?): DataResult<Nothing>()
    object Loading: DataResult<Nothing>()

}