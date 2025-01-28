package br.com.seutempo.api.service.users

import br.com.seutempo.api.exception.users.UserAlreadyExistsException
import br.com.seutempo.api.model.address.Address
import br.com.seutempo.api.model.client.Client
import br.com.seutempo.api.model.users.TypeUser
import br.com.seutempo.api.model.users.Users
import br.com.seutempo.api.model.users.request.UsersClientRequestNew
import br.com.seutempo.api.model.users.response.UsersResponse
import br.com.seutempo.api.repository.address.AddressRepository
import br.com.seutempo.api.repository.client.ClientRepository
import br.com.seutempo.api.repository.professional.ProfessionalRepository
import br.com.seutempo.api.repository.users.UsersRepository
import br.com.seutempo.api.util.AppUtil
import org.springframework.stereotype.Service

@Service
class UsersService(
    private val usersRepository: UsersRepository,
    private val clientRepository: ClientRepository,
    private val addressRepository: AddressRepository,
    private val professionalRepository: ProfessionalRepository,
) {
    fun getUsers(): List<UsersResponse> =
        usersRepository
            .findAll()
            .map { user ->
                UsersResponse(
                    id = user.id,
                    name = "${user.name} ${user.lastName}",
                    email = user.email,
                    phone = user.phone,
                    age = AppUtil.calcAge(user.dateAnniversary),
                    typeUser = user.typeUser,
                )
            }

    fun createUsersClient(newUsersClientRequest: UsersClientRequestNew) {
        val userExist = usersRepository.existsByEmailAndActiveIsTrue(newUsersClientRequest.email)

        if (userExist) throw UserAlreadyExistsException("User with email '${newUsersClientRequest.email}' already exists.")

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
