package shtain.it.studio.dev.countries.test.app.root.di

import android.content.Context
import dagger.Component
import shtain.it.studio.dev.countries.test.app.root.anim_manager.IAnimManager
import shtain.it.studio.dev.countries.test.app.root.anim_manager.di.DiAnimManagerModule
import shtain.it.studio.dev.countries.test.app.root.disposable_manager.IDisposableManager
import shtain.it.studio.dev.countries.test.app.root.disposable_manager.di.DiDisposableManagerModule
import shtain.it.studio.dev.countries.test.app.root.network.INetworkManager
import shtain.it.studio.dev.countries.test.app.root.network.di.DiNetworkModule
import shtain.it.studio.dev.countries.test.app.root.network.retrofit.RetrofitHelper
import javax.inject.Singleton

/**
 * Created by Alex Shtain on 22.02.2019.
 */
@Singleton
@Component(
    modules = [
        DiAppModule::class,
        DiAnimManagerModule::class,
        DiDisposableManagerModule::class,
        DiNetworkModule::class
    ]
)
interface DiRootComponent {
    fun context(): Context
    fun getAnimManager(): IAnimManager
    fun getDisposableManager(): IDisposableManager
    fun getRetrofitHelper(): RetrofitHelper
    fun getNetworkManager(): INetworkManager
}