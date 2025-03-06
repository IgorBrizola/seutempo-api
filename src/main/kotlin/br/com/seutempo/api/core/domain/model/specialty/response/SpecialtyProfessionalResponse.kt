package br.com.seutempo.api.core.domain.model.specialty.response

data class SpecialtyProfessionalResponse(
    val nameSpecialty: String,
    val professionals: MutableList<ProfessionalSpecialtyResponse>? = mutableListOf(),
)
