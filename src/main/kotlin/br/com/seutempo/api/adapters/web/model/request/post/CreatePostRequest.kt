package br.com.seutempo.api.adapters.web.model.request.post

data class CreatePostRequest(
    val title: String,
    val imgUrl: String,
    val idProfessional: Int,
)
