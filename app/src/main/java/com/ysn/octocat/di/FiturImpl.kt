package com.ysn.octocat.di

import android.util.Log
import javax.inject.Inject

class FiturImpl @Inject constructor() : Fitur {

    override fun printOut() {
        Log.d("FiturImpl", "printOut")
    }


}