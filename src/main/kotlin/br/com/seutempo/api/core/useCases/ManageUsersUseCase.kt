package br.com.seutempo.api.core.useCases

import br.com.seutempo.api.adapters.integration.GoogleMapsIntegration
import br.com.seutempo.api.adapters.integration.model.response.Geometry
import br.com.seutempo.api.configuration.web.GoogleMapsConfig
import br.com.seutempo.api.core.domain.model.Users
import br.com.seutempo.api.core.ports.input.ManageUsersInputPort
import br.com.seutempo.api.core.ports.output.ManageUsersOutputPort
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Point
import org.locationtech.jts.geom.PrecisionModel
import org.springframework.stereotype.Service

@Service
class ManageUsersUseCase(
    private val usersJpaRepository: ManageUsersOutputPort,
    private val googleMapsIntegration: GoogleMapsIntegration,
    private val googleMapsConfig: GoogleMapsConfig,
) : ManageUsersInputPort {
    private val geometryFactory = GeometryFactory(PrecisionModel(), 4326)

    override fun getUsers(): List<Users> =
        usersJpaRepository
            .findAll()

    override fun findUserById(id: Int): Users = usersJpaRepository.findById(id)

    override fun convertLocationGeo(address: String): Geometry {
        val results = googleMapsIntegration.getGeolocationUser(address, googleMapsConfig.apiKey)
        return results.results.map { item -> item.geometry }.first()
    }

    override fun convertGeometryPoint(geometry: Geometry): Point =
        geometryFactory.createPoint(
            org.locationtech.jts.geom
                .Coordinate(geometry.location.lng, geometry.location.lat),
        )
}
