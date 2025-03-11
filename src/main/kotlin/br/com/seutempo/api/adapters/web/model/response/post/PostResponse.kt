package br.com.seutempo.api.adapters.web.model.response.post

import br.com.seutempo.api.adapters.web.model.response.professional.ProfessionalResponse
import java.time.LocalDateTime

data class PostResponse(
    val id: Int?,
    val title: String,
    val imgUrl: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val professional: ProfessionalResponse,
)
