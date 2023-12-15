package android.template.core.network.remote

import android.template.core.network.MyModelDataSource
import android.template.core.network.model.NetworkMyModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Inject

class MyModelNetwork @Inject constructor(
    networkJson: Json,
) : MyModelDataSource {
    private val httpClient = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.BODY
        }
        install(DefaultRequest) {
            url("https://api.genderize.io/")
            contentType(ContentType.Application.Json)
        }
        install(ContentNegotiation) {
            json(networkJson)
        }
    }

    override suspend fun getMyModel(name: String): String {
        return httpClient.get("") {
            parameter("name", name)
        }
            .body<NetworkMyModel>()
            .name
    }

}