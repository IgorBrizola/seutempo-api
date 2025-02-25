package br.com.seutempo.api.adapters.repository

import br.com.seutempo.api.adapters.repository.jpa.category.CategoryJpaRepository
import br.com.seutempo.api.adapters.repository.model.CategoryEntity
import br.com.seutempo.api.core.ports.output.ManageCategoryOutputPort

class ManageCategoryRepository(
    private val categoryJpaRepository: CategoryJpaRepository,
) : ManageCategoryOutputPort {
    override fun existsByNameCategory(nameCategory: String): Boolean = categoryJpaRepository.existsByNameCategory(nameCategory)

    override fun save(categoryEntity: CategoryEntity) = categoryJpaRepository.save(categoryEntity)
}
