package io.sentinel.data.services

import io.sentinel.data.models.HTTPLogRequest
import io.sentinel.data.models.UILogRequest
import io.sentinel.data.models.UILogResponse
import io.sentinel.utils.Endpoints
import io.sentinel.utils.SentinelResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GatewayService {
    @POST(Endpoints.HTTP.report)
    suspend fun reportHttpLog(@Body log: UILogRequest): Response<SentinelResponse<UILogResponse>>

    @GET(Endpoints.UI.report)
    suspend fun getFarmerProfile(@Body log: HTTPLogRequest): Response<SentinelResponse<UILogRequest>>
}