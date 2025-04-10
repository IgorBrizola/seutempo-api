package br.com.seutempo.api.adapters.web

import br.com.seutempo.api.adapters.web.doc.ReviewOpenAPI
import br.com.seutempo.api.adapters.web.mapper.review.ReviewMapper
import br.com.seutempo.api.adapters.web.model.request.review.CreateReviewRequest
import br.com.seutempo.api.adapters.web.model.response.review.ReviewResponse
import br.com.seutempo.api.core.ports.input.ManageClientInputPort
import br.com.seutempo.api.core.ports.input.ManageProfessionalInputPort
import br.com.seutempo.api.core.ports.input.ManageReviewInputPort
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("reviews")
class RestReviewsController(
    private val reviewMapper: ReviewMapper,
    private val reviewUseCase: ManageReviewInputPort,
    private val professionalUseCase: ManageProfessionalInputPort,
    private val clientUseCase: ManageClientInputPort,
) : ReviewOpenAPI {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun createReview(
        @RequestBody createReviewRequest: CreateReviewRequest,
    ): ReviewResponse {
        val professional = professionalUseCase.findProfessionalById(createReviewRequest.idProfessional)

        val client = clientUseCase.findClientById(createReviewRequest.idClient)

        val createReview =
            reviewMapper.toCreateReview(
                professional = professional,
                client = client,
                rating = createReviewRequest.rating,
                comment = createReviewRequest.comment,
            )

        val review = reviewMapper.createReviewToReview(createReview)

        return reviewMapper.toResponse(reviewUseCase.createReview(review))
    }
}
