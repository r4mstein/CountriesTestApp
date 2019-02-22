package shtain.it.studio.dev.countries.test.app.root.base

/**
 * Created by Alex Shtain on 22.02.2019.
 */
interface IPresenter<V: IView> {
    fun setView(view: V)
    fun removeView()
    fun onStop()
}