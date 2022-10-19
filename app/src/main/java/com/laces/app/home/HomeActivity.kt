package com.laces.app.home

import android.view.LayoutInflater
import com.laces.app.databinding.ActivityHomeBinding
import com.laces.app.mvp.OccActivity

class HomeActivity : OccActivity<ActivityHomeBinding, HomePresenter, HomeView>(),
    HomeView {

    override fun providePresenter(): HomePresenter {
        return HomePresenter()
    }

    override fun provideBinding(layoutInflater: LayoutInflater): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun setContentText(text: String) {
        binding.textViewContent.text = text
    }
}