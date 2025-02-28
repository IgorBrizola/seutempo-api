package br.com.seutempo.api.core.domain.model

import br.com.seutempo.api.adapters.repository.model.TypeAddress
import br.com.seutempo.api.adapters.repository.model.UsersEntity
import org.locationtech.jts.geom.Point

data class Client(
    val id: Int,
    val user: UsersEntity,
    val surname: String,
    val cep: String,
    val state: String,
    val city: String,
    val neighborhood: String,
    val street: String,
    val number: Int,
    val complement: String,
    val additionalAddress: String,
    val typeAddress: TypeAddress,
    val latitude: Double,
    val longitude: Double,
    val location: Point,
)
