package br.com.seutempo.api.core.ports.output

import br.com.seutempo.api.adapters.repository.model.SpecialtyEntity
import br.com.seutempo.api.core.domain.model.specialty.Specialty
import br.com.seutempo.api.core.domain.model.specialty.request.UpdateSpecialty

interface ManageSpecialtyOutputPort {
    fun existsByNameSpecialtyAndCategoryEntityId(
        nameSpecialty: String,
        categoryId: Int,
    ): Boolean

    fun findSpecialtyWithProfessional(id: Int): Specialty

    fun save(specialty: Specialty): SpecialtyEntity

    fun findById(id: Int): Specialty

    fun findAllById(ids: List<Int>): List<Specialty>

    fun findAll(): List<Specialty>

    fun deleteById(id: Int)

    fun updateSpecialty(
        specialty: Specialty,
        updateSpecialty: UpdateSpecialty,
    ): Specialty
}
