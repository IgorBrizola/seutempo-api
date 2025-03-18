package br.com.seutempo.api.adapters.repository

import br.com.seutempo.api.adapters.repository.jpa.category.CategoryJpaRepository
import br.com.seutempo.api.adapters.repository.model.CategoryEntity
import br.com.seutempo.api.adapters.web.mapper.category.CategoryMapper
import br.com.seutempo.api.core.domain.exceptions.ResourceNotFoundException
import br.com.seutempo.api.core.domain.model.category.Category
import br.com.seutempo.api.core.ports.output.ManageCategoryOutputPort
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Repository

@Repository
class ManageCategoryRepository(
    private val categoryJpaRepository: CategoryJpaRepository,
    private val categoryMapper: CategoryMapper,
) : ManageCategoryOutputPort {
    override fun existsByNameCategory(nameCategory: String): Boolean = categoryJpaRepository.existsByNameCategory(nameCategory)

    override fun save(category: Category): Category {
        val categoryEntity = categoryMapper.toCategoryEntity(category)
        return categoryMapper.toDomain(categoryJpaRepository.save(categoryEntity))
    }

    override fun findById(id: Int): Category =
        categoryMapper.toDomain(
            categoryJpaRepository.findById(id).orElseThrow {
                throw ResourceNotFoundException("Category not found! - $id")
            },
        )

    override fun listAllCategory(name: String?): List<Category> {
        val spec = buildSpecFilter(name)

        return categoryMapper.toListDomain(categoryJpaRepository.findAll(spec))
    }

    private fun buildSpecFilter(name: String?): Specification<CategoryEntity> {
        var spec: Specification<CategoryEntity> = Specification.where(null)

        name?.let {
            spec = spec.and(CategoryJpaSpecs.containsNameCategory(name))
        }
        return spec
    }
}
