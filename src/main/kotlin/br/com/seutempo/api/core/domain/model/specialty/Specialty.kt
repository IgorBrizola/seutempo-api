package br.com.seutempo.api.core.domain.model.specialty

import br.com.seutempo.api.core.domain.model.category.Category
import br.com.seutempo.api.core.domain.model.specialty.response.ProfessionalSpecialtyResponse

data class Specialty(
    val id: Int?,
    val nameSpecialty: String,
    val category: Category,
    val professionals: MutableList<ProfessionalSpecialtyResponse>? = mutableListOf(),
)
