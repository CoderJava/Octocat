package com.ysn.octocat.di

import android.app.Application
import com.ysn.octocat.App
import com.ysn.octocat.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [(AndroidSupportInjectionModule::class), (AppModule::class), (OkHttpModule::class), (RetrofitModule::class), (ActivityBuilder::class), (FiturModule::class)])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

}
