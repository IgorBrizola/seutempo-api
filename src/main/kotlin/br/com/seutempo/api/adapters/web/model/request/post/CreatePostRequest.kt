package br.com.seutempo.api.adapters.web.model.request.post

import java.time.LocalDateTime

data class CreatePostRequest(
    val title: String,
    val imgUrl: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val idProfessional: Int,
)
