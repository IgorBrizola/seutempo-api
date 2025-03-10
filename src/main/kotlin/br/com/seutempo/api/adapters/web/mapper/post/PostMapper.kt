package br.com.seutempo.api.adapters.web.mapper.post

import br.com.seutempo.api.adapters.repository.model.PostsEntity
import br.com.seutempo.api.adapters.web.model.request.post.CreatePostRequest
import br.com.seutempo.api.adapters.web.model.response.post.PostResponse
import br.com.seutempo.api.core.domain.model.posts.Posts
import br.com.seutempo.api.core.domain.model.posts.request.CreatePost
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
)
interface PostMapper {
    fun toDomain(postsEntity: PostsEntity): Posts

    fun toEntity(posts: Posts): PostsEntity

    fun requestToCreate(createPostRequest: CreatePostRequest): CreatePost

    fun createToPost(createPost: CreatePost): Posts

    fun toResponse(posts: Posts): PostResponse
}
