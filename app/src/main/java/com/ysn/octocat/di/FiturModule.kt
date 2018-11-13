package com.ysn.octocat.di

import dagger.Binds
import dagger.Module

@Module
abstract class FiturModule {

    @Binds
    abstract fun bindFitur(fiturImpl: FiturImpl): Fitur

}