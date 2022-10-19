package com.laces.app.sdk

import javax.inject.Singleton

interface Sdk {
    fun getProducts()
    fun getDetails(id: String)
}

@Singleton
internal class SdkImpl : Sdk {

    override fun getProducts() {

    }

    override fun getDetails(id: String) {

    }
}
