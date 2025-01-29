package br.com.seutempo.api.service.category

import br.com.seutempo.api.mapper.category.CategoryMapper
import br.com.seutempo.api.model.category.request.CategoryNewRequest
import br.com.seutempo.api.model.exception.category.CategoryAlreadyExistsException
import br.com.seutempo.api.repository.category.CategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
    private val categoryMapper: CategoryMapper,
) {
    @Transactional
    fun createNewCategory(categoryRequestNew: CategoryNewRequest) {
        if (categoryRepository.existsByNameCategory(
                categoryRequestNew.name,
            )
        ) {
            throw CategoryAlreadyExistsException("Category (${categoryRequestNew.name}) already exist")
        }

        val category = categoryMapper.categoryNewRequestToCategory(categoryRequestNew)
        categoryRepository.save(category)
    }
}
