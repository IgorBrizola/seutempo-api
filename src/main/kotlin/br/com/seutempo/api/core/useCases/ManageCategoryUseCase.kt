package br.com.seutempo.api.core.useCases

import br.com.seutempo.api.adapters.repository.jpa.category.CategoryJpaRepository
import br.com.seutempo.api.adapters.web.mapper.category.CategoryMapper
import br.com.seutempo.api.adapters.web.model.request.category.NewCategoryRequest
import br.com.seutempo.api.core.domain.exceptions.ResourceAlreadyExistsException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ManageCategoryUseCase(
    private val categoryJpaRepository: CategoryJpaRepository,
    private val categoryMapper: CategoryMapper,
) {
    @Transactional
    fun createNewCategory(categoryRequestNew: NewCategoryRequest) {
        if (categoryJpaRepository.existsByNameCategory(
                categoryRequestNew.name,
            )
        ) {
            throw ResourceAlreadyExistsException("Category (${categoryRequestNew.name}) already exist")
        }

        val category = categoryMapper.categoryNewRequestToCategory(categoryRequestNew)
        categoryJpaRepository.save(category)
    }
}
