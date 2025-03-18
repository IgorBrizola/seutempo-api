package br.com.seutempo.api.core.ports.output

import br.com.seutempo.api.core.domain.model.category.Category

interface ManageCategoryOutputPort {
    fun existsByNameCategory(nameCategory: String): Boolean

    fun save(category: Category): Category

    fun findById(id: Int): Category

    fun listAllCategory(name: String?): List<Category>
}
