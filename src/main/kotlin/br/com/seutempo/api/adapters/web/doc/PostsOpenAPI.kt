package br.com.seutempo.api.adapters.web.doc

import br.com.seutempo.api.adapters.web.model.request.post.CreatePostRequest
import br.com.seutempo.api.adapters.web.model.request.post.UpdatePostRequest
import br.com.seutempo.api.adapters.web.model.response.post.PostResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "post controller")
interface PostsOpenAPI {
    @Operation(summary = "Create new posts")
    fun createPost(createPostRequest: CreatePostRequest): PostResponse

    @Operation(summary = "List all posts")
    fun listAllPosts(): List<PostResponse>

    @Operation(summary = "List posts by professional id")
    fun listPostsByProfessionalId(id: Int): List<PostResponse>

    @Operation(summary = "List posts by id")
    fun listPostById(id: Int): PostResponse

    @Operation(summary = "Update post by id")
    fun updatePostById(
        id: Int,
        updatePostRequest: UpdatePostRequest,
    ): PostResponse

    @Operation(summary = "Delete post by id")
    fun deletePostById(id: Int)
}
