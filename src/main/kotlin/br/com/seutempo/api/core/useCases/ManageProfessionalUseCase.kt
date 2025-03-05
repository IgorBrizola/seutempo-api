package br.com.seutempo.api.core.useCases

import br.com.seutempo.api.adapters.integration.model.response.GeoResponse
import br.com.seutempo.api.adapters.repository.model.UsersEntity
import br.com.seutempo.api.adapters.web.mapper.professional.ProfessionalMapper
import br.com.seutempo.api.adapters.web.mapper.users.UsersMapper
import br.com.seutempo.api.adapters.web.model.request.professional.NewProfessionalRequest
import br.com.seutempo.api.adapters.web.model.request.professional.UpdateAddressProfessionalRequest
import br.com.seutempo.api.adapters.web.model.response.professional.ProfessionalResponse
import br.com.seutempo.api.adapters.web.model.response.professional.UrlProfessionalResponse
import br.com.seutempo.api.core.domain.exceptions.ResourceAlreadyExistsException
import br.com.seutempo.api.core.ports.input.ManageClientInputPort
import br.com.seutempo.api.core.ports.input.ManageProfessionalInputPort
import br.com.seutempo.api.core.ports.input.ManageSpecialtyInputPort
import br.com.seutempo.api.core.ports.input.ManageUsersInputPort
import br.com.seutempo.api.core.ports.output.ManageProfessionalOutputPort
import br.com.seutempo.api.core.ports.output.ManageUsersOutputPort
import br.com.seutempo.api.util.AppUtil.removeAccents
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import kotlin.random.Random

@Service
class ManageProfessionalUseCase(
    private val professionalJpaRepository: ManageProfessionalOutputPort,
    private val usersJpaRepository: ManageUsersOutputPort,
    private val usersMapper: UsersMapper,
    private val professionalMapper: ProfessionalMapper,
    private val manageSpecialtyUseCase: ManageSpecialtyInputPort,
    private val manageUsersUseCase: ManageUsersInputPort,
    private val manageClientUseCase: ManageClientInputPort,
) : ManageProfessionalInputPort {
    private val log = LogManager.getLogger(javaClass)

    private val baseUrlPerfil = "https://seutempo.com.br/st/"

    @Transactional
    override fun createUsersProfessional(newUsersProfessionalRequest: NewProfessionalRequest) {
        verifyUserExists(newUsersProfessionalRequest)

        val user = usersMapper.usersProfessionalRequestToUsers(newUsersProfessionalRequest)

        val specialties =
            manageSpecialtyUseCase
                .findSpecialtyRegisterProfessional(newUsersProfessionalRequest.specialtyIds)

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
                specialties = specialties,
            )

        professionalJpaRepository.save(professional)
    }

    private fun generateGeolocation(cep: String): GeoResponse {
        val geometry = manageUsersUseCase.convertLocationGeo(cep)

        val point = manageUsersUseCase.convertGeometryPoint(geometry)

        return GeoResponse(
            latitude = geometry.location.lat,
            longitude = geometry.location.lng,
            point = point,
        )
    }

    private fun generateLink(user: UsersEntity): UrlProfessionalResponse {
        val nameLink = removeAccents("${user.name} ${user.lastName}")

        val nameLinkRandom = "$nameLink-${Random.nextInt(999)}"

        val urlProfessional = "$baseUrlPerfil$nameLink"

        val urlProfessionalRandom = "$baseUrlPerfil$nameLinkRandom"

        val existsUrlProfessional = professionalJpaRepository.existsByLinkNameProfessional(nameLink)

        return if (existsUrlProfessional) {
            UrlProfessionalResponse(
                nameLinkRandom,
                urlProfessionalRandom,
            )
        } else {
            UrlProfessionalResponse(nameLink, urlProfessional)
        }
    }

    private fun verifyUserExists(newUsersProfessionalRequest: NewProfessionalRequest) {
        if (usersJpaRepository.existsByEmailAndActiveIsTrue(newUsersProfessionalRequest.email)) {
            throw ResourceAlreadyExistsException("User with email '${newUsersProfessionalRequest.email}' already exists.")
        }
        if (usersJpaRepository.existsByCpfAndActiveIsTrue(newUsersProfessionalRequest.cpf)) {
            throw ResourceAlreadyExistsException("User with cpf '${newUsersProfessionalRequest.cpf}' already exists.")
        }
    }

    override fun getProfessionalToClients(
        name: String?,
        value: BigDecimal?,
    ): List<ProfessionalResponse> =
        professionalJpaRepository
            .findProfessionalsByFilters(name, value)
            .map { item ->
                professionalMapper.professionalToProfessionalResponse(
                    user = usersMapper.toUsers(item.user),
                    professional = item,
                )
            }

    override fun getProfessionalBySpecialtyId(id: Int): List<ProfessionalResponse> =
        professionalJpaRepository
            .findProfessionalEntityBySpecialtiesId(id)
            .map { item ->
                professionalMapper.professionalToProfessionalResponse(
                    user = usersMapper.toUsers(item.user),
                    professional = item,
                )
            }

    override fun getProfessionalByCategoryId(id: Int): List<ProfessionalResponse> =
        professionalJpaRepository
            .findProfessionalEntityBySpecialtiesCategoryEntityId(id)
            .map { item ->
                professionalMapper.professionalToProfessionalResponse(
                    user = usersMapper.toUsers(item.user),
                    professional = item,
                )
            }

    override fun findProfessionalWithLocation(id: Int): List<ProfessionalResponse> {
        val clientLocation = manageClientUseCase.findClientById(id).address.location

        return professionalJpaRepository.findProfessionalsWithinRadius(clientLocation).map { item ->
            professionalMapper.professionalToProfessionalResponse(
                user = usersMapper.toUsers(item.user),
                professional = item,
            )
        }
    }

    override fun findProfessionalById(id: Int): ProfessionalResponse {
        log.info("Buscando professional by id - $id")
        val professional = professionalJpaRepository.findById(id)
        val user = manageUsersUseCase.findUserById(professional.user.id!!)
        return professionalMapper.professionalToProfessionalResponse(user, professional)
    }

    override fun findProfessionalByLinkName(linkName: String): ProfessionalResponse {
        val professional =
            professionalJpaRepository.findProfessionalEntityByLinkNameProfessional(linkName)
        val user = manageUsersUseCase.findUserById(professional.user.id!!)
        return professionalMapper.professionalToProfessionalResponse(user, professional)
    }

    override fun updateAddress(
        id: Int,
        updateAddressProfessionalRequest: UpdateAddressProfessionalRequest,
    ) {
        val professional = professionalJpaRepository.findById(id)

        val geolocation = generateGeolocation(updateAddressProfessionalRequest.cep)

        val professionalUpdate =
            professional.copy(
                cep = updateAddressProfessionalRequest.cep,
                lat = geolocation.latitude,
                lon = geolocation.longitude,
                location = geolocation.point,
            )

        professionalJpaRepository.save(professionalUpdate)
    }
}
