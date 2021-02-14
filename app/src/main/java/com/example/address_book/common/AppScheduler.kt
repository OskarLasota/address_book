package com.example.address_book.common

import io.reactivex.Scheduler


interface AppScheduler {
    fun io() : Scheduler
    fun mainThread() : Scheduler
}