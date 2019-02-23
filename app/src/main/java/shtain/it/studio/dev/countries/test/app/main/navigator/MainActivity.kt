package shtain.it.studio.dev.countries.test.app.main.navigator

import android.os.Bundle
import kotlinx.android.synthetic.main.toolbar.*
import shtain.it.studio.dev.countries.test.app.R
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
    }

    override fun setToolbarTitle(title: String) {
        toolbar.title = title
    }
}
