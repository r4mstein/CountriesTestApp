package shtain.it.studio.dev.countries.test.app.root.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.view.View
import shtain.it.studio.dev.countries.test.app.root.ObjectGraph
import javax.inject.Inject

/**
 * Created by Alex Shtain on 22.02.2019.
 */
abstract class BaseFragment<N : INavigator, V : IView, P : IPresenter<V>> : Fragment(), IView {

    protected var mNavigator: N? = null
    @Inject
    lateinit var mPresenter: P
    protected var mObjectGraph: ObjectGraph? = null

    protected abstract fun setup()

    @CallSuper
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        @Suppress("UNCHECKED_CAST")
        mNavigator = (activity as INavigatorProvider<N>).getNavigator()
        mObjectGraph = ObjectGraph.getInstance(this.activity!!.application)
        setup()
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        @Suppress("UNCHECKED_CAST")
        mPresenter.setView(this as V)
    }

    @CallSuper
    override fun onStop() {
        super.onStop()
        mPresenter.onStop()
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.removeView()
    }
}