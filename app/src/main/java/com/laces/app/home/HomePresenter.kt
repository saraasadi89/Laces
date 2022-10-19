package com.laces.app.home

import com.laces.app.mvp.OccPresenter

class HomePresenter : OccPresenter<HomeView>() {

    override fun onCreate() {
        super.onCreate()
        sendToView { view ->
            view.setContentText("Hello product list!")
        }
    }
}