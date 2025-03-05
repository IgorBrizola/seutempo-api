package br.com.seutempo.api.core.ports.output

import br.com.seutempo.api.adapters.repository.model.SpecialtyEntity
import java.util.Optional

interface ManageSpecialtyOutputPort {
    fun existsByNameSpecialtyAndCategoryEntityId(
        nameSpecialty: String,
        categoryId: Int,
    ): Boolean

    fun findByProfessionalEntitiesId(id: Int?): List<SpecialtyEntity>

    fun save(specialtyEntity: SpecialtyEntity): SpecialtyEntity

    fun findById(id: Int): Optional<SpecialtyEntity>

    fun findAllById(ids: List<Int>): List<SpecialtyEntity>

    fun findAll(): List<SpecialtyEntity>

    fun deleteById(id: Int)
}
