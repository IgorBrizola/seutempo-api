package br.com.seutempo.api.adapters.web.mapper.category

import br.com.seutempo.api.adapters.repository.model.CategoryEntity
import br.com.seutempo.api.adapters.web.model.request.category.NewCategoryRequest
import br.com.seutempo.api.adapters.web.model.request.category.UpdateCategoryRequest
import br.com.seutempo.api.adapters.web.model.response.category.CategoryResponse
import br.com.seutempo.api.core.domain.model.category.Category
import br.com.seutempo.api.core.domain.model.category.request.UpdateCategory
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
)
interface CategoryMapper {
    fun categoryToCategoryNewRequest(categoryEntity: CategoryEntity): NewCategoryRequest

    @Mapping(source = "newCategoryRequest.name", target = "nameCategory")
    fun toCategoryDomain(newCategoryRequest: NewCategoryRequest): Category

    @Mapping(source = "categoryId", target = "id")
    fun toCategoryEntity(category: Category): CategoryEntity

    fun toListDomain(categoryEntity: List<CategoryEntity>): List<Category>

    @Mapping(source = "id", target = "categoryId")
    fun toDomain(categoryEntity: CategoryEntity): Category

    @Mapping(source = "newCategoryRequest.name", target = "nameCategory")
    fun categoryNewRequestToCategory(newCategoryRequest: NewCategoryRequest): CategoryEntity

    fun toResponse(category: Category): CategoryResponse

    fun toListResponse(category: List<Category>): List<CategoryResponse>

    fun toUpdate(
        categoryId: Int,
        updateCategoryRequest: UpdateCategoryRequest,
    ): UpdateCategory
}
