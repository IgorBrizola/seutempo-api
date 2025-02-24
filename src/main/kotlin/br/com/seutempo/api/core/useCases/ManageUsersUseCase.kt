package br.com.seutempo.api.core.useCases

import br.com.seutempo.api.adapters.integration.GoogleMapsIntegration
import br.com.seutempo.api.adapters.integration.model.response.Geometry
import br.com.seutempo.api.adapters.repository.jpa.users.UsersJpaRepository
import br.com.seutempo.api.adapters.web.mapper.users.UsersMapper
import br.com.seutempo.api.adapters.web.model.response.users.UsersResponse
import br.com.seutempo.api.configuration.web.GoogleMapsConfig
import br.com.seutempo.api.core.domain.exceptions.ResourceNotFoundException
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Point
import org.locationtech.jts.geom.PrecisionModel
import org.springframework.stereotype.Service

@Service
class ManageUsersUseCase(
    private val usersJpaRepository: UsersJpaRepository,
    private val usersMapper: UsersMapper,
    private val googleMapsIntegration: GoogleMapsIntegration,
    val googleMapsConfig: GoogleMapsConfig,
) {
    private val geometryFactory = GeometryFactory(PrecisionModel(), 4326)

    fun getUsers(): List<UsersResponse> =
        usersJpaRepository
            .findAll()
            .map { user ->
                usersMapper.usersToUsersResponse(user)
            }

    fun findUserById(id: Int): UsersResponse =
        usersMapper.usersToUsersResponse(
            usersJpaRepository.findById(id).orElseThrow {
                ResourceNotFoundException("User not found! - $id")
            },
        )

    fun convertLocationGeo(address: String): Geometry {
        val results = googleMapsIntegration.getGeolocationUser(address, googleMapsConfig.apiKey)
        return results.results.map { item -> item.geometry }.first()
    }

    fun convertGeometryPoint(geometry: Geometry): Point =
        geometryFactory.createPoint(
            org.locationtech.jts.geom
                .Coordinate(geometry.location.lng, geometry.location.lat),
        )
}
