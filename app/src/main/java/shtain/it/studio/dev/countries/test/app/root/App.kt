package shtain.it.studio.dev.countries.test.app.root

import android.app.Application

/**
 * Created by Alex Shtain on 22.02.2019.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ObjectGraph.getInstance(this) //initialize di
    }
}