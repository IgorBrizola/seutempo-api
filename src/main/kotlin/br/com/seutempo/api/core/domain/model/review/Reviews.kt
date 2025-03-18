package br.com.seutempo.api.core.domain.model.review

import br.com.seutempo.api.adapters.repository.model.ClientEntity
import br.com.seutempo.api.adapters.repository.model.ProfessionalEntity
import java.time.LocalDateTime

data class Reviews(
    val id: Int? = null,
    val client: ClientEntity,
    val idProfessional: ProfessionalEntity,
    val rating: Double,
    val comment: String?,
    val createdAt: LocalDateTime,
)
