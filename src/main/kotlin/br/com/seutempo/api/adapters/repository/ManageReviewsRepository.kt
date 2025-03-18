package br.com.seutempo.api.adapters.repository

import br.com.seutempo.api.adapters.repository.jpa.ReviewsJpaRepository
import br.com.seutempo.api.adapters.web.mapper.review.ReviewMapper
import br.com.seutempo.api.core.ports.output.ManageReviewsOutputPort
import org.springframework.stereotype.Repository

@Repository
class ManageReviewsRepository(
    private val reviewsJpaRepository: ReviewsJpaRepository,
    private val reviewMapper: ReviewMapper,
) : ManageReviewsOutputPort
