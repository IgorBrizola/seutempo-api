package br.com.seutempo.api.controller.category

import br.com.seutempo.api.service.category.CategoryService
import br.com.seutempo.api.service.category.CategorySpecialtyService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("category")
class CategoryController(
    private val categoryService: CategoryService,
    private val categorySpecialty: CategorySpecialtyService,
)
