package br.com.seutempo.api.adapters.repository

import br.com.seutempo.api.adapters.repository.model.CategoryEntity
import org.springframework.data.jpa.domain.Specification

object CategoryJpaSpecs {
    fun containsNameCategory(providedName: String): Specification<CategoryEntity> =
        Specification { root, _, criteriaBuilder ->
            criteriaBuilder.like(criteriaBuilder.lower(root.get("nameCategory")), "%${providedName.lowercase()}%")
        }
}
