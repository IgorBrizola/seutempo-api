package br.com.seutempo.api.adapters.web

import br.com.seutempo.api.adapters.web.doc.ReviewOpenAPI
import br.com.seutempo.api.adapters.web.mapper.review.ReviewMapper
import br.com.seutempo.api.adapters.web.model.request.review.CreateReviewRequest
import br.com.seutempo.api.adapters.web.model.response.review.ReviewResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("reviews")
class RestReviewsController(
    private val reviewMapper: ReviewMapper,
) : ReviewOpenAPI {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun createReview(createReviewRequest: CreateReviewRequest): ReviewResponse {
        TODO("Not yet implemented")
    }
}
