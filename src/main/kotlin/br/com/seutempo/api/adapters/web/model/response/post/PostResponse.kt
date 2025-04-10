package br.com.seutempo.api.adapters.web.model.response.post

import br.com.seutempo.api.adapters.web.model.response.professional.ProfessionalResponse

data class PostResponse(
    val id: Int?,
    val title: String,
    val imgUrl: String,
    val professional: ProfessionalResponse,
)
