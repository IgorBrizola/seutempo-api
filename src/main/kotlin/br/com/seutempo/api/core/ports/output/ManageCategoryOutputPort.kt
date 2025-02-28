package br.com.seutempo.api.core.ports.output

import br.com.seutempo.api.adapters.repository.model.CategoryEntity
import java.util.Optional

interface ManageCategoryOutputPort {
    fun existsByNameCategory(nameCategory: String): Boolean

    fun save(categoryEntity: CategoryEntity): CategoryEntity

    fun findById(id: Int): Optional<CategoryEntity>
}
