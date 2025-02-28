package br.com.seutempo.api.core.ports.input

import br.com.seutempo.api.adapters.integration.model.response.Geometry
import br.com.seutempo.api.adapters.web.model.response.users.UsersResponse
import org.locationtech.jts.geom.Point

interface ManageUsersInputPort {
    fun getUsers(): List<UsersResponse>

    fun findUserById(id: Int): UsersResponse

    fun convertLocationGeo(address: String): Geometry

    fun convertGeometryPoint(geometry: Geometry): Point
}
