package br.com.seutempo.api.core.ports.input

import br.com.seutempo.api.adapters.web.model.request.category.NewCategoryRequest

interface ManageCategoryInputPort {
    fun createNewCategory(categoryRequestNew: NewCategoryRequest)
}
