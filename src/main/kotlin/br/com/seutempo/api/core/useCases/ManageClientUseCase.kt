package br.com.seutempo.api.core.useCases

import br.com.seutempo.api.core.domain.exceptions.ResourceAlreadyExistsException
import br.com.seutempo.api.core.domain.model.client.Client
import br.com.seutempo.api.core.ports.input.ManageClientInputPort
import br.com.seutempo.api.core.ports.input.ManageGoogleMapsInputPort
import br.com.seutempo.api.core.ports.output.ManageClientOutputPort
import br.com.seutempo.api.core.ports.output.ManageUsersOutputPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ManageClientUseCase(
    private val clientJpaRepository: ManageClientOutputPort,
    private val usersJpaRepository: ManageUsersOutputPort,
    private val manageGoogleMapsUseCase: ManageGoogleMapsInputPort,
) : ManageClientInputPort {
    @Transactional
    override fun createUsersClient(client: Client) {
        if (usersJpaRepository.existsByEmailAndActiveIsTrue(client.user.email)) {
            throw ResourceAlreadyExistsException("User with email '${client.user.email}' already exists.")
        }

        if (usersJpaRepository.existsByCpfAndActiveIsTrue(client.user.cpf)) {
            throw ResourceAlreadyExistsException("User with cpf '${client.user.cpf}' already exists.")
        }

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
                state = geolocation.city,
            )

        return buildClient
    }

    override fun findClientById(id: Int): Client = clientJpaRepository.findById(id)
}
