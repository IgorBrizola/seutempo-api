package br.com.seutempo.api.adapters.repository

import br.com.seutempo.api.adapters.repository.jpa.specialty.SpecialtyJpaRepository
import br.com.seutempo.api.adapters.repository.model.SpecialtyEntity
import br.com.seutempo.api.core.ports.output.ManageSpecialtyOutputPort
import java.util.Optional

class ManageSpecialtyRepository(
    private val specialtyJpaRepository: SpecialtyJpaRepository,
) : ManageSpecialtyOutputPort {
    override fun existsByNameSpecialtyAndCategoryEntityId(
        nameSpecialty: String,
        categoryId: Int,
    ): Boolean = specialtyJpaRepository.existsByNameSpecialtyAndCategoryEntityId(nameSpecialty, categoryId)

    override fun findByProfessionalEntitiesId(id: Int?): List<SpecialtyEntity> = specialtyJpaRepository.findByProfessionalEntitiesId(id)

    override fun save(specialtyEntity: SpecialtyEntity): SpecialtyEntity = specialtyJpaRepository.save(specialtyEntity)

    override fun findById(id: Int): Optional<SpecialtyEntity> = specialtyJpaRepository.findById(id)

    override fun findAllById(ids: List<Int>): List<SpecialtyEntity> = specialtyJpaRepository.findAllById(ids)

    override fun findAll(): List<SpecialtyEntity> = specialtyJpaRepository.findAll()

    override fun deleteById(id: Int) = specialtyJpaRepository.deleteById(id)
}
