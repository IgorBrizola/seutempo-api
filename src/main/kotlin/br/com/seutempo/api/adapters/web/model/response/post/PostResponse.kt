package br.com.seutempo.api.adapters.web.model.response.post

import java.time.LocalDateTime

data class PostResponse(
    val id: Int?,
    val title: String,
    val imgUrl: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val idProfessional: Int,
)
