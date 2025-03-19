package com.example.lanzou.network
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.useragent.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import org.jsoup.Jsoup
import java.net.URLEncoder
import com.squareup.okhttp3.OkHttpClient  // 需要添加okhttp依赖
import com.example.lanzou.model.SoftwareItem  // 需要创建对应的数据类
import com.example.lanzou.util.useragent  // 需要确认useragent的来源
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
