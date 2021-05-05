package ru.hh.ftplayground

import android.app.Application
import ru.hh.ftplayground.di.DI


class FTPApp : Application() {

    override fun onCreate() {
        super.onCreate()

        DI.init(this)
    }

}