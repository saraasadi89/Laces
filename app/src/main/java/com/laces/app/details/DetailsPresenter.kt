package com.laces.app.details

import com.laces.app.mvp.OccPresenter

class DetailsPresenter: OccPresenter<DetailsView>() {

    override fun onCreate() {
        super.onCreate()
        sendToView { view ->
            view.setContentText("Hello Details!")
        }
    }
}