package br.com.seutempo.api.adapters.web

import br.com.seutempo.api.adapters.web.doc.CategoryOpenAPI
import br.com.seutempo.api.adapters.web.mapper.category.CategoryMapper
import br.com.seutempo.api.adapters.web.model.request.category.NewCategoryRequest
import br.com.seutempo.api.core.ports.input.ManageCategoryInputPort
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("category")
class RestCategoryController(
    private val manageCategoryUseCase: ManageCategoryInputPort,
    private val categoryMapper: CategoryMapper,
) : CategoryOpenAPI {
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    override fun createCategory(
        @RequestBody newCategoryRequest: NewCategoryRequest,
    ) {
        val category = categoryMapper.toCategoryDomain(newCategoryRequest)

        manageCategoryUseCase.createNewCategory(category)
    }
}
