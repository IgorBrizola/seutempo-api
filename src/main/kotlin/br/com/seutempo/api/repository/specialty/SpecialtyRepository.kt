package br.com.seutempo.api.repository.specialty

import br.com.seutempo.api.model.specialty.Specialty
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SpecialtyRepository : JpaRepository<Specialty, Int> {
    fun existsByNameSpecialtyAndCategoryId(
        nameSpecialty: String,
        categoryId: Int,
    ): Boolean

    fun findByProfessionalsId(id: Int?): List<Specialty>

    fun findByCategoryId(id: Int?): List<Specialty>
}
