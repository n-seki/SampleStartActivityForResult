package com.github.nseki.samplestartactivityforresult.samplestartactivityforresult.launcher

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleEmitter

class RxStartActivityForResult(
    activity: ComponentActivity
) {

    private var emitter: SingleEmitter<ActivityResult>? = null
    private val startActivityForResultLauncher = activity.registerForActivityResult(
        StartActivityForResult()
    ) {
        emitter?.onSuccess(it)
    }

    fun launch(intent: Intent): Single<ActivityResult> {
        startActivityForResultLauncher.launch(intent)
        return Single.create { emitter = it }
    }
}