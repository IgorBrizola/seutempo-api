package br.com.seutempo.api.adapters.web.mapper.review

import br.com.seutempo.api.adapters.repository.model.ReviewEntity
import br.com.seutempo.api.adapters.web.mapper.client.ClientMapper
import br.com.seutempo.api.adapters.web.mapper.professional.ProfessionalMapper
import br.com.seutempo.api.adapters.web.mapper.specialty.SpecialtyMapper
import br.com.seutempo.api.adapters.web.model.response.review.ReviewResponse
import br.com.seutempo.api.core.domain.model.client.Client
import br.com.seutempo.api.core.domain.model.professional.Professional
import br.com.seutempo.api.core.domain.model.review.Reviews
import br.com.seutempo.api.core.domain.model.review.request.CreateReview
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [ProfessionalMapper::class, ClientMapper::class, SpecialtyMapper::class],
)
interface ReviewMapper {
    fun toDomain(reviewEntity: ReviewEntity): Reviews

    fun toEntity(reviews: Reviews): ReviewEntity

    fun toResponse(reviews: Reviews): ReviewResponse

    fun toListResponse(reviews: List<Reviews>): List<ReviewResponse>

    @Mapping(source = "professional", target = "professional")
    @Mapping(source = "client", target = "client")
    fun toCreateReview(
        professional: Professional,
        client: Client,
        rating: Double,
        comment: String?,
    ): CreateReview

    fun createReviewToReview(createReview: CreateReview): Reviews
}
