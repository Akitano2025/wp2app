package com.example.lanzou.network

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.call.*  // 添加导入以使用body()
import io.ktor.http.*
import org.jsoup.Jsoup
import com.example.lanzou.model.SoftwareItem

class WebParser {
    private val client = HttpClient(OkHttp) {
        defaultRequest {
            header(HttpHeaders.UserAgent, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
        }
    }

    suspend fun parseData(url: String): List<SoftwareItem> {
        val response = client.get(url)
        val html = response.body<String>()  // 正确获取响应内容字符串
        val document = Jsoup.parse(html)     // 明确使用字符串解析
        return document.select(".xe-card").map { element ->
            SoftwareItem(
                name = element.select("strong").text(),
                description = element.select("p.overflowClip_1").text(),
                iconUrl = element.select("img").attr("data-src"),
                downloadUrl = element.select("a").attr("href")
            )
        }
    }
}