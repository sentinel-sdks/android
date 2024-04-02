package io.sentinel.utils

object Endpoints {
    object Headers {
        const val authorization = "Authorization"
        const val xSessionId = "X-Session-Id"
        const val xAccessId = "X-Access-Id"
    }

    object Queries {
        const val uiTag = "ui-tag"
        const val uiTagValue = "ui-tag-value"
    }

    object HTTP {
        const val report = "v1/tracking/http"
    }

    object UI {
        const val report = "v1/tracking/http"
    }
}