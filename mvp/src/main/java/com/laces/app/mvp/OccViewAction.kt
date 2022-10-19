package com.laces.app.mvp

interface OccViewAction<V: OccView> {
    fun invoke(view: V)
}