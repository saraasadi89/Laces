package com.laces.app.details

import com.laces.app.mvp.OccView

interface DetailsView: OccView {
    fun setContentText(text: String)
}