package br.com.seutempo.api.core.ports.input

import br.com.seutempo.api.core.domain.model.googleMaps.response.Geometry
import br.com.seutempo.api.core.domain.model.users.Users
import br.com.seutempo.api.core.domain.model.users.request.UpdatePasswordInput
import org.locationtech.jts.geom.Point

interface ManageUsersInputPort {
    fun getUsers(): List<Users>

    fun findUserById(id: Int): Users

    fun convertLocationGeo(address: String): Geometry

    fun convertGeometryPoint(geometry: Geometry): Point

    fun updatePassword(passwordInput: UpdatePasswordInput)
}
