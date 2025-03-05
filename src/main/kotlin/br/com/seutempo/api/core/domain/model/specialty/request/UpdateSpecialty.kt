package br.com.seutempo.api.core.domain.model.specialty.request

data class UpdateSpecialty(
    val id: Int,
    val nameSpecialty: String?,
    val categoryId: Int?,
)
