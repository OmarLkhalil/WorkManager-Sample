package com.douglasstarnes.backoffcriteria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    // Create an instance of WorkManager to manage the background work
    private val workManager = WorkManager.getInstance(this)


    private lateinit var btnWorkerFail: Button
    private lateinit var btnWorkerRetry: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnWorkerFail = findViewById(R.id.btnWorkerFail)
        btnWorkerRetry = findViewById(R.id.btnWorkerRetry)

        // Set click listeners for the buttons to create and enqueue WorkRequests
        btnWorkerFail.setOnClickListener {

            // Create a OneTimeWorkRequest for the WorkerFail class and enqueue it to WorkManager
            val workRequest = OneTimeWorkRequestBuilder<WorkerFail>().build()
            workManager.enqueue(workRequest)
        }

        btnWorkerRetry.setOnClickListener {

            // Create a OneTimeWorkRequest for the WorkerRetry class and enqueue it to WorkManager with a linear backoff
            val workRequest = OneTimeWorkRequestBuilder<WorkerRetry>()
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    10,
                    TimeUnit.SECONDS
                ).build()
            workManager.enqueue(workRequest)
        }
    }
}