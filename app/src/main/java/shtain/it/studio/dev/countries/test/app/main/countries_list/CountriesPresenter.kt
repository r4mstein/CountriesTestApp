package shtain.it.studio.dev.countries.test.app.main.countries_list

import android.annotation.SuppressLint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import shtain.it.studio.dev.countries.test.app.main.IMainService
import shtain.it.studio.dev.countries.test.app.main.countries_list.models.AdapterData
import shtain.it.studio.dev.countries.test.app.main.countries_list.models.AllCountriesResponse
import shtain.it.studio.dev.countries.test.app.root.base.BasePresenter
import shtain.it.studio.dev.countries.test.app.root.disposable_manager.IDisposableManager
import javax.inject.Inject

/**
 * Created by Alex Shtain on 23.02.2019.
 */
class CountriesPresenter @Inject constructor(
    private val mainService: IMainService,
    private val disposableManager: IDisposableManager
) : BasePresenter<ICountriesContract.View>(), ICountriesContract.Presenter {

    override fun onStop() {
        disposableManager.dispose()
    }

    @SuppressLint("CheckResult")
    override fun loadData() {
        disposableManager.add(mainService.getAllCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    mView?.dataLoaded(createAdapterData(response))
                },
                { throwable ->
                    mView?.loadDataFailed(throwable)
                }
            ))
    }

    private fun createAdapterData(data: List<AllCountriesResponse>): ArrayList<AdapterData> {
        val result = ArrayList<AdapterData>()
        data.map { result.add(AdapterData(it.name, it.flag, it.borders)) }
        return result
    }
}