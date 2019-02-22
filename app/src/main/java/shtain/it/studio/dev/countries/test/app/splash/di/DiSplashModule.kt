package shtain.it.studio.dev.countries.test.app.splash.di

import dagger.Module
import dagger.Provides
import shtain.it.studio.dev.countries.test.app.root.anim_manager.IAnimManager
import shtain.it.studio.dev.countries.test.app.root.disposable_manager.IDisposableManager
import shtain.it.studio.dev.countries.test.app.splash.IContract
import shtain.it.studio.dev.countries.test.app.splash.SplashPresenter

/**
 * Created by Alex Shtain on 22.02.2019.
 */
@Module
class DiSplashModule {

    @Provides
    @SplashScope
    fun provideSplashPresenter(animManager: IAnimManager,
                               disposableManager: IDisposableManager) : IContract.Presenter {
        return SplashPresenter(animManager, disposableManager)
    }
}