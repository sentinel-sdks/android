package dsd.sentinel

import android.app.Application
import io.sentinel.Sentinel

class SentinelApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Sentinel.init("a330fe09eb95b0230aae2fbae43c7457")
    }
}