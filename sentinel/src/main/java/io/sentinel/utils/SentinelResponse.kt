package io.sentinel.utils

import com.google.gson.annotations.SerializedName

data class SentinelResponse<T>(
    @SerializedName("header") var header: Header?,
    @SerializedName("body") var body: T?,
    @Transient var error: ApiError? = null
) {
    constructor(header: Header?, body: T?) : this(header, body, null)

    constructor(apiError: ApiError?) : this(null, null, apiError)

    data class Header(
        @SerializedName("requestId") var requestId: String?,
        @SerializedName("message") var customerMessage: String?,
        @SerializedName("code") var code: Int?,
        @SerializedName("successful") var successful: Boolean?,
        @SerializedName("timestamp") var timestamp: String?
    )
}

