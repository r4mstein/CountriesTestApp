package shtain.it.studio.dev.countries.test.app.root.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Alex Shtain on 22.02.2019.
 */
@Module
class DiAppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }
}