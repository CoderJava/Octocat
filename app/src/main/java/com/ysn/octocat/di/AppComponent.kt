package com.ysn.octocat.di

import com.ysn.octocat.api.Endpoints
import dagger.Component
import javax.inject.Singleton

@Component(modules = [(OkHttpModule::class), (RetrofitModule::class)])
@Singleton
interface AppComponent {

    fun getEndpoints(): Endpoints

}