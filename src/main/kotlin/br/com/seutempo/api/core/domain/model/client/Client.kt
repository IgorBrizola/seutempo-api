package br.com.seutempo.api.core.domain.model.client

import br.com.seutempo.api.adapters.repository.model.TypeAddress
import br.com.seutempo.api.core.domain.model.users.Users
import org.locationtech.jts.geom.Point

data class Client(
    val id: Int?,
    val user: Users,
    val surname: String? = null,
    val cep: String,
    val state: String?,
    val city: String?,
    val neighborhood: String,
    val street: String,
    val number: Int,
    val complement: String? = null,
    val formatedAddress: String? = null,
    val additionalAddress: String? = null,
    val typeAddress: TypeAddress,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val location: Point? = null,
)
