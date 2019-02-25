package shtain.it.studio.dev.countries.test.app.main.neighbors

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import shtain.it.studio.dev.countries.test.app.main.IMainService
import shtain.it.studio.dev.countries.test.app.main.countries_list.models.AdapterData
import shtain.it.studio.dev.countries.test.app.main.neighbors.models.NeighborsResponse
import shtain.it.studio.dev.countries.test.app.root.base.BasePresenter
import shtain.it.studio.dev.countries.test.app.root.disposable_manager.IDisposableManager
import shtain.it.studio.dev.countries.test.app.root.network.INetworkManager
import javax.inject.Inject

/**
 * Created by Alex Shtain on 25.02.2019.
 */
class NeighborsPresenter @Inject constructor(
    private val mainService: IMainService,
    private val disposableManager: IDisposableManager,
    private val networkManager: INetworkManager
): BasePresenter<INeighborsContract.View>(), INeighborsContract.Presenter {

    override fun onStop() {
        disposableManager.dispose()
    }

    override fun loadData(codes: String) {
        if (networkManager.isConnected()) {
            disposableManager.add(mainService.getNeighbors(codes)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        mView?.dataLoaded(createAdapterData(it))
                    },
                    {
                        mView?.loadDataFailed(it)
                    }
                )
            )
        } else {
            mView?.loadDataFailed(Throwable("No internet connection"))
        }
    }

    private fun createAdapterData(data: List<NeighborsResponse>): ArrayList<AdapterData> {
        val result = ArrayList<AdapterData>()
        data.map { result.add(AdapterData(it.name, it.flag, emptyList())) }
        return result
    }
}