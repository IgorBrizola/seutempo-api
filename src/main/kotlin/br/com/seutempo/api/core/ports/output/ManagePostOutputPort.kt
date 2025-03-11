package br.com.seutempo.api.core.ports.output

import br.com.seutempo.api.core.domain.model.posts.Posts

interface ManagePostOutputPort {
    fun save(posts: Posts): Posts

    fun listAllPosts(): List<Posts>

    fun findPostsByProfessionalId(id: Int): List<Posts>

    fun findPostById(id: Int): Posts
}
