package br.com.seutempo.api.adapters.web.mapper.post

import br.com.seutempo.api.adapters.repository.model.PostsEntity
import br.com.seutempo.api.adapters.web.mapper.professional.ProfessionalMapper
import br.com.seutempo.api.adapters.web.mapper.specialty.SpecialtyMapper
import br.com.seutempo.api.adapters.web.model.request.post.CreatePostRequest
import br.com.seutempo.api.adapters.web.model.request.post.UpdatePostRequest
import br.com.seutempo.api.adapters.web.model.response.post.PostResponse
import br.com.seutempo.api.core.domain.model.posts.Posts
import br.com.seutempo.api.core.domain.model.posts.request.CreatePost
import br.com.seutempo.api.core.domain.model.posts.request.UpdatePost
import br.com.seutempo.api.core.domain.model.professional.Professional
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [ProfessionalMapper::class, SpecialtyMapper::class],
)
interface PostMapper {
    fun toDomain(postsEntity: PostsEntity): Posts

    fun toEntity(posts: Posts): PostsEntity

    @Mapping(source = "professional", target = "professional")
    @Mapping(source = "createPostRequest.title", target = "title")
    @Mapping(source = "createPostRequest.imgUrl", target = "imgUrl")
    fun requestToCreate(
        createPostRequest: CreatePostRequest,
        professional: Professional,
    ): CreatePost

    fun createToPost(createPost: CreatePost): Posts

    fun toResponse(posts: Posts): PostResponse

    fun toListPosts(postsEntity: List<PostsEntity>): List<Posts>

    fun toListResponse(posts: List<Posts>): List<PostResponse>

    fun requestToUpdate(
        id: Int,
        updatePostRequest: UpdatePostRequest,
    ): UpdatePost
}
