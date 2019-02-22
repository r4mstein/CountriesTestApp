package shtain.it.studio.dev.countries.test.app.root.disposable_manager.di

import dagger.Module
import dagger.Provides
import shtain.it.studio.dev.countries.test.app.root.disposable_manager.DisposableManagerImpl
import shtain.it.studio.dev.countries.test.app.root.disposable_manager.IDisposableManager
import javax.inject.Singleton

/**
 * Created by Alex Shtain on 22.02.2019.
 */
@Module
class DiDisposableManagerModule {

    @Provides
    @Singleton
    fun provideDisposableManager(): IDisposableManager {
        return DisposableManagerImpl()
    }
}