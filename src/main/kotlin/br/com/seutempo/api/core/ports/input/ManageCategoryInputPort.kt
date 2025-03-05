package br.com.seutempo.api.core.ports.input

import br.com.seutempo.api.core.domain.model.Category

interface ManageCategoryInputPort {
    fun createNewCategory(newCategoryRequest: Category)
}
