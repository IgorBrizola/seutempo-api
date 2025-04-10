package br.com.seutempo.api.core.useCases

import br.com.seutempo.api.adapters.web.model.response.review.RatingResponse
import br.com.seutempo.api.core.domain.exceptions.BusinessException
import br.com.seutempo.api.core.domain.model.review.Reviews
import br.com.seutempo.api.core.ports.input.ManageReviewInputPort
import br.com.seutempo.api.core.ports.output.ManageReviewsOutputPort
import org.springframework.stereotype.Service

@Service
class ManageReviewUseCase(
    private val reviewJpaRepository: ManageReviewsOutputPort,
) : ManageReviewInputPort {
    override fun createReview(reviews: Reviews): Reviews =
        if (reviews.rating <= 5) reviewJpaRepository.save(reviews) else throw BusinessException("Avaliação não pode ser maior que 5")

    override fun findReviewByProfessionalId(id: Int): List<Reviews> = reviewJpaRepository.findByProfessionalId(id)

    override fun findRatingByProfessionalId(id: Int): RatingResponse = reviewJpaRepository.findRatingMediaByProfessionalId(id)
}
