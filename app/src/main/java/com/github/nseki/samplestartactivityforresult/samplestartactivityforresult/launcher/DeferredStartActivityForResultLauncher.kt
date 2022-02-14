package com.github.nseki.samplestartactivityforresult.samplestartactivityforresult.launcher

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import kotlinx.coroutines.CompletableDeferred

class DeferredStartActivityForResultLauncher(
    activity: ComponentActivity
) {

    private var deferred: CompletableDeferred<ActivityResult>? = null
    private val startActivityForResultLauncher = activity.registerForActivityResult(
        StartActivityForResult()
    ) {
        deferred?.complete(it)
    }

    fun launch(intent: Intent): CompletableDeferred<ActivityResult> {
        startActivityForResultLauncher.launch(intent)
        deferred = CompletableDeferred()
        return deferred!!
    }
}