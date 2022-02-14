package com.github.nseki.samplestartactivityforresult.samplestartactivityforresult

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.github.nseki.samplestartactivityforresult.samplestartactivityforresult.launcher.DeferredStartActivityForResultLauncher
import com.github.nseki.samplestartactivityforresult.samplestartactivityforresult.launcher.NormalStartActivityForResultLauncher
import com.github.nseki.samplestartactivityforresult.samplestartactivityforresult.launcher.RxStartActivityForResult
import com.github.nseki.samplestartactivityforresult.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val normalLauncher = NormalStartActivityForResultLauncher(this) { activityResult ->
        if (activityResult.resultCode == RESULT_OK) {
            onNewScore(activityResult.data)
        }
    }

    private val deferredLauncher = DeferredStartActivityForResultLauncher(this)

    private val rxLauncher = RxStartActivityForResult(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        binding.button.setOnClickListener {
            val intent = Intent(this, TargetActivity::class.java)
            normalLauncher.launch(intent)
        }
        binding.button2.setOnClickListener {
            lifecycleScope.launch {
                val intent = Intent(this@MainActivity, TargetActivity::class.java)
                val activityResult = deferredLauncher.launch(intent).await()
                if (activityResult.resultCode == Activity.RESULT_OK) {
                    onNewScore(activityResult.data)
                }
            }
        }
        binding.button3.setOnClickListener {
            val intent = Intent(this@MainActivity, TargetActivity::class.java)
            rxLauncher.launch(intent)
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { activityResult ->
                    if (activityResult.resultCode == Activity.RESULT_OK) {
                        onNewScore(activityResult.data)
                    }
                }
        }
    }

    private fun onNewScore(data: Intent?) {
        val score = data?.getIntExtra(TargetActivity.EXTRA_SCORE, 0)
        Toast.makeText(this, "score is $score", Toast.LENGTH_SHORT).show()
    }
}