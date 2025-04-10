package br.com.seutempo.api.core.useCases

import br.com.seutempo.api.core.domain.model.review.Reviews
import br.com.seutempo.api.core.ports.input.ManageReviewInputPort
import br.com.seutempo.api.core.ports.output.ManageReviewsOutputPort
import org.springframework.stereotype.Service

@Service
class ManageReviewUseCase(
    private val reviewJpaRepository: ManageReviewsOutputPort,
) : ManageReviewInputPort {
    override fun createReview(reviews: Reviews): Reviews = reviewJpaRepository.save(reviews)
}
