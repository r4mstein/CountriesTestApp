package shtain.it.studio.dev.countries.test.app.main.navigator

import shtain.it.studio.dev.countries.test.app.main.countries_list.models.AdapterData
import shtain.it.studio.dev.countries.test.app.root.base.INavigator

/**
 * Created by Alex Shtain on 23.02.2019.
 */
interface IMainNavigator : INavigator {
    fun showCountriesFragment()
    fun showNeighborsFragment(data: AdapterData)

    fun setToolbarTitle(title: String)

    fun showProgressBar()
    fun hideProgressBar()

    fun showErrorPlaceholder(message: String)
    fun hideErrorPlaceholder()
}