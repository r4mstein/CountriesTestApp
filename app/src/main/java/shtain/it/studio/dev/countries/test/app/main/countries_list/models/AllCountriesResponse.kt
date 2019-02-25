package shtain.it.studio.dev.countries.test.app.main.countries_list.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Alex Shtain on 25.02.2019.
 */
data class AllCountriesResponse(
    @SerializedName("name") val name: String,
    @SerializedName("flag") val flag: String,
    @SerializedName("borders") val borders: List<String>
)