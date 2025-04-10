package br.com.seutempo.api.core.domain.model.review

import br.com.seutempo.api.core.domain.model.client.Client
import br.com.seutempo.api.core.domain.model.professional.Professional

data class Reviews(
    val id: Int?,
    val client: Client,
    val professional: Professional,
    val rating: Double,
    val comment: String?,
)
