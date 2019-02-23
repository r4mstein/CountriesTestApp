package shtain.it.studio.dev.countries.test.app.main.navigator

import shtain.it.studio.dev.countries.test.app.root.base.IPresenter
import shtain.it.studio.dev.countries.test.app.root.base.IView

/**
 * Created by Alex Shtain on 23.02.2019.
 */
interface IContract {

    interface View : IView

    interface Presenter : IPresenter<View>
}