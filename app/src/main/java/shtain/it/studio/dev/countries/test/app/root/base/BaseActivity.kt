package shtain.it.studio.dev.countries.test.app.root.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import shtain.it.studio.dev.countries.test.app.root.ObjectGraph
import javax.inject.Inject

/**
 * Created by Alex Shtain on 22.02.2019.
 */
abstract class BaseActivity<N : INavigator, V : IView, P : IPresenter<V>> : AppCompatActivity(),
    INavigatorProvider<N>, IView {

    protected var mNavigator: N? = null
    @Inject
    lateinit var mPresenter: P
    protected var mObjectGraph: ObjectGraph? = null

    protected abstract fun setup()

    override fun getNavigator(): N? {
        return mNavigator
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mObjectGraph = ObjectGraph.getInstance(application)
        setup()
    }

    override fun onStart() {
        super.onStart()
        @Suppress("UNCHECKED_CAST")
        mPresenter.setView(this as V)
    }

    override fun onStop() {
        super.onStop()
        mPresenter.onStop()
        mPresenter.removeView()
    }

    protected fun addFragment(_containerId: Int, _fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(_containerId, _fragment)
            .commit()
    }

    protected fun replaceFragment(_containerId: Int, _fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(_containerId, _fragment)
            .commit()
    }

    protected fun replaceFragmentAndAddToBackStack(_containerId: Int, _fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(_containerId, _fragment)
            .addToBackStack(null)
            .commit()
    }
}