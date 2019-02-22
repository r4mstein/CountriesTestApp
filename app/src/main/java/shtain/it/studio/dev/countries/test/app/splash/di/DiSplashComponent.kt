package shtain.it.studio.dev.countries.test.app.splash.di

import dagger.Component
import shtain.it.studio.dev.countries.test.app.root.di.DiRootComponent
import shtain.it.studio.dev.countries.test.app.splash.SplashActivity

/**
 * Created by Alex Shtain on 22.02.2019.
 */
@SplashScope
@Component(
    modules = [DiSplashModule::class],
    dependencies = [DiRootComponent::class]
)
interface DiSplashComponent {
    fun inject(activity: SplashActivity)
}