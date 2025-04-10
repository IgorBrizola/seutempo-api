package br.com.seutempo.api.adapters.web.doc

import br.com.seutempo.api.adapters.web.model.request.review.CreateReviewRequest
import br.com.seutempo.api.adapters.web.model.response.review.RankResponse
import br.com.seutempo.api.adapters.web.model.response.review.RatingResponse
import br.com.seutempo.api.adapters.web.model.response.review.ReviewResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "review controller")
interface ReviewOpenAPI {
    @Operation(summary = "create new review professional")
    fun createReview(createReviewRequest: CreateReviewRequest): ReviewResponse

    @Operation(summary = "find reviews by professional id")
    fun findReviewByProfessionalId(id: Int): List<ReviewResponse>

    @Operation(summary = "Find rating by professional id")
    fun findRatingByProfessionalId(id: Int): RatingResponse

    @Operation(summary = "Find ranking professionals")
    fun findRankByRatingProfessional(): List<RankResponse>
}
