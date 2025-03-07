package br.com.seutempo.api.adapters.repository.jpa.category

import br.com.seutempo.api.adapters.repository.model.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryJpaRepository : JpaRepository<CategoryEntity, Int> {
    fun existsByNameCategory(nameCategory: String): Boolean
}
