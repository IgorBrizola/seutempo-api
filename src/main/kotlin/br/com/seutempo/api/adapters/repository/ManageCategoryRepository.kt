package br.com.seutempo.api.adapters.repository

import br.com.seutempo.api.adapters.repository.jpa.category.CategoryJpaRepository
import br.com.seutempo.api.adapters.repository.model.CategoryEntity
import br.com.seutempo.api.core.ports.output.ManageCategoryOutputPort
import java.util.Optional

class ManageCategoryRepository(
    private val categoryJpaRepository: CategoryJpaRepository,
) : ManageCategoryOutputPort {
    override fun existsByNameCategory(nameCategory: String): Boolean = categoryJpaRepository.existsByNameCategory(nameCategory)

    override fun save(categoryEntity: CategoryEntity) = categoryJpaRepository.save(categoryEntity)

    override fun findById(id: Int): Optional<CategoryEntity> = categoryJpaRepository.findById(id)
}
