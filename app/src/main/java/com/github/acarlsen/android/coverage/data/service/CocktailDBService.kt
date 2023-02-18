package com.github.acarlsen.android.coverage.data.service

import com.github.acarlsen.android.coverage.data.models.DrinksDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Inject

interface CocktailDBService {
    suspend fun lookupRandomCocktail(): DrinksDto

    companion object {
        private const val TIME_OUT = 15000L
        private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1"
        const val RANDOM_COCKTAIL = "$BASE_URL/random.php"

        fun create(): CocktailDBService {
            return CocktailDBServiceImpl(
                client = HttpClient(Android) {
                    // Logging
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    // JSON
                    install(ContentNegotiation) {
                        json(
                            Json {
                                prettyPrint = true
                                isLenient = true
                                ignoreUnknownKeys = true
                            }
                        )
                    }
                    // Timeout
                    install(HttpTimeout) {
                        requestTimeoutMillis = TIME_OUT
                        connectTimeoutMillis = TIME_OUT
                        socketTimeoutMillis = TIME_OUT
                    }
                    // Apply to all requests
                    defaultRequest {
                        // Parameter("api_key", "some_api_key")
                        // Content Type
                        contentType(ContentType.Application.Json)
                        accept(ContentType.Application.Json)
                    }
                }
            )
        }
    }
}

class CocktailDBServiceImpl @Inject constructor(
    private val client: HttpClient
) : CocktailDBService {
    override suspend fun lookupRandomCocktail(): DrinksDto {
        return try {
            client.get(CocktailDBService.RANDOM_COCKTAIL).body()
        } catch (ex: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${ex.response.status.description}")
            throw ex
        } catch (ex: ClientRequestException) {
            // 4xx - responses
            println("Error: ${ex.response.status.description}")
            throw ex
        } catch (ex: ServerResponseException) {
            // 5xx - response
            println("Error: ${ex.response.status.description}")
            throw ex
        }
    }
}
