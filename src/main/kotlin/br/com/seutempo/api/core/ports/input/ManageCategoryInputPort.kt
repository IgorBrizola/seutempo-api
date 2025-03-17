package br.com.seutempo.api.core.ports.input

import br.com.seutempo.api.core.domain.model.category.Category

interface ManageCategoryInputPort {
    fun createNewCategory(newCategoryRequest: Category)

    fun findById(id: Int): Category

    fun listAllCategory(name: String?): List<Category>
}
