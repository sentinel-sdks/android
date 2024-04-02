package io.sentinel.utils

data class ApiError(
    val message: String?,
    val code: Int?,
    @Transient var apiErrorStatus: ApiStatus.ApiErrorStatus?,
    @Transient var apiHeaderStatus: ApiStatus.ApiHeaderStatus?
) {
    constructor(apiErrorStatus: ApiStatus.ApiErrorStatus) : this(null, null, apiErrorStatus, null)

    constructor(apiHeaderStatus: ApiStatus.ApiHeaderStatus) : this(
        null,
        null,
        null,
        apiHeaderStatus
    )
}
