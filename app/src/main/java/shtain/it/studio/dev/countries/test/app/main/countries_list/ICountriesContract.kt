package shtain.it.studio.dev.countries.test.app.main.countries_list

import shtain.it.studio.dev.countries.test.app.main.countries_list.models.AdapterData
import shtain.it.studio.dev.countries.test.app.root.base.IPresenter
import shtain.it.studio.dev.countries.test.app.root.base.IView

/**
 * Created by Alex Shtain on 23.02.2019.
 */
interface ICountriesContract {

    interface View : IView {
        fun dataLoaded(data: ArrayList<AdapterData>)
        fun loadDataFailed(throwable: Throwable)
    }

    interface Presenter : IPresenter<View> {
        fun loadData()
    }
}