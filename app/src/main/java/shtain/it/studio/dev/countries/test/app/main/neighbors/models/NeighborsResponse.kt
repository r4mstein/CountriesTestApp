package shtain.it.studio.dev.countries.test.app.main.neighbors.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Alex Shtain on 25.02.2019.
 */
data class NeighborsResponse(
    @SerializedName("name") val name: String,
    @SerializedName("flag") val flag: String
)