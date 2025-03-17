package br.com.seutempo.api.core.ports.output

import br.com.seutempo.api.adapters.repository.model.CategoryEntity
import br.com.seutempo.api.core.domain.model.category.Category

interface ManageCategoryOutputPort {
    fun existsByNameCategory(nameCategory: String): Boolean

    fun save(category: Category): CategoryEntity

    fun findById(id: Int): Category

    fun listAllCategory(): List<Category>
}
