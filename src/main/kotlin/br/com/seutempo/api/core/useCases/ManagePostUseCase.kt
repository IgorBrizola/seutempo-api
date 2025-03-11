package br.com.seutempo.api.core.useCases

import br.com.seutempo.api.core.domain.model.posts.Posts
import br.com.seutempo.api.core.domain.model.posts.request.UpdatePost
import br.com.seutempo.api.core.domain.model.professional.Professional
import br.com.seutempo.api.core.ports.input.ManagePostInputPort
import br.com.seutempo.api.core.ports.output.ManagePostOutputPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ManagePostUseCase(
    private val postJpaRepository: ManagePostOutputPort,
) : ManagePostInputPort {
    @Transactional
    override fun createPost(post: Posts): Posts = postJpaRepository.save(post)

    override fun listAllPosts(): List<Posts> = postJpaRepository.listAllPosts()

    override fun listPostsByProfessionalId(professional: Professional): List<Posts> =
        postJpaRepository.findPostsByProfessionalId(professional.id!!)

    override fun listPostById(id: Int): Posts = postJpaRepository.findPostById(id)

    @Transactional
    override fun updatePost(updatePost: UpdatePost): Posts {
        val post = postJpaRepository.findPostById(updatePost.id)

        val postUpdate =
            post.copy(
                title = updatePost.title ?: post.title,
                imgUrl = updatePost.imgUrl ?: post.imgUrl,
            )

        return postJpaRepository.save(postUpdate)
    }

    @Transactional
    override fun deletePostById(id: Int) {
        postJpaRepository.findPostById(id)

        postJpaRepository.deletePostById(id)
    }
}
