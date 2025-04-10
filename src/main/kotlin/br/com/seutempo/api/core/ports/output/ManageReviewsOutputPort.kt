package br.com.seutempo.api.core.ports.output

import br.com.seutempo.api.core.domain.model.review.Reviews

interface ManageReviewsOutputPort {
    fun save(reviews: Reviews): Reviews
}
