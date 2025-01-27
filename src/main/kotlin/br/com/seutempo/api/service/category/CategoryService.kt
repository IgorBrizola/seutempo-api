package br.com.seutempo.api.service.category

import br.com.seutempo.api.repository.category.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
)
