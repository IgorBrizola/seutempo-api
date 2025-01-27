package br.com.seutempo.api.service.category

import br.com.seutempo.api.repository.category.CategorySpecialtyRepository
import org.springframework.stereotype.Service

@Service
class CategorySpecialtyService(
    private val categorySpecialtyRepository: CategorySpecialtyRepository,
)
