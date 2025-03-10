package br.com.seutempo.api.core.useCases

import br.com.seutempo.api.core.domain.model.users.Users
import br.com.seutempo.api.core.domain.model.users.request.UpdatePasswordInput
import br.com.seutempo.api.core.ports.input.ManageUsersInputPort
import br.com.seutempo.api.core.ports.output.ManageGoogleMapsIntegrationOutputPort
import br.com.seutempo.api.core.ports.output.ManageUsersOutputPort
import org.springframework.stereotype.Service

@Service
class ManageUsersUseCase(
    private val usersJpaRepository: ManageUsersOutputPort,
    private val googleMapsIntegration: ManageGoogleMapsIntegrationOutputPort,
) : ManageUsersInputPort {
    override fun getUsers(): List<Users> =
        usersJpaRepository
            .findAll()

    override fun findUserById(id: Int): Users = usersJpaRepository.findById(id)

    override fun updatePassword(passwordInput: UpdatePasswordInput) {
        // TODO: ADD VALIDATION PASSWORD
        val user = usersJpaRepository.findById(passwordInput.id)
        usersJpaRepository.updatePassword(user, passwordInput)
    }
}
