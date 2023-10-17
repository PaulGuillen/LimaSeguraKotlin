package devpaul.business.safetylima.data.models.entity


data class ArticleNews(
    val source: Source? = null,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
)

data class Source(
    val id: String? = null,
    val name: String? = null
)

data class News(
    val status: String? = null,
    val totalResults: Int? = null,
    val articles: List<ArticleNews> = listOf(),
)