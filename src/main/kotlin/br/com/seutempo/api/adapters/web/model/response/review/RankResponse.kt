package br.com.seutempo.api.adapters.web.model.response.review

import br.com.seutempo.api.adapters.web.model.response.professional.ProfessionalResponse

data class RankResponse(
    val professional: ProfessionalResponse,
    val rating: Double,
    val totalReviews: Long,
)
