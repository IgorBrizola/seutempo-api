package br.com.seutempo.api.service.professional

import br.com.seutempo.api.mapper.professional.ProfessionalMapper
import br.com.seutempo.api.mapper.users.UsersMapper
import br.com.seutempo.api.model.exception.users.UserAlreadyExistsException
import br.com.seutempo.api.model.professional.request.UsersProfessionalRequestNew
import br.com.seutempo.api.model.users.Users
import br.com.seutempo.api.repository.professional.ProfessionalRepository
import br.com.seutempo.api.repository.users.UsersRepository
import br.com.seutempo.api.util.AppUtil.removeAccents
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.random.Random

@Service
class ProfessionalService(
    private val professionalRepository: ProfessionalRepository,
    private val usersRepository: UsersRepository,
    private val usersMapper: UsersMapper,
    private val professionalMapper: ProfessionalMapper,
) {
    private val baseUrlPerfil = "https://seutempo.com.br/st/"

    @Transactional
    fun createUsersProfessional(newUsersProfessionalRequest: UsersProfessionalRequestNew) {
        if (usersRepository.existsByEmailAndActiveIsTrue(newUsersProfessionalRequest.email)) {
            throw UserAlreadyExistsException("User with email '${newUsersProfessionalRequest.email}' already exists.")
        }

        if (usersRepository.existsByCpfAndActiveIsTrue(newUsersProfessionalRequest.cpf)) {
            throw UserAlreadyExistsException("User with cpf '${newUsersProfessionalRequest.cpf}' already exists.")
        }

        val user = usersMapper.usersProfessionalRequestToUsers(newUsersProfessionalRequest)

        val professional =
            professionalMapper.newUsersProfessionalRequestToProfessional(
                user,
                newUsersProfessionalRequest,
                generateLink(user),
            )

        professionalRepository.save(professional)
    }

    private fun generateLink(user: Users): String {
        val nameUrl = "${user.name} ${user.lastName}"

        val urlProfessional = "$baseUrlPerfil${removeAccents(nameUrl)}"

        val urlProfessionalRandom = "$baseUrlPerfil${removeAccents(nameUrl)}-${Random.nextInt(999)}"

        val existsUrlProfessional = professionalRepository.existsByLinkProfessional(urlProfessional)

        return if (existsUrlProfessional) urlProfessionalRandom else urlProfessional
    }
}
