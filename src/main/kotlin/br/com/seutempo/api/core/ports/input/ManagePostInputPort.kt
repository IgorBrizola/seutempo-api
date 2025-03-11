package br.com.seutempo.api.core.ports.input

import br.com.seutempo.api.core.domain.model.posts.Posts
import br.com.seutempo.api.core.domain.model.professional.Professional

interface ManagePostInputPort {
    fun createPost(post: Posts): Posts

    fun listAllPosts(): List<Posts>

    fun listPostsByProfessionalId(professional: Professional): List<Posts>
}
