package project.tronku.newst.Models

import com.google.gson.annotations.SerializedName

data class NewsModel(

    @SerializedName("title")
    val headline: String,

    @SerializedName("content")
    val summary: String?,

    @SerializedName("source")
    val source: Source,

    @SerializedName("urlToImage")
    val imgUrl: String,

    @SerializedName("url")
    val newsUrl: String,

    @SerializedName("publishedAt")
    val publishTime: String,

    @SerializedName("description")
    val description: String,

    val bookmarked: Boolean)


data class Source(

    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String)
