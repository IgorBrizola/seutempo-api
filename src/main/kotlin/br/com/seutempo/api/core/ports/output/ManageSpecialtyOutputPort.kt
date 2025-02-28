package br.com.seutempo.api.core.ports.output

import br.com.seutempo.api.adapters.repository.model.SpecialtyEntity

interface ManageSpecialtyOutputPort {
    fun existsByNameSpecialtyAndCategoryEntityId(
        nameSpecialty: String,
        categoryId: Int,
    ): Boolean

    fun findByProfessionalEntitiesId(id: Int?): List<SpecialtyEntity>
}
