package br.com.seutempo.api.adapters.web.mapper.category

import br.com.seutempo.api.adapters.repository.model.CategoryEntity
import br.com.seutempo.api.adapters.web.model.request.category.NewCategoryRequest
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
    fun categoryNewRequestToCategory(newCategoryRequest: NewCategoryRequest): CategoryEntity
}
