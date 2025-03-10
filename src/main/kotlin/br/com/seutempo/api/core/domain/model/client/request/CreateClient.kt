package br.com.seutempo.api.core.domain.model.client.request

import br.com.seutempo.api.adapters.repository.model.TypeAddress
import br.com.seutempo.api.adapters.repository.model.TypeUser
import br.com.seutempo.api.core.domain.model.users.Users
import org.locationtech.jts.geom.Point
import java.time.LocalDateTime

data class CreateClient(
    val user: Users,
    val surname: String? = null,
    val cep: String,
    val state: String? = null,
    val city: String? = null,
    val neighborhood: String? = null,
    val street: String? = null,
    val number: Int? = null,
    val complement: String? = null,
    val additionalAddress: String? = null,
    val typeAddress: TypeAddress,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val location: Point? = null,
    val typeUser: TypeUser = TypeUser.CLIENT,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val active: Boolean = true,
)
