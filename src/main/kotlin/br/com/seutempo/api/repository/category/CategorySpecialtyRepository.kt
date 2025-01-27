package br.com.seutempo.api.repository.category

import br.com.seutempo.api.model.category.CategorySpecialty
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategorySpecialtyRepository : JpaRepository<CategorySpecialty, Int>
