package br.com.seutempo.api.adapters.repository.jpa.post

import br.com.seutempo.api.adapters.repository.model.PostsEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PostJpaRepository : JpaRepository<PostsEntity, Int> {
    fun findPostsByProfessionalId(id: Int): List<PostsEntity>
}
