package shtain.it.studio.dev.countries.test.app.main.neighbors

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import kotlinx.android.synthetic.main.neighbors_fragment.*
import shtain.it.studio.dev.countries.test.app.R
import shtain.it.studio.dev.countries.test.app.main.countries_list.adapter.Adapter
import shtain.it.studio.dev.countries.test.app.main.countries_list.models.AdapterData
import shtain.it.studio.dev.countries.test.app.main.navigator.IMainNavigator
import shtain.it.studio.dev.countries.test.app.root.base.BaseFragment

/**
 * Created by Alex Shtain on 25.02.2019.
 */
private const val NEIGHBORS_DATA = "neighbors_data"

class NeighborsFragment : BaseFragment<IMainNavigator, INeighborsContract.View, INeighborsContract.Presenter>(), INeighborsContract.View {

    private lateinit var mMainCountry: AdapterData
    private lateinit var mAdapter: Adapter

    companion object {
        fun newInstance(data: AdapterData): NeighborsFragment {
            val bundle = Bundle().apply { putParcelable(NEIGHBORS_DATA, data) }
            return NeighborsFragment().apply { arguments = bundle }
        }
    }

    override fun setup() {
        mObjectGraph?.mMainComponent?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.neighbors_fragment, container, false)
        arguments?.let { if (!it.isEmpty && it.containsKey(NEIGHBORS_DATA)) mMainCountry = it.getParcelable(NEIGHBORS_DATA) }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        srlNeighbors.setOnRefreshListener { loadData() }
    }

    override fun onStart() {
        super.onStart()
        mNavigator?.setToolbarTitle("${getString(R.string.toolbar_title_neighbors)} ${mMainCountry.name}")
        loadData()
    }

    override fun onStop() {
        super.onStop()
        mNavigator?.hideProgressBar()
        hideSwipeLoader()
    }

    private fun loadData() {
        mNavigator?.hideErrorPlaceholder()
        hideSwipeLoader()
        if (mMainCountry.borders.isEmpty()) tvPlaceholder.visibility = VISIBLE
        else {
            mNavigator?.showProgressBar()
            mPresenter.loadData(mMainCountry.borders.joinToString(separator = ";"))
        }
    }

    private fun setupList() {
        mAdapter = Adapter()
        rvNeighborsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    override fun dataLoaded(data: ArrayList<AdapterData>) {
        mAdapter.addData(data)
        mNavigator?.hideProgressBar()
        hideSwipeLoader()
    }

    override fun loadDataFailed(throwable: Throwable) {
        mNavigator?.hideProgressBar()
        hideSwipeLoader()
        mNavigator?.showErrorPlaceholder(throwable.message ?: "")
    }

    private fun hideSwipeLoader() {
        if (srlNeighbors.isRefreshing) srlNeighbors.isRefreshing = false
    }
}