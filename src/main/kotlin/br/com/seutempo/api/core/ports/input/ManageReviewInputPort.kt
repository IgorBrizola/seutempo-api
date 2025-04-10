package br.com.seutempo.api.core.ports.input

import br.com.seutempo.api.core.domain.model.review.Reviews

interface ManageReviewInputPort {
    fun createReview(reviews: Reviews): Reviews

    fun findReviewByProfessionalId(id: Int): List<Reviews>
}
