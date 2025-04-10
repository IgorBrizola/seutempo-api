package br.com.seutempo.api.adapters.repository.jpa

import br.com.seutempo.api.adapters.repository.model.ReviewEntity
import br.com.seutempo.api.adapters.web.model.response.review.RatingResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

private const val RATING_RESPONSE_RESPONSE = "br.com.seutempo.api.adapters.web.model.response.review.RatingResponse"

interface ReviewsJpaRepository : JpaRepository<ReviewEntity, Int> {
    fun findByProfessionalId(id: Int): List<ReviewEntity>

    @Query(
        """
    SELECT new $RATING_RESPONSE_RESPONSE(
       COALESCE(1.0 * AVG(r.rating), 0.0),
       COALESCE(COUNT(r.id), 0)
    )
    FROM ReviewEntity r
    WHERE r.professional.id = :professionalId
""",
    )
    fun findRatingMediaByProfessionalId(
        @Param("professionalId") id: Int,
    ): RatingResponse
}
