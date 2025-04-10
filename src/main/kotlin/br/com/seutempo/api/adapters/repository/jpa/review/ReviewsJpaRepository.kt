package br.com.seutempo.api.adapters.repository.jpa.review

import br.com.seutempo.api.adapters.repository.model.ReviewEntity
import br.com.seutempo.api.adapters.web.model.response.review.RatingResponse
import br.com.seutempo.api.adapters.web.model.response.review.SimpleRankResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

private const val RATING_RESPONSE = "br.com.seutempo.api.adapters.web.model.response.review.RatingResponse"
private const val RANK_RESPONSE = "br.com.seutempo.api.adapters.web.model.response.review.SimpleRankResponse"

interface ReviewsJpaRepository : JpaRepository<ReviewEntity, Int> {
    fun findByProfessionalId(id: Int): List<ReviewEntity>

    @Query(
        """
    SELECT new $RATING_RESPONSE(
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

    @Query(
        """
    SELECT new $RANK_RESPONSE(
        r.professional.id,
        AVG(r.rating)
    )
    FROM ReviewEntity r
    GROUP BY r.professional.id
    HAVING COUNT(r.id) > 0
    ORDER BY AVG(r.rating) DESC
""",
    )
    fun findRankByProfessional(): List<SimpleRankResponse>
}
