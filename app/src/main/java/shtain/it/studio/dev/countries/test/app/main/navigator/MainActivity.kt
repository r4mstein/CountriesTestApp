package shtain.it.studio.dev.countries.test.app.main.navigator

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import shtain.it.studio.dev.countries.test.app.R
import shtain.it.studio.dev.countries.test.app.main.countries_list.CountriesFragment
import shtain.it.studio.dev.countries.test.app.root.base.BaseActivity

class MainActivity : BaseActivity<IMainNavigator, IContract.View, IContract.Presenter>(), IMainNavigator,
    IContract.View {

    override fun setup() {
        mNavigator = this
        mObjectGraph?.mMainComponent?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        showCountriesFragment()
    }

    override fun showCountriesFragment() {
        addFragment(R.id.flRootContainer, CountriesFragment.newInstance())
    }

    override fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun showProgressBar() {
        if (pbLoader.visibility == View.GONE) pbLoader.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        if (pbLoader.visibility == View.VISIBLE) pbLoader.visibility = View.GONE
    }
}
