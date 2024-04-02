package io.sentinel

import android.app.Application


class Sentinel {
    internal var accessId = ""
    internal var activeObservers = mutableListOf<String>()
    internal var zone = "DEFAULT"
    private lateinit var app: Application
    internal var inForeground = false

    companion object {
        fun init(id: String) =
            Sentinel()
                .let {
                    it.accessId = id
                    it.startTheWatch()
                    it
                }

        fun Sentinel.zone(zone: String) =
            this
                .let {
                    it.zone = zone
                    it
                }

        fun Sentinel.attach(application: Application) =
            this
                .let {
                    it.app = application
                    it
                }

        fun Sentinel.watch(vararg tags: String) =
            this
                .let {
                    it.activeObservers.clear()
                    it.activeObservers.addAll(tags)
                    it
                }
    }

    private fun logEvent(tag: String) {

    }

    private fun startTheWatch() {

    }
}