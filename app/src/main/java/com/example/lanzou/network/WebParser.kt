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
