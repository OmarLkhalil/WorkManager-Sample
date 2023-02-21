package com.douglasstarnes.backoffcriteria

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

// Class representing a Worker that fails its work
class WorkerFail(context: Context, params: WorkerParameters): Worker(context, params) {
    override fun doWork(): Result {
        return Result.failure() // Fails the work by returning a failure result
    }
}
