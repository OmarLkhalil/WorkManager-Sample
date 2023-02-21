package com.douglasstarnes.backoffcriteria

// Define a singleton object to keep track of the number of retries for the background work
object WorkStatusSingleton {
    var workRetries: Int = 0
}
