package shtain.it.studio.dev.countries.test.app.main.countries_list

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.countries_fragment.*
import shtain.it.studio.dev.countries.test.app.R
import shtain.it.studio.dev.countries.test.app.main.countries_list.adapter.Adapter
import shtain.it.studio.dev.countries.test.app.main.countries_list.adapter.ClickListener
import shtain.it.studio.dev.countries.test.app.main.countries_list.models.AdapterData
import shtain.it.studio.dev.countries.test.app.main.navigator.IMainNavigator
import shtain.it.studio.dev.countries.test.app.root.base.BaseFragment
import java.util.concurrent.TimeUnit


/**
 * Created by Alex Shtain on 23.02.2019.
 */
class CountriesFragment : BaseFragment<IMainNavigator, ICountriesContract.View, ICountriesContract.Presenter>(),
    ICountriesContract.View {

    private lateinit var mAdapter: Adapter
    val subject = PublishSubject.create<String>()
    private lateinit var mSearchDisposable: Disposable

    companion object {
        fun newInstance(): CountriesFragment {
            return CountriesFragment()
        }
    }

    override fun setup() {
        mObjectGraph?.mMainComponent?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.countries_fragment, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
    }

    override fun onStart() {
        super.onStart()
        mNavigator?.setToolbarTitle(resources.getString(R.string.toolbar_title_list))
        if (mAdapter.getDataSize() == 0) {
            mNavigator?.showProgressBar()
            mPresenter.loadData()
        }
        subscribeForSearchObservable()
    }

    override fun onStop() {
        super.onStop()
        if (!mSearchDisposable.isDisposed) mSearchDisposable.dispose()
        mNavigator?.hideProgressBar()
    }

    @SuppressLint("CheckResult")
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_menu, menu)
        setupSearchView(menu?.findItem(R.id.action_search))
    }

    private fun setupSearchView(menu: MenuItem?) {
        val mSearchView = menu?.actionView as SearchView
        mSearchView.apply {
            queryHint = resources.getString(R.string.search)

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    subject.onNext(newText)
                    return true
                }
            })
        }
    }

    private fun subscribeForSearchObservable() {
        mSearchDisposable = subject
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                mAdapter.filterData(it)
            }
    }

    private fun setupList() {
        mAdapter = Adapter(context!!).apply { setClickListener(mClickListener) }
        rvList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    private val mClickListener = object : ClickListener {
        override fun itemClicked(data: AdapterData) {
            Toast.makeText(context, data.name, Toast.LENGTH_SHORT).show()
        }
    }

    override fun dataLoaded(data: ArrayList<AdapterData>) {
        mAdapter.addData(data)
        mNavigator?.hideProgressBar()
    }

    override fun loadDataFailed(throwable: Throwable) {
        mNavigator?.hideProgressBar()
        Log.e("CountriesFragment", "loadDataFailed ${throwable.message}")
    }
}