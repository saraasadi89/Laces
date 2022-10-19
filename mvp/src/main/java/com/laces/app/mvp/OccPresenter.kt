package com.laces.app.mvp

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import java.util.*

open class OccPresenter<V : OccView> {

    @Suppress("PrivatePropertyName")
    private val TAG = this.javaClass.simpleName

    protected val presenterScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private var view: V? = null

    private val executor = OccViewExecutor()

    private val queuedViewActions = Collections.synchronizedList(mutableListOf<OccViewAction<V>>())

    open fun onCreate() {
        Log.v(TAG, "onCreate()")
    }

    open fun onStart(view: V) {
        Log.v(TAG, "onStart()")
        this.view = view

        queuedViewActions.onEach { action -> sendToView(action) }
        queuedViewActions.clear()
    }

    open fun onStop() {
        Log.v(TAG, "onStop()")
        presenterScope.cancel()
    }

    open fun onDestroy() {
        Log.v(TAG, "onDestroy()")
        view = null
        queuedViewActions.clear()
        presenterScope.cancel()
    }

    private fun sendToView(action: OccViewAction<V>) {
        val view = view

        if (view != null) {
            runOnUiThread {
                synchronized(view) {
                    action.invoke(view)
                }
            }
        } else {
            queuedViewActions.add(action)
        }
    }

    protected fun sendToView(block: ((V) -> Unit)) {
        val action = object : OccViewAction<V> {
            override fun invoke(view: V) {
                block(view)
            }
        }
        sendToView(action)
    }

    private fun runOnUiThread(action: Runnable) {
        executor.execute(action)
    }
}