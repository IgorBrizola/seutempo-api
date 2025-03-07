package br.com.seutempo.api.adapters.repository.jpa.specialty

import br.com.seutempo.api.adapters.repository.model.SpecialtyEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SpecialtyJpaRepository : JpaRepository<SpecialtyEntity, Int> {
    fun existsByNameSpecialtyAndCategoryEntityId(
        nameSpecialty: String,
        categoryId: Int,
    ): Boolean
}
