package br.com.seutempo.api.core.useCases

import br.com.seutempo.api.adapters.integration.model.response.GeoResponse
import br.com.seutempo.api.adapters.web.model.request.professional.UpdateAddressProfessionalRequest
import br.com.seutempo.api.adapters.web.model.response.professional.ProfessionalResponse
import br.com.seutempo.api.adapters.web.model.response.professional.UrlProfessionalResponse
import br.com.seutempo.api.core.domain.exceptions.ResourceAlreadyExistsException
import br.com.seutempo.api.core.domain.model.professional.Professional
import br.com.seutempo.api.core.ports.input.ManageClientInputPort
import br.com.seutempo.api.core.ports.input.ManageProfessionalInputPort
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
    private val manageUsersUseCase: ManageUsersInputPort,
    private val manageClientUseCase: ManageClientInputPort,
) : ManageProfessionalInputPort {
    private val log = LogManager.getLogger(javaClass)

    private val baseUrlPerfil = "https://seutempo.com.br/st/"

    @Transactional
    override fun createUsersProfessional(professional: Professional) {
        verifyUserExists(professional)

        val buildProfessional = buildProfessional(professional)

        professionalJpaRepository.save(buildProfessional)
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

    private fun generateLink(professional: Professional): UrlProfessionalResponse {
        val nameLink = removeAccents("${professional.user.name} ${professional.user.lastName}")

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

    private fun buildProfessional(professional: Professional): Professional {
        val geolocation = generateGeolocation(professional.cep)

        val urlProfessional = generateLink(professional)

        professional.urlProfessional = urlProfessional.urlProfessional
        professional.linkNameProfessional = urlProfessional.linkNameProfessional

        professional.lat = geolocation.latitude
        professional.lon = geolocation.longitude
        professional.location = geolocation.point

        return professional
    }

    private fun verifyUserExists(professional: Professional) {
        if (usersJpaRepository.existsByEmailAndActiveIsTrue(professional.user.email)) {
            throw ResourceAlreadyExistsException("User with email '${professional.user.email}' already exists.")
        }
        if (usersJpaRepository.existsByCpfAndActiveIsTrue(professional.user.cpf)) {
            throw ResourceAlreadyExistsException("User with cpf '${professional.user.cpf}' already exists.")
        }
    }

    override fun getProfessionalToClients(
        name: String?,
        value: BigDecimal?,
    ): List<ProfessionalResponse> =
        professionalJpaRepository
            .findProfessionalsByFilters(name, value)

    override fun getProfessionalBySpecialtyId(id: Int): MutableList<Professional> =
        professionalJpaRepository
            .findProfessionalEntityBySpecialtiesId(id)

    override fun getProfessionalByCategoryId(id: Int): List<ProfessionalResponse> =
        professionalJpaRepository
            .findProfessionalEntityBySpecialtiesCategoryEntityId(id)

    override fun findProfessionalWithLocation(id: Int): List<ProfessionalResponse> =
        professionalJpaRepository.findProfessionalsWithinRadius(manageClientUseCase.findClientById(id).address.location)

    override fun findProfessionalById(id: Int): Professional {
        log.info("Buscando professional by id - $id")
        return professionalJpaRepository.findById(id)
    }

    override fun findProfessionalByLinkName(linkName: String): ProfessionalResponse =
        professionalJpaRepository.findProfessionalEntityByLinkNameProfessional(linkName)

    override fun updateAddress(
        id: Int,
        updateAddressProfessionalRequest: UpdateAddressProfessionalRequest,
    ) {
        val professional = professionalJpaRepository.findById(id)

        val geolocation = generateGeolocation(updateAddressProfessionalRequest.cep)

        updateAddressProfessionalRequest.lon = geolocation.longitude
        updateAddressProfessionalRequest.lat = geolocation.latitude
        updateAddressProfessionalRequest.location = geolocation.point

        val professionalUpdate =
            professional.copy(
                id = id,
                cep = updateAddressProfessionalRequest.cep,
                lat = updateAddressProfessionalRequest.lat,
                lon = updateAddressProfessionalRequest.lon,
                location = updateAddressProfessionalRequest.location,
            )

        professionalJpaRepository.save(professionalUpdate)
    }
}
