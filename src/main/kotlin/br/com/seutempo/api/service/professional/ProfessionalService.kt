package br.com.seutempo.api.service.professional

import br.com.seutempo.api.integration.response.GeoResponse
import br.com.seutempo.api.mapper.professional.ProfessionalMapper
import br.com.seutempo.api.mapper.specialty.SpecialtyMapper
import br.com.seutempo.api.mapper.users.UsersMapper
import br.com.seutempo.api.model.exception.ResourceNotFoundException
import br.com.seutempo.api.model.exception.users.UserAlreadyExistsException
import br.com.seutempo.api.model.professional.request.UpdateAddressProfessionalRequest
import br.com.seutempo.api.model.professional.request.UsersProfessionalRequestNew
import br.com.seutempo.api.model.professional.response.ProfessionalResponse
import br.com.seutempo.api.model.professional.response.UrlProfessional
import br.com.seutempo.api.model.users.Users
import br.com.seutempo.api.repository.professional.ProfessionalRepository
import br.com.seutempo.api.repository.users.UsersRepository
import br.com.seutempo.api.service.client.ClientService
import br.com.seutempo.api.service.specialty.SpecialtyService
import br.com.seutempo.api.service.users.UsersService
import br.com.seutempo.api.util.AppUtil.removeAccents
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import kotlin.random.Random

@Service
class ProfessionalService(
    private val professionalRepository: ProfessionalRepository,
    private val usersRepository: UsersRepository,
    private val usersMapper: UsersMapper,
    private val professionalMapper: ProfessionalMapper,
    private val specialtyService: SpecialtyService,
    private val usersService: UsersService,
    private val clientService: ClientService,
    private val specialtyMapper: SpecialtyMapper,
) {
    private val log = LogManager.getLogger(javaClass)

    private val baseUrlPerfil = "https://seutempo.com.br/st/"

    @Transactional
    fun createUsersProfessional(newUsersProfessionalRequest: UsersProfessionalRequestNew) {
        verifyUserExists(newUsersProfessionalRequest)

        val user = usersMapper.usersProfessionalRequestToUsers(newUsersProfessionalRequest)

        val specialtiesResponse =
            specialtyService
                .findSpecialtyByIds(newUsersProfessionalRequest.specialtyIds)

        val specialtyEntity = specialtiesResponse.map { item -> specialtyMapper.toSpecialtyEntity(item) }

        val urlProfessional = generateLink(user)

        val geolocation = generateGeolocation(newUsersProfessionalRequest.cep)

        val professional =
            professionalMapper.newUsersProfessionalRequestToProfessional(
                user = user,
                newUsersProfessionalRequest = newUsersProfessionalRequest,
                lat = geolocation.latitude,
                lon = geolocation.longitude,
                location = geolocation.point,
                linkNameProfessional = urlProfessional.linkNameProfessional,
                urlProfessional = urlProfessional.urlProfessional,
                specialties = specialtyEntity,
            )

        professionalRepository.save(professional)
    }

    private fun generateGeolocation(cep: String): GeoResponse {
        val geometry = usersService.convertLocationGeo(cep)

        val point = usersService.convertGeometryPoint(geometry)

        return GeoResponse(
            latitude = geometry.location.lat,
            longitude = geometry.location.lng,
            point = point,
        )
    }

    private fun generateLink(user: Users): UrlProfessional {
        val nameLink = removeAccents("${user.name} ${user.lastName}")

        val nameLinkRandom = "$nameLink-${Random.nextInt(999)}"

        val urlProfessional = "$baseUrlPerfil$nameLink"

        val urlProfessionalRandom = "$baseUrlPerfil$nameLinkRandom"

        val existsUrlProfessional = professionalRepository.existsByLinkNameProfessional(nameLink)

        return if (existsUrlProfessional) {
            UrlProfessional(
                nameLinkRandom,
                urlProfessionalRandom,
            )
        } else {
            UrlProfessional(nameLink, urlProfessional)
        }
    }

    private fun verifyUserExists(newUsersProfessionalRequest: UsersProfessionalRequestNew) {
        if (usersRepository.existsByEmailAndActiveIsTrue(newUsersProfessionalRequest.email)) {
            throw UserAlreadyExistsException("User with email '${newUsersProfessionalRequest.email}' already exists.")
        }
        if (usersRepository.existsByCpfAndActiveIsTrue(newUsersProfessionalRequest.cpf)) {
            throw UserAlreadyExistsException("User with cpf '${newUsersProfessionalRequest.cpf}' already exists.")
        }
    }

    fun getProfessionalToClients(
        name: String?,
        value: BigDecimal?,
    ): List<ProfessionalResponse> =
        professionalRepository
            .findProfessionalsByFilters(name, value)
            .map { item ->
                professionalMapper.professionalToProfessionalResponse(
                    user = usersMapper.usersToUsersResponse(item.user),
                    professional = item,
                )
            }

    fun getProfessionalBySpecialtyId(id: Int): List<ProfessionalResponse> =
        professionalRepository
            .findProfessionalBySpecialtiesId(id)
            .map { item ->
                professionalMapper.professionalToProfessionalResponse(
                    user = usersMapper.usersToUsersResponse(item.user),
                    professional = item,
                )
            }

    fun getProfessionalByCategoryId(id: Int): List<ProfessionalResponse> =
        professionalRepository
            .findProfessionalBySpecialtiesCategoryId(id)
            .map { item ->
                professionalMapper.professionalToProfessionalResponse(
                    user = usersMapper.usersToUsersResponse(item.user),
                    professional = item,
                )
            }

    fun findProfessionalWithLocation(id: Int): List<ProfessionalResponse> {
        val clientLocation = clientService.findClientById(id).addresses.location

        return professionalRepository.findProfessionalsWithinRadius(clientLocation).map { item ->
            professionalMapper.professionalToProfessionalResponse(
                user = usersMapper.usersToUsersResponse(item.user),
                professional = item,
            )
        }
    }

    fun findProfessionalById(id: Int): ProfessionalResponse {
        log.info("Buscando professional by id - $id")
        val professional = professionalRepository.findById(id).orElseThrow { ResourceNotFoundException("Professional not found! - $id") }
        val user = usersService.findUserById(professional.user.id!!)
        return professionalMapper.professionalToProfessionalResponse(user, professional)
    }

    fun findProfessionalByLinkName(linkName: String): ProfessionalResponse {
        val professional =
            professionalRepository.findProfessionalByLinkNameProfessional(linkName).orElseThrow {
                ResourceNotFoundException("Professional not found! - $linkName")
            }
        val user = usersService.findUserById(professional.user.id!!)
        return professionalMapper.professionalToProfessionalResponse(user, professional)
    }

    fun updateAddress(
        id: Int,
        updateAddressProfessionalRequest: UpdateAddressProfessionalRequest,
    ) {
        val professional = professionalRepository.findById(id).orElseThrow { ResourceNotFoundException("Professional not found! - $id") }

        val geolocation = generateGeolocation(updateAddressProfessionalRequest.cep)

        val professionalUpdate =
            professional.copy(
                cep = updateAddressProfessionalRequest.cep,
                lat = geolocation.latitude,
                lon = geolocation.longitude,
                location = geolocation.point,
            )

        professionalRepository.save(professionalUpdate)
    }
}
