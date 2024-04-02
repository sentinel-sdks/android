package io.sentinel.utils

import java.util.UUID

object Constants {
    const val IMMEDIATE = "immediate"
    const val AUTHORIZATION = "Authorization"
    const val CONTENT_TYPE = "Content-Type"
    const val ACCEPT = "Accept"
    const val SOURCE_KEY = "X-Source-Key"
    const val SOURCE_SYSTEM = "X-Source-System"
    const val SOURCE_APPLICATION = "X-Source-Application"
    const val SOURCE_TYPE = "X-Source-Type"
    const val VERSION = "X-Source-Version"
    const val MESSAGE_ID = "X-MessageID"
    const val X_MSISDN = "X-MSISDN"
    const val GATEWAY_KEY = "x-api-key"
    const val BEARER_AUTH = "Bearer "
    const val BASIC_AUTH = "Basic"
    const val MSISDN_SERVICE_IDENTIFIER = "MSISDN"
    const val FEEDBACK_COLLECTION = "feedback"
    const val SIM_PROVIDER_CODE = "63902"
    const val CMS = "cms"
    const val PREFERENCES = "connected_chama"
    const val XYZ = "dsd"
    const val APPLICATION_JSON = "application/json"
    const val AUTH_CONTENT_TYPE = "application/x-www-form-urlencoded"
    const val HASHED_MSISDN = "HASHED_MSISDN"
    const val SOURCE_SYSTEM_VALUE = "dsd-fe-cargo-watch-android"
    const val DEVICE_ID = "X-DeviceId"
    const val DEVICE_TOKEN = "X-DeviceToken"

    object ResponseCodes {
        const val SUCCESS = 200
        const val NOT_FOUND = 404
        const val SYSTEM_ERROR = 500
        const val SYSTEM_FAILURE = 812
        const val IMEI_BLACKLISTED_PERMANENTLY = 613
        const val MSISDN_BLACKLISTED_TEMPORARILY = 616
        const val APP_VERSION_BLACKLISTED = 617
        const val NOT_WHITELISTED = 601
    }

    val conversationId: String
        get() = UUID.randomUUID().toString()
    val messageId: String
        get() = UUID.randomUUID().toString()
    val randomDeviceToken: String
        get() = UUID.randomUUID().toString()
}

fun numberCodeAdder(_number: String?): String {
    var number = _number
    return try {
        number =
            if (number!!.length > 9) "254" + number.substring(number.length - 9) else "254$number"
        number
    } catch (e: Exception) {
        e.printStackTrace()
        number.toString()
    }
}