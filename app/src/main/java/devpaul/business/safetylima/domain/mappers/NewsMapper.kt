package devpaul.business.safetylima.domain.mappers

import devpaul.business.safetylima.data.models.entity.ArticleNews
import devpaul.business.safetylima.data.models.entity.News
import devpaul.business.safetylima.data.models.entity.Source
import devpaul.business.safetylima.data.models.response.ArticleNewsResponse
import devpaul.business.safetylima.data.models.response.NewsResponse
import devpaul.business.safetylima.data.models.response.SourceResponse

class NewsMapper {
    fun map(newsResponse: NewsResponse): News {
        val articles = newsResponse.articles.map { mapArticleNews(it) }

        return News(
            status = newsResponse.status,
            totalResults = newsResponse.totalResults,
            articles = articles
        )
    }

    private fun mapArticleNews(articleResponse: ArticleNewsResponse): ArticleNews {
        return ArticleNews(
            source = mapSource(articleResponse.source),
            author = articleResponse.author,
            title = articleResponse.title,
            description = articleResponse.articleDescription,
            url = articleResponse.url,
            urlToImage = articleResponse.imageUrl,
            publishedAt = articleResponse.publishDate,
            content = articleResponse.content
        )
    }

    private fun mapSource(sourceResponse: SourceResponse): Source {
        return Source(
            id = sourceResponse.id,
            name = sourceResponse.name
        )
    }
}
