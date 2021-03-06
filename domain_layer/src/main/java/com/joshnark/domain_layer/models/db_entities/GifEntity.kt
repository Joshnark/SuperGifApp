package com.joshnark.domain_layer.models.db_entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.joshnark.domain_layer.models.Gif
import com.joshnark.domain_layer.models.db_entities.GifEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class GifEntity(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("embed_url")
    val embedUrl: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("source_tld")
    val source: String?,

    val cachedUrl: String?,
    val isLiked: Boolean
) {

    fun toGif(): Gif {
        return Gif(
            id = this.id,
            embedUrl = this.embedUrl,
            title = this.title,
            isLiked = this.isLiked,
            cachedUrl = this.cachedUrl,
            username = this.username,
            source = this.source
        )
    }

    companion object {
        fun fromGif(gif: Gif): GifEntity {
            return GifEntity(
                id = gif.id,
                embedUrl = gif.embedUrl,
                title = gif.title,
                isLiked = gif.isLiked,
                cachedUrl = gif.images?.original?.url,
                username = gif.username,
                source = gif.source
            )
        }

        internal const val TABLE_NAME= "saved_gifs"
    }
}
