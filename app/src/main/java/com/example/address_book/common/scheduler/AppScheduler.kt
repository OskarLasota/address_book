package com.example.address_book.common.scheduler

import io.reactivex.Scheduler


interface AppScheduler {
    fun io() : Scheduler
    fun mainThread() : Scheduler
}