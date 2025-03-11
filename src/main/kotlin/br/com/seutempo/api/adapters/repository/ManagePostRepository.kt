package br.com.seutempo.api.adapters.repository

import br.com.seutempo.api.adapters.repository.jpa.post.PostJpaRepository
import br.com.seutempo.api.adapters.web.mapper.post.PostMapper
import br.com.seutempo.api.core.domain.model.posts.Posts
import br.com.seutempo.api.core.ports.output.ManagePostOutputPort
import org.springframework.stereotype.Repository

@Repository
class ManagePostRepository(
    private val postJpaRepository: PostJpaRepository,
    private val postMapper: PostMapper,
) : ManagePostOutputPort {
    override fun save(posts: Posts): Posts {
        val postEntity = postMapper.toEntity(posts)
        return postMapper.toDomain(postJpaRepository.save(postEntity))
    }

    override fun listAllPosts(): List<Posts> =
        postMapper.toListPosts(
            postJpaRepository.findAll(),
        )

    override fun findPostsByProfessionalId(id: Int): List<Posts> =
        postMapper.toListPosts(
            postJpaRepository.findPostsByProfessionalId(id),
        )
}
