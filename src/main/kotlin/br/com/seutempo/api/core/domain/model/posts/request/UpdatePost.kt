package br.com.seutempo.api.core.domain.model.posts.request

data class UpdatePost(
    val id: Int,
    val title: String?,
    val imgUrl: String?,
)
