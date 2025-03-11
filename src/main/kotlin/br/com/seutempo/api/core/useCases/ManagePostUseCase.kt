package br.com.seutempo.api.core.useCases

import br.com.seutempo.api.core.domain.model.posts.Posts
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
}
