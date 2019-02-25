package shtain.it.studio.dev.countries.test.app.root.network.di

import android.content.Context
import dagger.Module
import dagger.Provides
import shtain.it.studio.dev.countries.test.app.root.network.INetworkManager
import shtain.it.studio.dev.countries.test.app.root.network.NetworkManagerImpl
import shtain.it.studio.dev.countries.test.app.root.network.retrofit.RetrofitHelper
import javax.inject.Singleton

/**
 * Created by Alex Shtain on 25.02.2019.
 */
@Module
class DiNetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitHelper(): RetrofitHelper {
        return RetrofitHelper()
    }

    @Provides
    @Singleton
    fun provideNetworkManager(context: Context): INetworkManager {
        return NetworkManagerImpl(context)
    }
}