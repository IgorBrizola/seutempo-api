package br.com.seutempo.api.adapters.web.doc

import br.com.seutempo.api.adapters.web.model.request.post.CreatePostRequest
import br.com.seutempo.api.adapters.web.model.response.post.PostResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "post controller")
interface PostsOpenAPI {
    @Operation(summary = "Create new posts")
    fun createPost(createPostRequest: CreatePostRequest): PostResponse

    @Operation(summary = "List all posts")
    fun listAllPosts(): List<PostResponse>
}
