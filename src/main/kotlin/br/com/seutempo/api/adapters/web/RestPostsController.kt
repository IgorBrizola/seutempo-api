package br.com.seutempo.api.adapters.web

import br.com.seutempo.api.adapters.web.doc.PostsOpenAPI
import br.com.seutempo.api.adapters.web.mapper.post.PostMapper
import br.com.seutempo.api.adapters.web.model.request.post.CreatePostRequest
import br.com.seutempo.api.adapters.web.model.request.post.UpdatePostRequest
import br.com.seutempo.api.adapters.web.model.response.post.PostResponse
import br.com.seutempo.api.core.ports.input.ManagePostInputPort
import br.com.seutempo.api.core.ports.input.ManageProfessionalInputPort
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("posts")
class RestPostsController(
    private val postMapper: PostMapper,
    private val postUseCase: ManagePostInputPort,
    private val professionalUseCase: ManageProfessionalInputPort,
) : PostsOpenAPI {
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    override fun createPost(
        @RequestBody createPostRequest: CreatePostRequest,
    ): PostResponse {
        val professional = professionalUseCase.findProfessionalById(createPostRequest.idProfessional)

        val createPost = postMapper.requestToCreate(createPostRequest, professional)

        val postWithProfessional =
            createPost.copy(
                professional = professional,
            )

        val post = postMapper.createToPost(postWithProfessional)

        return postMapper.toResponse(postUseCase.createPost(post))
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    override fun listAllPosts(): List<PostResponse> =
        postMapper.toListResponse(
            postUseCase.listAllPosts(),
        )

    @GetMapping("professional/{id}")
    @ResponseStatus(HttpStatus.OK)
    override fun listPostsByProfessionalId(
        @PathVariable id: Int,
    ): List<PostResponse> {
        val professional = professionalUseCase.findProfessionalById(id)

        return postMapper.toListResponse(
            postUseCase.listPostsByProfessionalId(professional),
        )
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    override fun listPostById(
        @PathVariable id: Int,
    ): PostResponse =
        postMapper.toResponse(
            postUseCase.listPostById(id),
        )

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    override fun updatePostById(
        @PathVariable id: Int,
        @RequestBody updatePostRequest: UpdatePostRequest,
    ): PostResponse {
        val updatePost = postMapper.requestToUpdate(id, updatePostRequest)

        return postMapper.toResponse(postUseCase.updatePost(updatePost))
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun deletePostById(
        @PathVariable id: Int,
    ) = postUseCase.deletePostById(id)
}
