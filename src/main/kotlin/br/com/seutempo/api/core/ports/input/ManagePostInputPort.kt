package br.com.seutempo.api.core.ports.input

import br.com.seutempo.api.core.domain.model.posts.Posts

interface ManagePostInputPort {
    fun createPost(post: Posts): Posts

    fun listAllPosts(): List<Posts>
}
