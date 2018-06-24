package com.takeiteasy.vip.genesistest.data.network.utils

//can't understand why do you need the interface?
interface NetworkManager {
    fun isInternetAvailable(): Boolean
}