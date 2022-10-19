package com.laces.app.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class OccFragment<P : OccPresenter<V>, V : OccView, B : ViewBinding> : Fragment(),
    OccView {
    private var _binding: B? = null

    protected val binding get() = _binding!!

    protected lateinit var presenter: P

    abstract fun providePresenter(): P

    abstract fun provideBinding(inflater: LayoutInflater): B

    abstract fun buildView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = providePresenter()
        presenter.onCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = provideBinding(inflater)
        buildView()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        @Suppress("UNCHECKED_CAST")
        presenter.onStart(this as V)
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}