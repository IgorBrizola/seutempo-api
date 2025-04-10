package br.com.seutempo.api.core.domain.model.review.request

import br.com.seutempo.api.core.domain.model.client.Client
import br.com.seutempo.api.core.domain.model.professional.Professional

data class CreateReview(
    val client: Client,
    val professional: Professional,
    val rating: Double,
    val comment: String?,
)
