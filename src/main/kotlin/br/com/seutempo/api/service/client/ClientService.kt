package br.com.seutempo.api.service.client

import br.com.seutempo.api.exception.users.UserAlreadyExistsException
import br.com.seutempo.api.model.address.Address
import br.com.seutempo.api.model.client.Client
import br.com.seutempo.api.model.client.request.UsersClientRequestNew
import br.com.seutempo.api.model.users.TypeUser
import br.com.seutempo.api.model.users.Users
import br.com.seutempo.api.repository.address.AddressRepository
import br.com.seutempo.api.repository.client.ClientRepository
import br.com.seutempo.api.repository.users.UsersRepository
import org.springframework.stereotype.Service

@Service
class ClientService(
    private val clientRepository: ClientRepository,
    private val usersRepository: UsersRepository,
    private val addressRepository: AddressRepository,
) {
    fun createUsersClient(newUsersClientRequest: UsersClientRequestNew) {
        if (usersRepository.existsByEmailAndActiveIsTrue(newUsersClientRequest.email)) {
            throw UserAlreadyExistsException("User with email '${newUsersClientRequest.email}' already exists.")
        }

        if (usersRepository.existsByCpfAndActiveIsTrue(newUsersClientRequest.cpf)) {
            throw UserAlreadyExistsException("User with cpf '${newUsersClientRequest.cpf}' already exists.")
        }

        val newUser =
            Users(
                name = newUsersClientRequest.name,
                lastName = newUsersClientRequest.lastName,
                email = newUsersClientRequest.email,
                password = newUsersClientRequest.password,
                cpf = newUsersClientRequest.cpf,
                phone = newUsersClientRequest.phone,
                dateAnniversary = newUsersClientRequest.dateAnniversary,
                typeUser = TypeUser.CLIENT,
            )

        val user = usersRepository.save(newUser)

        val newClient =
            Client(
                user = user,
                surname = newUsersClientRequest.surname,
            )

        val client = clientRepository.save(newClient)

        val newAddress =
            Address(
                cep = newUsersClientRequest.address.cep,
                state = newUsersClientRequest.address.state,
                city = newUsersClientRequest.address.city,
                neighborhood = newUsersClientRequest.address.neighborhood,
                street = newUsersClientRequest.address.street,
                number = newUsersClientRequest.address.number,
                complement = newUsersClientRequest.address.complement,
                additionalAddress = newUsersClientRequest.address.additionalAddress,
                typeAddress = newUsersClientRequest.address.typeAddress,
                client = client,
            )

        addressRepository.save(newAddress)
    }
}
