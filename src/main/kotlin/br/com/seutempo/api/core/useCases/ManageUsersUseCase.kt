package br.com.seutempo.api.core.useCases

import br.com.seutempo.api.core.domain.model.googleMaps.response.Geometry
import br.com.seutempo.api.core.domain.model.users.Users
import br.com.seutempo.api.core.domain.model.users.request.UpdatePasswordInput
import br.com.seutempo.api.core.ports.input.ManageUsersInputPort
import br.com.seutempo.api.core.ports.output.ManageGoogleMapsIntegrationOutputPort
import br.com.seutempo.api.core.ports.output.ManageUsersOutputPort
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Point
import org.locationtech.jts.geom.PrecisionModel
import org.springframework.stereotype.Service

@Service
class ManageUsersUseCase(
    private val usersJpaRepository: ManageUsersOutputPort,
    private val googleMapsIntegration: ManageGoogleMapsIntegrationOutputPort,
) : ManageUsersInputPort {
    private val geometryFactory = GeometryFactory(PrecisionModel(), 4326)

    override fun getUsers(): List<Users> =
        usersJpaRepository
            .findAll()

    override fun findUserById(id: Int): Users = usersJpaRepository.findById(id)

    override fun convertLocationGeo(address: String): Geometry {
        val results = googleMapsIntegration.getGeolocationUser(address)
        return results.results.map { item -> item.geometry }.first()
    }

    override fun convertGeometryPoint(geometry: Geometry): Point =
        geometryFactory.createPoint(
            org.locationtech.jts.geom
                .Coordinate(geometry.location.lng, geometry.location.lat),
        )

    override fun updatePassword(passwordInput: UpdatePasswordInput) {
        // TODO: ADD VALIDATION PASSWORD
        val user = usersJpaRepository.findById(passwordInput.id)
        usersJpaRepository.updatePassword(user, passwordInput)
    }
}
