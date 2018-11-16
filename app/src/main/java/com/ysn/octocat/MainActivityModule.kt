package com.ysn.octocat

import android.app.Application
import com.ysn.octocat.di.FiturMainActivity2
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideFiturMainActivity(): FiturMainActivity {
        return FiturMainActivity()
    }

    @Provides
    fun provideFiturMainActivity2(application: Application): FiturMainActivity2 {
        return FiturMainActivity2(application)
    }

}