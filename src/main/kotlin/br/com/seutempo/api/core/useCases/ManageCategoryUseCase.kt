package br.com.seutempo.api.core.useCases

import br.com.seutempo.api.core.domain.exceptions.ResourceAlreadyExistsException
import br.com.seutempo.api.core.domain.model.category.Category
import br.com.seutempo.api.core.ports.input.ManageCategoryInputPort
import br.com.seutempo.api.core.ports.output.ManageCategoryOutputPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ManageCategoryUseCase(
    private val categoryJpaRepository: ManageCategoryOutputPort,
) : ManageCategoryInputPort {
    @Transactional
    override fun createNewCategory(newCategoryRequest: Category) {
        if (categoryJpaRepository.existsByNameCategory(
                newCategoryRequest.nameCategory,
            )
        ) {
            throw ResourceAlreadyExistsException("Category (${newCategoryRequest.nameCategory}) already exist")
        }
        categoryJpaRepository.save(newCategoryRequest)
    }

    override fun findById(id: Int): Category = categoryJpaRepository.findById(id)

    override fun listAllCategory(name: String?): List<Category> = categoryJpaRepository.listAllCategory(name)
}
