package shtain.it.studio.dev.countries.test.app.main.countries_list.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Alex Shtain on 23.02.2019.
 */
data class AdapterData(var name: String, var flagUrl: String, var borders: List<String>) : Parcelable {

    constructor(source: Parcel) : this(
        source.readString() ?: "",
        source.readString() ?: "",
        source.createStringArrayList() ?: emptyList()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(flagUrl)
        writeStringList(borders)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<AdapterData> = object : Parcelable.Creator<AdapterData> {
            override fun createFromParcel(source: Parcel): AdapterData = AdapterData(source)
            override fun newArray(size: Int): Array<AdapterData?> = arrayOfNulls(size)
        }
    }
}