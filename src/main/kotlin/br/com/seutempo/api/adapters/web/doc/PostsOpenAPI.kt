package br.com.seutempo.api.adapters.web.doc

import br.com.seutempo.api.adapters.web.model.request.post.CreatePostRequest
import br.com.seutempo.api.adapters.web.model.response.post.PostResponse
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "post controller")
interface PostsOpenAPI {
    fun createPost(createPostRequest: CreatePostRequest): PostResponse
}
