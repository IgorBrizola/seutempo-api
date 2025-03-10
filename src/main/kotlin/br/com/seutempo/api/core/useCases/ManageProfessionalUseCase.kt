package br.com.seutempo.api.core.useCases

import br.com.seutempo.api.core.domain.exceptions.BusinessException
import br.com.seutempo.api.core.domain.exceptions.ResourceAlreadyExistsException
import br.com.seutempo.api.core.domain.model.professional.Professional
import br.com.seutempo.api.core.domain.model.professional.request.UpdateLocation
import br.com.seutempo.api.core.domain.model.professional.request.UpdateProfessionalInput
import br.com.seutempo.api.core.domain.model.professional.response.UrlProfessionalResponse
import br.com.seutempo.api.core.ports.input.ManageClientInputPort
import br.com.seutempo.api.core.ports.input.ManageGoogleMapsInputPort
import br.com.seutempo.api.core.ports.input.ManageProfessionalInputPort
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
    private val manageClientUseCase: ManageClientInputPort,
    private val manageGoogleMapsUseCase: ManageGoogleMapsInputPort,
    private val specialtyJpaRepository: ManageSpecialtyUseCase,
) : ManageProfessionalInputPort {
    private val log = LogManager.getLogger(javaClass)

    private val baseUrlPerfil = "https://seutempo.com.br/st/"

    @Transactional
    override fun createUsersProfessional(professional: Professional) {
        verifyUserExists(professional)

        val buildProfessional = buildProfessional(professional)

        professionalJpaRepository.save(buildProfessional)
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
        val geolocation = manageGoogleMapsUseCase.getInfoLocations(professional.cep)

        val urlProfessional = generateLink(professional)

        professional.urlProfessional = urlProfessional.urlProfessional
        professional.linkNameProfessional = urlProfessional.linkNameProfessional

        professional.lat = geolocation.lat
        professional.lon = geolocation.lon
        professional.location = geolocation.location

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

    override fun getProfessionals(
        name: String?,
        maxValue: BigDecimal?,
        minValue: BigDecimal?,
    ): List<Professional> =
        professionalJpaRepository
            .findProfessionalsByFilters(name, maxValue, minValue)

    override fun getProfessionalBySpecialtyId(id: Int): MutableList<Professional> =
        professionalJpaRepository
            .findProfessionalEntityBySpecialtiesId(id)

    override fun getProfessionalByCategoryId(id: Int): List<Professional> =
        professionalJpaRepository
            .findProfessionalEntityBySpecialtiesCategoryEntityId(id)

    override fun findProfessionalWithLocation(id: Int): List<Professional> =
        professionalJpaRepository.findProfessionalsWithinRadius(manageClientUseCase.findClientById(id).location)

    override fun findProfessionalById(id: Int): Professional {
        log.info("Buscando professional by id - $id")
        return professionalJpaRepository.findById(id)
    }

    override fun findProfessionalByLinkName(linkName: String): Professional =
        professionalJpaRepository.findProfessionalEntityByLinkNameProfessional(linkName)

    @Transactional
    override fun updateAddress(updateLocation: UpdateLocation): Professional {
        val professional = buildLocationProfessional(updateLocation)

        val professionalUpdate =
            professional.copy(
                id = updateLocation.id,
                cep = updateLocation.cep ?: professional.cep,
                serviceRadiusKm = updateLocation.serviceRadiusKm ?: professional.serviceRadiusKm,
                lat = updateLocation.lat ?: professional.lat,
                lon = updateLocation.lon ?: professional.lon,
                location = updateLocation.location ?: professional.location,
            )
        professionalJpaRepository.save(professionalUpdate)

        return professionalUpdate
    }

    private fun buildLocationProfessional(updateLocation: UpdateLocation): Professional {
        val professional = professionalJpaRepository.findById(updateLocation.id)

        if (updateLocation.cep != null) {
            val location = manageGoogleMapsUseCase.getInfoLocations(updateLocation.cep)

            updateLocation.lon = location.lon
            updateLocation.lat = location.lat
            updateLocation.location = location.location
        }

        return professional
    }

    @Transactional
    override fun updateProfessionalById(professionalInput: UpdateProfessionalInput): Professional {
        val professional = professionalJpaRepository.findById(professionalInput.id)
        return professionalJpaRepository.updateProfessional(professional, professionalInput)
    }

    @Transactional
    override fun disableProfessionalById(id: Int) {
        val professional = professionalJpaRepository.findById(id)
        professionalJpaRepository.disableProfessional(professional)
    }

    @Transactional
    override fun activeProfessionalById(id: Int) {
        val professional = professionalJpaRepository.findById(id)
        professionalJpaRepository.activeProfessional(professional)
    }

    @Transactional
    override fun removeSpecialtyProfessional(
        id: Int,
        specialitiesIds: List<Int>,
    ) {
        val professional = professionalJpaRepository.findById(id)

        professional.specialties.removeIf { it.id in specialitiesIds }

        professionalJpaRepository.save(professional)
    }

    override fun addSpecialtyProfessional(
        id: Int,
        specialitiesIds: List<Int>,
    ) {
        val professional = professionalJpaRepository.findById(id)

        if (professional.specialties.size >= 5) {
            throw BusinessException("Already limit max (5) specialities register!")
        }

        val newSpecialities = specialtyJpaRepository.findSpecialtyByIds(specialitiesIds)

        val existingSpecialities = professional.specialties.map { it.id }.toSet()

        val specialitiesToAdd = newSpecialities.filterNot { it.id in existingSpecialities }

        if (specialitiesToAdd.isEmpty()) {
            throw BusinessException("Specialities already register!")
        }

        professional.specialties.addAll(specialitiesToAdd)

        professionalJpaRepository.save(professional)
    }
}
