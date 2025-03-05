package br.com.seutempo.api.core.ports.output

import br.com.seutempo.api.adapters.repository.model.CategoryEntity
import br.com.seutempo.api.core.domain.model.Category
import java.util.Optional

interface ManageCategoryOutputPort {
    fun existsByNameCategory(nameCategory: String): Boolean

    fun save(category: Category)

    fun findById(id: Int): Optional<CategoryEntity>
}
