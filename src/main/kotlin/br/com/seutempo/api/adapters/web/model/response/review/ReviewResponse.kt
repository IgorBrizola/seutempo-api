package br.com.seutempo.api.adapters.web.model.response.review

import br.com.seutempo.api.adapters.web.model.response.client.ClientResponse
import br.com.seutempo.api.adapters.web.model.response.professional.ProfessionalResponse

data class ReviewResponse(
    val id: Int,
    val client: ClientResponse,
    val professional: ProfessionalResponse,
    val rating: Double,
    val comment: String?,
)
