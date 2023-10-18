package devpaul.business.safetylima.domain.util

import devpaul.business.safetylima.BuildConfig.DEBUG
import timber.log.Timber

object TimberFactory {
    fun setupOnDebug() {
        Timber.uprootAll()
        if (DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}