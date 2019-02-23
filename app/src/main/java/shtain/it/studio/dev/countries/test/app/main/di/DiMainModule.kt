package shtain.it.studio.dev.countries.test.app.main.di

import dagger.Module
import dagger.Provides
import shtain.it.studio.dev.countries.test.app.main.navigator.IContract
import shtain.it.studio.dev.countries.test.app.main.navigator.MainPresenter

/**
 * Created by Alex Shtain on 23.02.2019.
 */
@Module
class DiMainModule {

    @Provides
    @MainScope
    fun provideMainPresenter(): IContract.Presenter {
        return MainPresenter()
    }
}