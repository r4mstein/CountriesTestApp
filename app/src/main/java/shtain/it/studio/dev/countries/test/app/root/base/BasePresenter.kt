package shtain.it.studio.dev.countries.test.app.root.base

import android.support.annotation.CallSuper

/**
 * Created by Alex Shtain on 22.02.2019.
 */
abstract class BasePresenter<V: IView> : IPresenter<V> {

    protected var mView: V? = null

    @CallSuper
    override fun setView(view: V) {
        mView = view
    }

    @CallSuper
    override fun removeView() {
        mView = null
    }

    /**
     *  This method is called in onStop() method in BaseFragment and BaseActivity.
     *  Override to add custom behavior.
     */
    override fun onStop() {

    }
}