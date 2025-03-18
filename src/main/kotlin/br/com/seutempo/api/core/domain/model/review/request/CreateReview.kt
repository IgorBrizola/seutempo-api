package br.com.seutempo.api.core.domain.model.review.request

data class CreateReview(
    val idClient: Int,
    val idProfessional: Int,
    val rating: Double,
    val comment: String?,
)
