package br.com.seutempo.api.core.ports.output

import br.com.seutempo.api.core.domain.model.posts.Posts

interface ManagePostOutputPort {
    fun save(posts: Posts): Posts
}
