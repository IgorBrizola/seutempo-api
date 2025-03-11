package br.com.seutempo.api.adapters.web

import br.com.seutempo.api.adapters.web.doc.PostsOpenAPI
import br.com.seutempo.api.adapters.web.mapper.post.PostMapper
import br.com.seutempo.api.adapters.web.model.request.post.CreatePostRequest
import br.com.seutempo.api.adapters.web.model.response.post.PostResponse
import br.com.seutempo.api.core.ports.input.ManagePostInputPort
import br.com.seutempo.api.core.ports.input.ManageProfessionalInputPort
import org.springframework.http.HttpStatus
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
}
