package br.com.seutempo.api.repository.category

import br.com.seutempo.api.model.category.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Int>
