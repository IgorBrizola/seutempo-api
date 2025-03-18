package br.com.seutempo.api.adapters.web.doc

import br.com.seutempo.api.adapters.web.model.request.category.NewCategoryRequest
import br.com.seutempo.api.adapters.web.model.request.category.UpdateCategoryRequest
import br.com.seutempo.api.adapters.web.model.response.category.CategoryResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "category controller")
interface CategoryOpenAPI {
    @Operation(summary = "Create new category")
    fun createCategory(newCategoryRequest: NewCategoryRequest)

    @Operation(summary = "List all category")
    fun listAllCategory(name: String?): List<CategoryResponse>

    @Operation(summary = "Find category by id")
    fun findCategoryById(id: Int): CategoryResponse

    @Operation(summary = "Update category by id")
    fun updateCategoryById(
        categoryId: Int,
        updateCategoryRequest: UpdateCategoryRequest,
    ): CategoryResponse

    @Operation(summary = "Delete category by id", method = "@GetMapping")
    fun deleteByCategoryById(id: Int)
}
