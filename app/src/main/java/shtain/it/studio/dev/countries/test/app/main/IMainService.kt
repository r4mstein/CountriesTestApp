package shtain.it.studio.dev.countries.test.app.main

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import shtain.it.studio.dev.countries.test.app.main.countries_list.models.AllCountriesResponse
import shtain.it.studio.dev.countries.test.app.main.neighbors.models.NeighborsResponse

/**
 * Created by Alex Shtain on 25.02.2019.
 */
interface IMainService {

    @GET("all")
    fun getAllCountries(): Single<ArrayList<AllCountriesResponse>>

    @GET("alpha")
    fun getNeighbors(@Query("codes") codes: String): Single<ArrayList<NeighborsResponse>>
}