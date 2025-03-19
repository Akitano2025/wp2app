package com.example.lanzou.network

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import org.jsoup.Jsoup
import com.example.lanzou.model.SoftwareItem

class WebParser {
    private val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            header(HttpHeaders.UserAgent, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
        }
    }

    suspend fun parseData(url: String): List<SoftwareItem> {
        val html = client.get(url).bodyAsText()
        return Jsoup.parse(html).select(".xe-card").map {
            SoftwareItem(
                name = it.select("strong").text(),
                description = it.select("p.overflowClip_1").text(),
                iconUrl = it.select("img").attr("data-src"),
                downloadUrl = it.select("a").attr("href")
            )
        }
    }
}