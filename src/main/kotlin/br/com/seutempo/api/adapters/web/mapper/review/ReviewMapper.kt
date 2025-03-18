package br.com.seutempo.api.adapters.web.mapper.review

import br.com.seutempo.api.adapters.repository.model.ReviewEntity
import br.com.seutempo.api.adapters.web.model.request.review.CreateReviewRequest
import br.com.seutempo.api.adapters.web.model.response.review.ReviewResponse
import br.com.seutempo.api.core.domain.model.review.Reviews
import br.com.seutempo.api.core.domain.model.review.request.CreateReview
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
)
interface ReviewMapper {
    fun toDomain(reviewEntity: ReviewEntity): Reviews

    fun toResponse(reviews: Reviews): ReviewResponse

    fun toListDomain(reviewEntity: List<ReviewEntity>): List<Reviews>

    fun toListResponse(reviews: List<Reviews>): List<ReviewResponse>

    fun toCreateReview(createReviewRequest: CreateReviewRequest): CreateReview
}
