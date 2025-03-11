package br.com.seutempo.api.core.domain.model.posts.request

import br.com.seutempo.api.core.domain.model.professional.Professional
import java.time.LocalDateTime

data class CreatePost(
    val title: String,
    val imgUrl: String,
    val createdAt: LocalDateTime,
    val professional: Professional,
)
