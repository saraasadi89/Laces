package com.laces.app.details

import android.view.LayoutInflater
import com.laces.app.databinding.ActivityDetailsBinding
import com.laces.app.mvp.OccActivity

class DetailsActivity : OccActivity<ActivityDetailsBinding, DetailsPresenter, DetailsView>(),
    DetailsView {

    override fun providePresenter(): DetailsPresenter {
        return DetailsPresenter()
    }

    override fun provideBinding(layoutInflater: LayoutInflater): ActivityDetailsBinding {
        return ActivityDetailsBinding.inflate(layoutInflater)
    }

    override fun setContentText(text: String) {
        binding.textViewContent.text = text
    }
}