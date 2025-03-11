package br.com.seutempo.api.adapters.web.model.request.post

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class CreatePostRequest(
    val title: String,
    val imgUrl: String,
    @Schema(hidden = true)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val idProfessional: Int,
)
