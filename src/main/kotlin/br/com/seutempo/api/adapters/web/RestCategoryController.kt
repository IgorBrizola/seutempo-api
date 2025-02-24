package br.com.seutempo.api.adapters.web

import br.com.seutempo.api.adapters.web.model.request.category.NewCategoryRequest
import br.com.seutempo.api.core.useCases.ManageCategoryUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("category")
class RestCategoryController(
    private val manageCategoryUseCase: ManageCategoryUseCase,
) {
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun createCategory(
        @RequestBody categoryRequestNew: NewCategoryRequest,
    ) = manageCategoryUseCase.createNewCategory(categoryRequestNew)
}

// TODO: configure mapper to controller category
