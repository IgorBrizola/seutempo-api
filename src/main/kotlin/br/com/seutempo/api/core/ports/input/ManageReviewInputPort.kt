package br.com.seutempo.api.core.ports.input

import br.com.seutempo.api.adapters.web.model.response.review.RankResponse
import br.com.seutempo.api.adapters.web.model.response.review.RatingResponse
import br.com.seutempo.api.core.domain.model.review.Reviews

interface ManageReviewInputPort {
    fun createReview(reviews: Reviews): Reviews

    fun findReviewByProfessionalId(id: Int): List<Reviews>

    fun findRatingByProfessionalId(id: Int): RatingResponse

    fun findRankByRatingProfessional(): List<RankResponse>
}
