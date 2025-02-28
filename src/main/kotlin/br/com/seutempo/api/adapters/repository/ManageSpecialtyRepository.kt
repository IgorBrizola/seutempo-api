package br.com.seutempo.api.adapters.repository

import br.com.seutempo.api.adapters.repository.jpa.specialty.SpecialtyJpaRepository
import br.com.seutempo.api.adapters.repository.model.SpecialtyEntity
import br.com.seutempo.api.core.ports.output.ManageSpecialtyOutputPort

class ManageSpecialtyRepository(
    private val specialtyJpaRepository: SpecialtyJpaRepository,
) : ManageSpecialtyOutputPort {
    override fun existsByNameSpecialtyAndCategoryEntityId(
        nameSpecialty: String,
        categoryId: Int,
    ): Boolean = specialtyJpaRepository.existsByNameSpecialtyAndCategoryEntityId(nameSpecialty, categoryId)

    override fun findByProfessionalEntitiesId(id: Int?): List<SpecialtyEntity> = specialtyJpaRepository.findByProfessionalEntitiesId(id)
}
