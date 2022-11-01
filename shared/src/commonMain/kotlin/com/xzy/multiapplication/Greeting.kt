package com.xzy.multiapplication

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class Greeting {
    private val platform: Platform = getPlatform()
    private val baseUrl: String = "https://api.spacexdata.com/v4/launches"

    @Throws(Exception::class)
    suspend fun greeting(): String {
        val rockets: List<RocketLaunch> = httpClient.get(baseUrl).body()
        val lastSuccessLaunch: RocketLaunch = rockets.last { it.launchSuccess == true }

        return "Hello, ${platform.name.reversed()}!" +
                "\nThere are only ${daysUntilNewYear()} days left until New Year" +
                "\nThe last successful launch was ${lastSuccessLaunch.launchDateUTC}"
    }

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
}