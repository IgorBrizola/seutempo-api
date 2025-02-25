package br.com.seutempo.api.core.ports.output

import br.com.seutempo.api.adapters.repository.model.CategoryEntity

interface ManageCategoryOutputPort {
    fun existsByNameCategory(nameCategory: String): Boolean

    fun save(categoryEntity: CategoryEntity): CategoryEntity
}
