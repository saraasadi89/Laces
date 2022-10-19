package com.laces.app.home

import com.laces.app.mvp.OccView

interface HomeView: OccView {
    fun setContentText(text: String)
}