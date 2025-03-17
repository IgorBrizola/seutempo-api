package br.com.seutempo.api.adapters.web.doc

import br.com.seutempo.api.adapters.web.model.request.category.NewCategoryRequest
import br.com.seutempo.api.adapters.web.model.response.category.CategoryResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "category controller")
interface CategoryOpenAPI {
    @Operation(summary = "Create new category")
    fun createCategory(newCategoryRequest: NewCategoryRequest)

    @Operation(summary = "List all category")
    fun listAllCategory(): List<CategoryResponse>

    @Operation(summary = "Find category by id")
    fun findCategoryById(id: Int): CategoryResponse
}
