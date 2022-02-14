package com.github.nseki.samplestartactivityforresult.samplestartactivityforresult

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.nseki.samplestartactivityforresult.databinding.ActivityTargetBinding

class TargetActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SCORE = "score"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityTargetBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        binding.button.setOnClickListener {
            val resultIntent = Intent("Dummy action").putExtra(EXTRA_SCORE, 77)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}