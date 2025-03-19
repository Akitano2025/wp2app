package com.example.lanzou.network
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.useragent.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import org.jsoup.Jsoup
import java.net.URLEncoder
class WebParser {
    private val client = HttpClient()

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
