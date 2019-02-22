package shtain.it.studio.dev.countries.test.app.root.anim_manager.di

import dagger.Module
import dagger.Provides
import shtain.it.studio.dev.countries.test.app.root.anim_manager.AnimManagerImpl
import shtain.it.studio.dev.countries.test.app.root.anim_manager.IAnimManager
import javax.inject.Singleton

/**
 * Created by Alex Shtain on 22.02.2019.
 */
@Module
class DiAnimManagerModule {

    @Provides
    @Singleton
    fun provideAnimManager(): IAnimManager {
        return AnimManagerImpl()
    }
}