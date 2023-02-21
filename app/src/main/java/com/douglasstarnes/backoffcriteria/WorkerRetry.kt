package com.douglasstarnes.backoffcriteria

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

// Define a WorkerRetry class that extends the WorkManager Worker class
class WorkerRetry(context: Context, params: WorkerParameters): Worker(context, params) {

    // Override the doWork method to define the background work that this Worker should perform
    override fun doWork(): Result {

        // Log the current number of retries to the console
        println("Still working: ${WorkStatusSingleton.workRetries}")

        // If the number of retries is less than 3, retry the work and increment the retry count
        return if (WorkStatusSingleton.workRetries < 3) {
            WorkStatusSingleton.workRetries += 1
            Result.retry()
        }
        // If the number of retries is greater than or equal to 3, mark the work as successful
        else {
            Result.success()
        }
    }
}