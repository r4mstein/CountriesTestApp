package shtain.it.studio.dev.countries.test.app.root.base

/**
 * Created by Alex Shtain on 22.02.2019.
 */
interface INavigatorProvider<N : INavigator> {
    fun getNavigator(): N?
}