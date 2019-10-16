package com.mino.urltask5ktlin.ui.common.binding

import com.mino.urltask5ktlin.di.main.PerMain
import com.mino.urltask5ktlin.ui.main.viewmodel.UrlViewModel
import javax.inject.Inject

@PerMain
class ClickHandler @Inject constructor() {

    fun addClick(urlViewModel: UrlViewModel) {
        urlViewModel.insert()
    }
}