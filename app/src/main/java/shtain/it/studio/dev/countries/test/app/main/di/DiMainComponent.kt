package shtain.it.studio.dev.countries.test.app.main.di

import dagger.Component
import shtain.it.studio.dev.countries.test.app.main.countries_list.CountriesFragment
import shtain.it.studio.dev.countries.test.app.main.navigator.MainActivity
import shtain.it.studio.dev.countries.test.app.main.neighbors.NeighborsFragment
import shtain.it.studio.dev.countries.test.app.root.di.DiRootComponent

/**
 * Created by Alex Shtain on 23.02.2019.
 */
@MainScope
@Component(
    modules = [DiMainModule::class],
    dependencies = [DiRootComponent::class]
)
interface DiMainComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: CountriesFragment)
    fun inject(fragment: NeighborsFragment)
}