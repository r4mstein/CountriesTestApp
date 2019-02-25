package shtain.it.studio.dev.countries.test.app.main.di

import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import shtain.it.studio.dev.countries.test.app.main.IMainService
import shtain.it.studio.dev.countries.test.app.main.countries_list.CountriesPresenter
import shtain.it.studio.dev.countries.test.app.main.countries_list.ICountriesContract
import shtain.it.studio.dev.countries.test.app.main.navigator.IContract
import shtain.it.studio.dev.countries.test.app.main.navigator.MainPresenter
import shtain.it.studio.dev.countries.test.app.root.disposable_manager.IDisposableManager
import shtain.it.studio.dev.countries.test.app.root.network.retrofit.RetrofitHelper

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

    @Provides
    @MainScope
    fun provideCountriesPresenter(mainService: IMainService,
                                  disposableManager: IDisposableManager): ICountriesContract.Presenter {
        return CountriesPresenter(mainService, disposableManager)
    }

    @Provides
    @MainScope
    fun provideMainService(retrofitHelper: RetrofitHelper): IMainService {
        return retrofitHelper.createService(IMainService::class.java, HttpLoggingInterceptor.Level.BODY)
    }
}