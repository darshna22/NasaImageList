package com.example.obviouscompanyassignment.nasalist.data.apimodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NasaImageResponseItem(
    @Expose
    @SerializedName("copyright")
    val copyright: String,
    @Expose
    @SerializedName("date")
    val date: String,
    @Expose
    @SerializedName("explanation")
    val explanation: String,
    @Expose
    @SerializedName("hdurl")
    val hdurl: String,
    @Expose
    @SerializedName("media_type")
    val media_type: String,
    @Expose
    @SerializedName("service_version")
    val service_version: String,
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("url")
    val url: String

):Serializable