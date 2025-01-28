package br.com.seutempo.api.service.professional

import br.com.seutempo.api.exception.users.UserAlreadyExistsException
import br.com.seutempo.api.mapper.users.UsersMapper
import br.com.seutempo.api.model.professional.Professional
import br.com.seutempo.api.model.professional.request.UsersProfessionalRequestNew
import br.com.seutempo.api.repository.professional.ProfessionalRepository
import br.com.seutempo.api.repository.users.UsersRepository
import br.com.seutempo.api.util.AppUtil.removeAccents
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class ProfessionalService(
    private val professionalRepository: ProfessionalRepository,
    private val usersRepository: UsersRepository,
    private val usersMapper: UsersMapper,
) {
    fun createUsersProfessional(newUsersProfessionalRequest: UsersProfessionalRequestNew) {
        if (usersRepository.existsByEmailAndActiveIsTrue(newUsersProfessionalRequest.email)) {
            throw UserAlreadyExistsException("User with email '${newUsersProfessionalRequest.email}' already exists.")
        }

        if (usersRepository.existsByCpfAndActiveIsTrue(newUsersProfessionalRequest.cpf)) {
            throw UserAlreadyExistsException("User with cpf '${newUsersProfessionalRequest.cpf}' already exists.")
        }

        val newUser = usersMapper.usersProfessionalRequestToUsers(newUsersProfessionalRequest)

        val user = usersRepository.save(newUser)

        val urlSeuTempo = "https://seutempo.com.br/st/"

        val nameUrl = "${user.name} ${user.lastName}"

        val urlProfessional = "$urlSeuTempo${removeAccents(nameUrl)}"

        val urlProfessionalRandom = "$urlSeuTempo${removeAccents(nameUrl)}-${Random.nextInt(999)}"

        val existsUrlProfessional = professionalRepository.existsByLinkProfessional(urlProfessional)

        val linkProfessional = if (existsUrlProfessional) urlProfessionalRandom else urlProfessional

        professionalRepository.save(
            Professional(
                user = user,
                linkProfessional = linkProfessional,
                valueHour = newUsersProfessionalRequest.valueHour,
            ),
        )
    }
}
