package shtain.it.studio.dev.countries.test.app.splash

import android.animation.AnimatorSet
import shtain.it.studio.dev.countries.test.app.root.base.IPresenter
import shtain.it.studio.dev.countries.test.app.root.base.IView

/**
 * Created by Alex Shtain on 22.02.2019.
 */
interface IContract {

    interface View : IView {
        fun startAnimation()
        fun openMainScreen()
    }

    interface Presenter : IPresenter<View> {
        fun startTimer()
        fun createLogoAnim(view: android.view.View): AnimatorSet
        fun createTextAnim(view: android.view.View): AnimatorSet
    }
}