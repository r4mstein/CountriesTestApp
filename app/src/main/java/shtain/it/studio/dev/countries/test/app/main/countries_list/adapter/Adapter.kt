package shtain.it.studio.dev.countries.test.app.main.countries_list.adapter

import android.content.Context
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import shtain.it.studio.dev.countries.test.app.R
import shtain.it.studio.dev.countries.test.app.main.countries_list.models.AdapterData
import shtain.it.studio.dev.countries.test.app.root.GlideApp
import shtain.it.studio.dev.countries.test.app.root.SvgSoftwareLayerSetter


/**
 * Created by Alex Shtain on 23.02.2019.
 */
class Adapter(private val context: Context) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private val mData = ArrayList<AdapterData>()
    private val mAllDataForFilter = ArrayList<AdapterData>()
    private var mRequestBuilder: RequestBuilder<PictureDrawable>? = null
    private lateinit var mClickListener: ClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.countries_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mData[position]
        holder.name.text = data.name
        loadFlag(data, holder)
        holder.itemView.setOnClickListener { mClickListener.itemClicked(data) }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    private fun loadFlag(data: AdapterData, holder: ViewHolder) {
        if (mRequestBuilder == null) {
            mRequestBuilder = GlideApp.with(context)
                .`as`(PictureDrawable::class.java)
                .placeholder(R.drawable.ic_launcher_background)
                .transition(withCrossFade())
                .listener(SvgSoftwareLayerSetter())
        }
        mRequestBuilder?.load(Uri.parse(data.flagUrl.trim()))?.into(holder.flag)
    }

    fun addData(data: ArrayList<AdapterData>) {
        useData(data)
        processData(mAllDataForFilter, data)
    }

    fun filterData(filter: String) {
        if (filter.isEmpty()) useData(mAllDataForFilter)
        else useData(mAllDataForFilter.filter { it.name.startsWith(filter, true) })

    }

    private fun useData(data: Collection<AdapterData>) {
        processData(mData, data)
        notifyDataSetChanged()
    }

    private fun processData(list: ArrayList<AdapterData>, data: Collection<AdapterData>) {
        list.apply {
            clear()
            addAll(data)
        }
    }

    fun getDataSize(): Int {
        return mAllDataForFilter.size
    }

    fun setClickListener(listener: ClickListener) {
        mClickListener = listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val flag: ImageView = view.findViewById(R.id.ivCountryFlag)
        val name: TextView = view.findViewById(R.id.tvCountryName)
    }
}