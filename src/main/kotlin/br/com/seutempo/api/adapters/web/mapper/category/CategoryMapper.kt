package br.com.seutempo.api.adapters.web.mapper.category

import br.com.seutempo.api.adapters.repository.model.CategoryEntity
import br.com.seutempo.api.adapters.web.model.request.category.NewCategoryRequest
import br.com.seutempo.api.core.domain.model.category.Category
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

    fun toCategoryEntity(category: Category): CategoryEntity

    @Mapping(source = "id", target = "categoryId")
    fun toDomain(categoryEntity: CategoryEntity): Category

    @Mapping(source = "newCategoryRequest.name", target = "nameCategory")
    fun categoryNewRequestToCategory(newCategoryRequest: NewCategoryRequest): CategoryEntity
}
