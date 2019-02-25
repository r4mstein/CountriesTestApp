package shtain.it.studio.dev.countries.test.app.root.network

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Alex Shtain on 25.02.2019.
 */
class NetworkManagerImpl(context: Context) : INetworkManager {

    private var mCm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun isConnected(): Boolean {
        val activeNetwork = mCm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}