package br.com.seutempo.api.core.ports.output

import br.com.seutempo.api.adapters.web.model.response.review.RankResponse
import br.com.seutempo.api.adapters.web.model.response.review.RatingResponse
import br.com.seutempo.api.core.domain.model.review.Reviews

interface ManageReviewsOutputPort {
    fun save(reviews: Reviews): Reviews

    fun findByProfessionalId(id: Int): List<Reviews>

    fun findRatingMediaByProfessionalId(id: Int): RatingResponse

    fun findRankByRatingProfessional(): List<RankResponse>
}
