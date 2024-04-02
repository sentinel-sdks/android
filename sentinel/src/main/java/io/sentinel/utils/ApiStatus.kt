package io.sentinel.utils

object ApiStatus {
    /**
     * various api status to know what happened if something goes wrong with a repository call
     */
    enum class ApiErrorStatus {
        /**
         * api in connecting to repository (Server or Database)
         */
        NO_CONNECTION,

        /**
         * api in getting value (Json Error, Server Error, etc)
         */
        BAD_RESPONSE,

        /**
         * Time out  api
         */
        TIMEOUT,

        /**
         * bad credentials
         */
        UNAUTHORIZED,

        /**
         * forbidden
         */
        FORBIDDEN,

        /**
         * no data available in repository
         */
        EMPTY_RESPONSE,

        /**
         * no data available in repository
         */
        BAD_ROUTE,

        /**
         * an unexpected api
         */
        NOT_DEFINED
    }

    /**
     * various api status depending on status code on header body
     */
    enum class ApiHeaderStatus {
        GENERAL_USER_ERROR,
        USER_BLOCKED,
        VERSION_BLOCKED,
        INTERNAL_SERVER_ERROR,
        AUTHENTICATION_EXPIRED,
        NOT_WHITELISTED,
    }
}
