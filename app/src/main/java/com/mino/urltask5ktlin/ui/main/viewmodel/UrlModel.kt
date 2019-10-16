package com.mino.urltask5ktlin.ui.main.viewmodel

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.mino.urltask5ktlin.utils.URL_LOADING

class UrlModel(
    url: String,
    availability: Int = URL_LOADING
) {

    fun resetState(urlModel: UrlModel): UrlModel {
        urlModel.availability.set(URL_LOADING)
        return urlModel
    }

    val url: ObservableField<String> = ObservableField(url)
    val availability: ObservableInt = ObservableInt(availability)
}