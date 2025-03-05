package br.com.seutempo.api.core.domain.model.specialty.request

data class CreateSpecialty(
    val nameSpecialty: String,
    val categoryId: Int,
)
