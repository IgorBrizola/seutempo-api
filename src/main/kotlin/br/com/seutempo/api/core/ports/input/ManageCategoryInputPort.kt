package br.com.seutempo.api.core.ports.input

import br.com.seutempo.api.core.domain.model.category.Category
import br.com.seutempo.api.core.domain.model.category.request.UpdateCategory

interface ManageCategoryInputPort {
    fun createNewCategory(newCategoryRequest: Category)

    fun findById(id: Int): Category

    fun listAllCategory(name: String?): List<Category>

    fun updateCategory(updateCategory: UpdateCategory): Category

    fun deleteCategoryById(id: Int)
}
