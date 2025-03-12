package br.com.seutempo.api.core.useCases

import br.com.seutempo.api.core.domain.exceptions.BusinessException
import br.com.seutempo.api.core.domain.model.client.Client
import br.com.seutempo.api.core.domain.model.client.request.UpdateAddressClient
import br.com.seutempo.api.core.domain.model.client.request.UpdateClient
import br.com.seutempo.api.core.ports.input.ManageClientInputPort
import br.com.seutempo.api.core.ports.input.ManageGoogleMapsInputPort
import br.com.seutempo.api.core.ports.input.ManageUsersInputPort
import br.com.seutempo.api.core.ports.output.ManageClientOutputPort
import br.com.seutempo.api.util.AppUtil.isValidPhoneNumber
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ManageClientUseCase(
    private val clientJpaRepository: ManageClientOutputPort,
    private val manageGoogleMapsUseCase: ManageGoogleMapsInputPort,
    private val manageUsersUseCase: ManageUsersInputPort,
) : ManageClientInputPort {
    private val log = LogManager.getLogger(javaClass)

    @Transactional
    override fun createUsersClient(client: Client) {
        manageUsersUseCase.verifyIfUserIsValid(client.user)

        val buildClient = buildClient(client)

        clientJpaRepository.save(buildClient)
    }

    private fun buildClient(client: Client): Client {
        val geolocation = manageGoogleMapsUseCase.getInfoLocations(client.cep)

        val buildClient =
            client.copy(
                latitude = geolocation.latitude,
                longitude = geolocation.longitude,
                location = geolocation.location,
                formatedAddress = geolocation.formatedAddress,
                city = geolocation.city,
                state = geolocation.state,
            )

        return buildClient
    }

    override fun findClientById(id: Int): Client = clientJpaRepository.findById(id)

    override fun listAllClients(): List<Client> = clientJpaRepository.listAllClients()

    override fun listClientById(id: Int): Client = clientJpaRepository.findById(id)

    @Transactional
    override fun updateClient(updateClient: UpdateClient): Client {
        val client = clientJpaRepository.findById(updateClient.id)

        if (updateClient.phone != null &&
            !isValidPhoneNumber(updateClient.phone)
        ) {
            throw BusinessException("Number phone is invalid! - use format +xx (xx) xxxxx-xxxx")
        }

        val clientUpdated =
            client.copy(
                surname = updateClient.surname ?: client.surname,
                user =
                    client.user.copy(
                        photoUser = updateClient.photoUser ?: client.user.photoUser,
                        phone = updateClient.phone ?: client.user.phone,
                    ),
            )

        return clientJpaRepository.save(clientUpdated)
    }

    @Transactional
    override fun updateAddressClient(updateAddressClient: UpdateAddressClient): Client {
        val client = clientJpaRepository.findById(updateAddressClient.id)

        val geolocation = manageGoogleMapsUseCase.getInfoLocations(updateAddressClient.cep)

        val updateAddress =
            client.copy(
                cep = updateAddressClient.cep,
                number = updateAddressClient.number,
                complement = updateAddressClient.complement ?: client.complement,
                additionalAddress = updateAddressClient.additionalAddress ?: client.additionalAddress,
                typeAddress = updateAddressClient.typeAddress,
                street = updateAddressClient.street,
                neighborhood = updateAddressClient.neighborhood,
                formatedAddress = geolocation.formatedAddress,
                state = geolocation.state,
                city = geolocation.city,
                latitude = geolocation.latitude,
                longitude = geolocation.longitude,
                location = geolocation.location,
            )

        log.info("Client Address update - $updateAddress")

        return clientJpaRepository.save(updateAddress)
    }
}
