package shtain.it.studio.dev.countries.test.app.root

import android.content.Context
import shtain.it.studio.dev.countries.test.app.main.di.DaggerDiMainComponent
import shtain.it.studio.dev.countries.test.app.main.di.DiMainComponent
import shtain.it.studio.dev.countries.test.app.root.di.DaggerDiRootComponent
import shtain.it.studio.dev.countries.test.app.root.di.DiAppModule
import shtain.it.studio.dev.countries.test.app.splash.di.DaggerDiSplashComponent
import shtain.it.studio.dev.countries.test.app.splash.di.DiSplashComponent

/**
 * Created by Alex Shtain on 22.02.2019.
 */
class ObjectGraph private constructor(context: Context) {

    val mSplashComponent: DiSplashComponent
    val mMainComponent: DiMainComponent

    init {
        val rootComponent = DaggerDiRootComponent.builder().diAppModule(DiAppModule(context)).build()
        mSplashComponent = DaggerDiSplashComponent.builder().diRootComponent(rootComponent).build()
        mMainComponent = DaggerDiMainComponent.builder().diRootComponent(rootComponent).build()
    }

    companion object Factory {
        private var graph: ObjectGraph? = null

        fun getInstance(context: Context): ObjectGraph {
            if (graph == null) {
                graph = ObjectGraph(context)
            }
            return graph!!
        }
    }
}