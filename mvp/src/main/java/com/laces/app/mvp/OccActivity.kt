package com.laces.app.mvp

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class OccActivity<B : ViewBinding, P : OccPresenter<V>, V : OccView> : AppCompatActivity(),
    OccView {

    abstract fun providePresenter(): P

    protected lateinit var presenter: P

    private var thread: Thread? = null
    private var maintenanceThread: Thread? = null

    private var isAttached = false
    private var isResumed = true

    private var ownResources: Resources? = null

    private var newContext: Context? = null

    protected lateinit var binding: B

    abstract fun provideBinding(layoutInflater: LayoutInflater): B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = providePresenter()
        presenter.onCreate()
        binding = provideBinding(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        isResumed = true
    }

    override fun onStart() {
        isAttached = true
        super.onStart()

        @Suppress("UNCHECKED_CAST")
        presenter.onStart(this as V)
    }

    override fun onPause() {
        super.onPause()
        isResumed = false
    }

    override fun onStop() {
        isAttached = false
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun runUiThread(block: (() -> Unit)) {
        Thread { runOnUiThread { block() } }.start()
    }

    protected fun runThread(block: (() -> Unit)) {
        Thread { block() }.start()
    }
}
