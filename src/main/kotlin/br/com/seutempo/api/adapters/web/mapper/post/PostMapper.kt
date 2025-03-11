package br.com.seutempo.api.adapters.web.mapper.post

import br.com.seutempo.api.adapters.repository.model.PostsEntity
import br.com.seutempo.api.adapters.repository.model.SpecialtyEntity
import br.com.seutempo.api.adapters.web.model.request.post.CreatePostRequest
import br.com.seutempo.api.adapters.web.model.response.post.PostResponse
import br.com.seutempo.api.adapters.web.model.response.specialty.SpecialtyResponse
import br.com.seutempo.api.core.domain.model.posts.Posts
import br.com.seutempo.api.core.domain.model.posts.request.CreatePost
import br.com.seutempo.api.core.domain.model.professional.Professional
import br.com.seutempo.api.core.domain.model.specialty.Specialty
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
)
interface PostMapper {
    @Mapping(source = "category", target = "categoryEntity")
    @Mapping(source = "category.categoryId", target = "categoryEntity.id")
    fun specialtyToSpecialtyEntity(specialty: Specialty): SpecialtyEntity

    @Mapping(source = "categoryEntity", target = "category")
    @Mapping(source = "categoryEntity.id", target = "category.categoryId")
    fun specialtyToSpecialtyEntity(specialty: SpecialtyEntity): Specialty

    @Mapping(source = "categoryEntity.nameCategory", target = "nameCategory")
    fun toSpecialtyResponse(specialties: SpecialtyEntity): SpecialtyResponse

    fun toDomain(postsEntity: PostsEntity): Posts

    fun toEntity(posts: Posts): PostsEntity

    @Mapping(source = "professional", target = "professional")
    @Mapping(source = "createPostRequest.title", target = "title")
    @Mapping(source = "createPostRequest.imgUrl", target = "imgUrl")
    @Mapping(source = "createPostRequest.createdAt", target = "createdAt")
    fun requestToCreate(
        createPostRequest: CreatePostRequest,
        professional: Professional,
    ): CreatePost

    fun createToPost(createPost: CreatePost): Posts

    fun toResponse(posts: Posts): PostResponse

    fun toListPosts(postsEntity: List<PostsEntity>): List<Posts>

    fun toListResponse(posts: List<Posts>): List<PostResponse>
}
