package br.com.seutempo.api.adapters.web.model.request.review

data class CreateReviewRequest(
    val idClient: Int,
    val idProfessional: Int,
    val rating: Double,
    val comment: String?,
)
