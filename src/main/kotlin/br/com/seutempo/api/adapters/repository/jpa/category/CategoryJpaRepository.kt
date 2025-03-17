package br.com.seutempo.api.adapters.repository.jpa.category

import br.com.seutempo.api.adapters.repository.model.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface CategoryJpaRepository :
    JpaRepository<CategoryEntity, Int>,
    JpaSpecificationExecutor<CategoryEntity> {
    fun existsByNameCategory(nameCategory: String): Boolean
}
