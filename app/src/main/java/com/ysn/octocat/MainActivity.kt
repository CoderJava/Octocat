package com.ysn.octocat

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.ysn.octocat.api.Endpoints
import com.ysn.octocat.di.Fitur
import com.ysn.octocat.di.FiturMainActivity2
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val TAG = javaClass.simpleName

    @Inject
    lateinit var endpoints: Endpoints

    @Inject
    lateinit var fitur: Fitur

    @Inject
    lateinit var fiturMainActivity: FiturMainActivity

    @Inject
    lateinit var fiturMainActivity2: FiturMainActivity2

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermission()

        edit_text_search_user.setOnEditorActionListener { _, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                showLoading()
                fitur.printOut()
                fiturMainActivity.printOut()
                fiturMainActivity2.printOut()
                endpoints.searchUsers(edit_text_search_user.text.toString().trim())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            hideLoading()
                            val jsonResponse = JSONObject(it.string())
                            Log.d(TAG, "jsonResponse: $jsonResponse")
                            text_view_result.text = jsonResponse.toString()
                        },
                        {
                            hideLoading()
                            it.printStackTrace()
                        },
                        {

                        }
                    )
            }
            false
        }
    }

    private fun hideLoading() {
        progress_bar_activity_main.visibility = View.GONE
        text_view_result.visibility = View.VISIBLE
    }

    private fun showLoading() {
        progress_bar_activity_main.visibility = View.VISIBLE
        text_view_result.visibility = View.GONE
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
