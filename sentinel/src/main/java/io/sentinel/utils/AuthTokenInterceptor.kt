package dsd.agri.sense.data

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import dsd.agri.sense.data.models.auth.AccessTokenResponse
import dsd.agri.sense.data.remoteConfig.RemoteConfig
import dsd.agri.sense.data.sharedPref.PreferencesHelper
import dsd.agri.sense.data.utils.Constants
import dsd.agri.sense.data.utils.numberCodeAdder
import dsd.agri.sense.data.BuildConfig
import java.io.IOException
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit

class AuthTokenInterceptor(
    private val interceptor: HttpLoggingInterceptor,
    private val preferencesHelper: PreferencesHelper,
    private val config: RemoteConfig
) : Interceptor {
    private val tokenTimeOut: Long = 5
    override fun intercept(chain: Interceptor.Chain): Response {
        var currentToken: String? = preferencesHelper.accessToken
        val currentTokenExpiry: Long? = preferencesHelper.accessTokenExpiry
        var mainRequest = chain.request()
        val now = Date().time / 1000
        try {
            if (currentToken.isNullOrEmpty()) {
                currentToken = saveToken()
            } else if (currentTokenExpiry!! < now) {
                currentToken = saveToken()
            }
        } catch (e: Exception) {
            Timber.d("Error getting token ${e.message}")
        }

        mainRequest = mainRequest.newBuilder()
            .addHeader(
                Constants.AUTHORIZATION, Constants.BEARER_AUTH + currentToken
            )
            .build()
        return chain.proceed(mainRequest)
    }

    /**
     * Save the token and expiry time to preferences
     */
    private fun saveToken(): String {
        val tokenObject = refreshToken()
        return if (tokenObject != null) {
            preferencesHelper.accessTokenExpiry = addSecondsToCurrentTime(tokenObject.expiresIn!!)
            preferencesHelper.accessToken = tokenObject.code.orEmpty()
            tokenObject.code.orEmpty()
        } else {
            ""
        }
    }

    /**
     * Add the expiry seconds to the current time and return the object as unix epoc
     */
    private fun addSecondsToCurrentTime(seconds: Int): Long {
        val now = Calendar.getInstance()
        now.add(Calendar.SECOND, seconds)
        return now.time.time / 1000
    }

    @Throws(IOException::class, Exception::class)
    private fun refreshToken(): AccessTokenResponse? {
        val tokenRequest = Request.Builder()
            .url(BuildConfig.GATEWAY_URL + GatewayEndpoints.Auth.refresh)
            .addHeader(
                Constants.AUTHORIZATION,
                "${Constants.BEARER_AUTH}${preferencesHelper.accessToken}"
            )
            .addHeader(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
            .addHeader(Constants.ACCEPT, "*/*")
            .addHeader(Constants.SOURCE_APPLICATION, Constants.SOURCE_SYSTEM_VALUE)
            .addHeader(Constants.SOURCE_KEY, BuildConfig.GATEWAY_TOKEN)
            .addHeader(Constants.SOURCE_SYSTEM, Constants.SOURCE_SYSTEM_VALUE)
            .addHeader(Constants.SOURCE_TYPE, Constants.SOURCE_SYSTEM_VALUE)
            .addHeader(Constants.VERSION, BuildConfig.VERSION_NAME)
            .addHeader(Constants.MESSAGE_ID, Constants.messageId)
            .addHeader(Constants.GATEWAY_KEY, BuildConfig.GATEWAY_TOKEN)
            .addHeader(Constants.X_MSISDN, numberCodeAdder(preferencesHelper.msisdn))
            .get()
            .build()

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(tokenTimeOut, TimeUnit.SECONDS)
            .readTimeout(tokenTimeOut, TimeUnit.SECONDS)
            .build()
        val tokenResponse = client.newCall(tokenRequest).execute()
        return if (tokenResponse.isSuccessful) {
            val responseString = tokenResponse.body?.string().orEmpty()
            val tokenObject = Gson().fromJson(
                Gson().toJsonTree(responseString).asJsonObject["body"],
                AccessTokenResponse::class.java
            )
            tokenObject
        } else {
            null
        }
    }
}
