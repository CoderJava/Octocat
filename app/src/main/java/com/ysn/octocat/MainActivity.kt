package com.ysn.octocat

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.inputmethod.EditorInfo
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.ysn.octocat.api.Endpoints
import com.ysn.octocat.di.AppComponent
import com.ysn.octocat.di.DaggerAppComponent
import com.ysn.octocat.di.OkHttpModule
import com.ysn.octocat.di.RetrofitModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val TAG = javaClass.simpleName
    lateinit var appComponent: AppComponent

    @Inject
    lateinit var endpoints: Endpoints

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermission()

        appComponent = DaggerAppComponent.builder()
            .okHttpModule(OkHttpModule())
            .retrofitModule(RetrofitModule())
            .build()
        appComponent.inject(this)
        edit_text_search_user.setOnEditorActionListener { _, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                endpoints.searchUsers("CoderJava")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            val jsonResponse = JSONObject(it.string())
                            Log.d(TAG, "jsonResponse: $jsonResponse")
                        },
                        {
                            it.printStackTrace()
                        },
                        {
                            /* nothing to do in here */
                        }
                    )
            }
            false
        }
    }

    private fun checkPermission() {
        Dexter.withActivity(this)
            .withPermission(Manifest.permission.INTERNET)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    /* nothing to do in here */
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {
                    /* nothing to do in here */
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    /* nothing to do in here */
                }
            })
            .check()
    }
}
