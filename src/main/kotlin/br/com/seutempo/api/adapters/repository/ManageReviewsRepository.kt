package br.com.seutempo.api.adapters.repository

import br.com.seutempo.api.adapters.repository.jpa.ReviewsJpaRepository
import br.com.seutempo.api.adapters.web.mapper.review.ReviewMapper
import br.com.seutempo.api.adapters.web.model.response.review.RatingResponse
import br.com.seutempo.api.core.domain.model.review.Reviews
import br.com.seutempo.api.core.ports.output.ManageReviewsOutputPort
import org.springframework.stereotype.Repository

@Repository
class ManageReviewsRepository(
    private val reviewsJpaRepository: ReviewsJpaRepository,
    private val reviewMapper: ReviewMapper,
) : ManageReviewsOutputPort {
    override fun save(reviews: Reviews): Reviews {
        val reviewEntity = reviewMapper.toEntity(reviews)
        return reviewMapper.toDomain(reviewsJpaRepository.save(reviewEntity))
    }

    override fun findByProfessionalId(id: Int): List<Reviews> {
        val reviewEntity = reviewsJpaRepository.findByProfessionalId(id)
        return reviewMapper.toListDomain(reviewEntity)
    }

    override fun findRatingMediaByProfessionalId(id: Int): RatingResponse = reviewsJpaRepository.findRatingMediaByProfessionalId(id)
}
