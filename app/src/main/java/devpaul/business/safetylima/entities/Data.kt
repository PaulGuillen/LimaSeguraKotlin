package  devpaul.business.safetylima.entities

class Data(
    var status: String? = null,
    var totalResult: Int? = null,
    var articles: MutableList<Articles>
) {
    class Articles(
        var author: String? = null,
        var title: String? = null,
        var description: String? = null,
        var url: String? = null,
        var urlToImage: String? = null,
        var content: String? = null,
        var publishedAt: String? = null
    )
}