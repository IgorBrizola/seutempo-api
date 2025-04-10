package br.com.seutempo.api.adapters.repository.jpa

import br.com.seutempo.api.adapters.repository.model.ReviewEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewsJpaRepository : JpaRepository<ReviewEntity, Int> {
    fun findByProfessionalId(id: Int): List<ReviewEntity>
}
