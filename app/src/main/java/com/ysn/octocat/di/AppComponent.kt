package com.ysn.octocat.di

import com.ysn.octocat.App
import com.ysn.octocat.MainActivity
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [(AndroidSupportInjectionModule::class), (OkHttpModule::class), (RetrofitModule::class), (ActivityBuilder::class), (FiturModule::class)])
@Singleton
interface AppComponent {

    fun inject(app: App)

}
