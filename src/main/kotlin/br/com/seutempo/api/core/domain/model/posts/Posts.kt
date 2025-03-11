package br.com.seutempo.api.core.domain.model.posts

import br.com.seutempo.api.core.domain.model.professional.Professional
import java.time.LocalDateTime

data class Posts(
    val id: Int?,
    val title: String,
    val imgUrl: String,
    val createdAt: LocalDateTime,
    val professional: Professional,
)
