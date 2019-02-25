package shtain.it.studio.dev.countries.test.app.main.countries_list

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
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

    private var mAdapter: Adapter? = null
    val subject = PublishSubject.create<String>()
    private lateinit var mSearchDisposable: Disposable
    private var mSearchedText = ""

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
        srlCountries.setOnRefreshListener { loadData() }
    }

    override fun onStart() {
        super.onStart()
        mNavigator?.setToolbarTitle(resources.getString(R.string.toolbar_title_list))
        if (mAdapter?.getDataSize() == 0) loadData()
        subscribeForSearchObservable()
    }

    override fun onStop() {
        super.onStop()
        if (!mSearchDisposable.isDisposed) mSearchDisposable.dispose()
        mNavigator?.hideProgressBar()
        hideSwipeLoader()
    }

    private fun loadData() {
        mNavigator?.hideErrorPlaceholder()
        hideSwipeLoader()
        mNavigator?.showProgressBar()
        mPresenter.loadData()
    }

    @SuppressLint("CheckResult")
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_menu, menu)
        setupSearchView(menu?.findItem(R.id.action_search))
    }

    private fun setupSearchView(menu: MenuItem?) {
        val mSearchView = menu?.actionView as SearchView
        mSearchView.apply {
            if (!mSearchedText.isEmpty()) {
                menu.expandActionView()
                mSearchView.setQuery(mSearchedText, true)
                mSearchView.clearFocus()
            }
            else queryHint = resources.getString(R.string.search)

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
                mAdapter?.filterData(it)
                mSearchedText = it
            }
    }

    private fun setupList() {
        if (mAdapter == null) mAdapter = Adapter().apply { setClickListener(mClickListener) }
        rvList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    private val mClickListener = object : ClickListener {
        override fun itemClicked(data: AdapterData) {
            mNavigator?.showNeighborsFragment(data)
        }
    }

    override fun dataLoaded(data: ArrayList<AdapterData>) {
        mAdapter?.addData(data)
        mNavigator?.hideProgressBar()
        hideSwipeLoader()
    }

    override fun loadDataFailed(throwable: Throwable) {
        mNavigator?.hideProgressBar()
        mNavigator?.showErrorPlaceholder(throwable.message ?: "")
        hideSwipeLoader()
    }

    private fun hideSwipeLoader() {
        if (srlCountries.isRefreshing) srlCountries.isRefreshing = false
    }
}