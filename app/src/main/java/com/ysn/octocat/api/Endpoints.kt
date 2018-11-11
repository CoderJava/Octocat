package com.ysn.octocat.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoints {

    @GET("search/users")
    fun searchUsers(@Query("q") q: String): Observable<ResponseBody>

}