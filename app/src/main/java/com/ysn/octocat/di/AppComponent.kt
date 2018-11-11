package com.ysn.octocat.di

import com.ysn.octocat.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [(OkHttpModule::class), (RetrofitModule::class)])
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)

}