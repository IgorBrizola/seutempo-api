package br.com.seutempo.api.adapters.web.doc

import br.com.seutempo.api.adapters.web.model.request.review.CreateReviewRequest
import br.com.seutempo.api.adapters.web.model.response.review.ReviewResponse
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "review controller")
interface ReviewOpenAPI {
    fun createReview(createReviewRequest: CreateReviewRequest): ReviewResponse
}
