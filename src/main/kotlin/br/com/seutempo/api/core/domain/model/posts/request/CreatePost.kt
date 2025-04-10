package br.com.seutempo.api.core.domain.model.posts.request

import br.com.seutempo.api.core.domain.model.professional.Professional

data class CreatePost(
    val title: String,
    val imgUrl: String,
    val professional: Professional,
)
