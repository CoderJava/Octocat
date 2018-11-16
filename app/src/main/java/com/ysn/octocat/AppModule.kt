package com.ysn.octocat

import android.app.Application
import com.ysn.octocat.di.FiturMainActivity2
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [(MainActivityComponent::class)])
class AppModule {

    /*@Provides
    @Singleton
    fun provideFiturMainActivity2(application: Application) = FiturMainActivity2(application)*/

}