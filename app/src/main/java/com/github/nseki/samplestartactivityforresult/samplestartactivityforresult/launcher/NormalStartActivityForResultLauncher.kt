package com.github.nseki.samplestartactivityforresult.samplestartactivityforresult.launcher

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult

class NormalStartActivityForResultLauncher(
    activity: ComponentActivity,
    action: (ActivityResult) -> Unit,
) {

    private val startActivityForResult = activity.registerForActivityResult(
        StartActivityForResult()
    ) { activityResult -> action(activityResult) }

    fun launch(intent: Intent) {
        startActivityForResult.launch(intent)
    }
}