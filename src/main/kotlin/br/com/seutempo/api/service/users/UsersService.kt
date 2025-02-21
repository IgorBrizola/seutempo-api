package br.com.seutempo.api.service.users

import br.com.seutempo.api.integration.GoogleMapsConfig
import br.com.seutempo.api.integration.GoogleMapsIntegration
import br.com.seutempo.api.integration.response.Geometry
import br.com.seutempo.api.mapper.users.UsersMapper
import br.com.seutempo.api.model.exception.ResourceNotFoundException
import br.com.seutempo.api.model.users.response.UsersResponse
import br.com.seutempo.api.repository.users.UsersRepository
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Point
import org.locationtech.jts.geom.PrecisionModel
import org.springframework.stereotype.Service

@Service
class UsersService(
    private val usersRepository: UsersRepository,
    private val usersMapper: UsersMapper,
    private val googleMapsIntegration: GoogleMapsIntegration,
    val googleMapsConfig: GoogleMapsConfig,
) {
    private val geometryFactory = GeometryFactory(PrecisionModel(), 4326)

    fun getUsers(): List<UsersResponse> =
        usersRepository
            .findAll()
            .map { user ->
                usersMapper.usersToUsersResponse(user)
            }

    fun findUserById(id: Int): UsersResponse =
        usersMapper.usersToUsersResponse(
            usersRepository.findById(id).orElseThrow {
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
