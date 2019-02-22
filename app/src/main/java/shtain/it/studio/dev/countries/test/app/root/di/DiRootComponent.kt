package shtain.it.studio.dev.countries.test.app.root.di

import android.content.Context
import dagger.Component
import shtain.it.studio.dev.countries.test.app.root.anim_manager.IAnimManager
import shtain.it.studio.dev.countries.test.app.root.anim_manager.di.DiAnimManagerModule
import shtain.it.studio.dev.countries.test.app.root.disposable_manager.IDisposableManager
import shtain.it.studio.dev.countries.test.app.root.disposable_manager.di.DiDisposableManagerModule
import javax.inject.Singleton

/**
 * Created by Alex Shtain on 22.02.2019.
 */
@Singleton
@Component(
    modules = [
        DiAppModule::class,
        DiAnimManagerModule::class,
        DiDisposableManagerModule::class
    ]
)
interface DiRootComponent {
    fun context(): Context
    fun getAnimManager(): IAnimManager
    fun getDisposableManager(): IDisposableManager
}