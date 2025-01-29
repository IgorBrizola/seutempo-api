package br.com.seutempo.api.mapper.category

import br.com.seutempo.api.model.category.Category
import br.com.seutempo.api.model.category.request.CategoryNewRequest
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
)
interface CategoryMapper {
    fun categoryToCategoryNewRequest(category: Category): CategoryNewRequest

    @Mapping(source = "categoryNewRequest.name", target = "nameCategory")
    fun categoryNewRequestToCategory(categoryNewRequest: CategoryNewRequest): Category
}
