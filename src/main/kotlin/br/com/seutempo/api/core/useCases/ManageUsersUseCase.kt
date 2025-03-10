package br.com.seutempo.api.core.useCases

import br.com.seutempo.api.core.domain.exceptions.BusinessException
import br.com.seutempo.api.core.domain.exceptions.ResourceAlreadyExistsException
import br.com.seutempo.api.core.domain.model.users.Users
import br.com.seutempo.api.core.domain.model.users.request.UpdatePasswordInput
import br.com.seutempo.api.core.ports.input.ManageUsersInputPort
import br.com.seutempo.api.core.ports.output.ManageUsersOutputPort
import br.com.seutempo.api.util.AppUtil.isValidCpf
import br.com.seutempo.api.util.AppUtil.isValidPhoneNumber
import org.springframework.stereotype.Service

@Service
class ManageUsersUseCase(
    private val usersJpaRepository: ManageUsersOutputPort,
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

    override fun verifyIfUserIsValid(user: Users) =
        when {
            usersJpaRepository.existsByEmailAndActiveIsTrue(user.email) -> {
                throw ResourceAlreadyExistsException("User with email '${user.email}' already exists.")
            }
            usersJpaRepository.existsByCpfAndActiveIsTrue(user.cpf) -> {
                throw ResourceAlreadyExistsException("User with cpf '${user.cpf}' already exists.")
            }
            usersJpaRepository.existsByPhoneAndActiveIsTrue(user.phone) -> {
                throw ResourceAlreadyExistsException("User with phone number '${user.phone}' already exists.")
            }
            !isValidPhoneNumber(user.phone) -> {
                throw BusinessException("Number format invalid! - use format +xx (xx) xxxxx-xxxx")
            }
            !isValidCpf(user.cpf) -> {
                throw BusinessException("Cpf format invalid! - use format xxx.xxx.xxx-xx")
            }
            // TODO: disable comments after tests
//            !isCpfExist(professional.user.cpf) -> {
//                throw BusinessException("Cpf not exist!")
//            }
            else -> {}
        }
}
