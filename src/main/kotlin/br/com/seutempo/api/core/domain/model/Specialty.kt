package br.com.seutempo.api.core.domain.model

import br.com.seutempo.api.adapters.repository.model.CategoryEntity
import br.com.seutempo.api.adapters.repository.model.ProfessionalEntity

data class Specialty(
    val id: Int,
    val nameSpecialty: String,
    val categoryEntity: CategoryEntity,
    val professionalEntities: MutableList<ProfessionalEntity>,
)
