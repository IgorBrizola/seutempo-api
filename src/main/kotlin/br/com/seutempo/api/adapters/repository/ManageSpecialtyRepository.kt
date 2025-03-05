package br.com.seutempo.api.adapters.repository

import br.com.seutempo.api.adapters.repository.jpa.specialty.SpecialtyJpaRepository
import br.com.seutempo.api.adapters.repository.model.SpecialtyEntity
import br.com.seutempo.api.adapters.web.mapper.specialty.SpecialtyMapper
import br.com.seutempo.api.core.domain.exceptions.ResourceNotFoundException
import br.com.seutempo.api.core.domain.model.specialty.Specialty
import br.com.seutempo.api.core.ports.output.ManageSpecialtyOutputPort

class ManageSpecialtyRepository(
    private val specialtyJpaRepository: SpecialtyJpaRepository,
    private val specialtyMapper: SpecialtyMapper,
) : ManageSpecialtyOutputPort {
    override fun existsByNameSpecialtyAndCategoryEntityId(
        nameSpecialty: String,
        categoryId: Int,
    ): Boolean = specialtyJpaRepository.existsByNameSpecialtyAndCategoryEntityId(nameSpecialty, categoryId)

    override fun findByProfessionalEntitiesId(id: Int?): List<Specialty> =
        specialtyMapper.toListSpecialty(specialtyJpaRepository.findByProfessionalEntitiesId(id))

    override fun save(specialty: Specialty): SpecialtyEntity = specialtyJpaRepository.save(specialtyMapper.toEntity(specialty))

    override fun findById(id: Int): Specialty {
        val specialty = specialtyJpaRepository.findById(id).orElseThrow { throw ResourceNotFoundException("Specialty not found! - $id") }
        return specialtyMapper.toSpecialty(specialty)
    }

    override fun findAllById(ids: List<Int>): List<Specialty> = specialtyMapper.toListSpecialty(specialtyJpaRepository.findAllById(ids))

    override fun findAll(): List<Specialty> = specialtyMapper.toListSpecialty(specialtyJpaRepository.findAll())

    override fun deleteById(id: Int) {
        val specialty = specialtyJpaRepository.findById(id).orElseThrow { throw ResourceNotFoundException("Specialty not found! - $id") }
        specialty.professionalEntities?.forEach { it.specialties.remove(specialty) }
        specialtyJpaRepository.deleteById(id)
    }
}
