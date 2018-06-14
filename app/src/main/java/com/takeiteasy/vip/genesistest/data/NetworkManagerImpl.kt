package com.takeiteasy.vip.genesistest.data

import com.takeiteasy.vip.genesistest.domain.network.NetworkManager
import java.io.IOException

class NetworkManagerImpl : NetworkManager {

    override fun isInternetAvailable(): Boolean = isOnline()

    private fun isOnline(): Boolean {
        val runtime = Runtime.getRuntime()
        try {
            val ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8")
            val exitValue = ipProcess.waitFor()
            return exitValue == 0
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }


        return false
    }
}