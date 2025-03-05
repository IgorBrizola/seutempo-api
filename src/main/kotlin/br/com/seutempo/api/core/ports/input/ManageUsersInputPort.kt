package br.com.seutempo.api.core.ports.input

import br.com.seutempo.api.adapters.integration.model.response.Geometry
import br.com.seutempo.api.core.domain.model.users.Users
import org.locationtech.jts.geom.Point

interface ManageUsersInputPort {
    fun getUsers(): List<Users>

    fun findUserById(id: Int): Users

    fun convertLocationGeo(address: String): Geometry

    fun convertGeometryPoint(geometry: Geometry): Point
}
