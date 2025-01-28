package br.com.seutempo.api.service.professional

import br.com.seutempo.api.exception.users.UserAlreadyExistsException
import br.com.seutempo.api.model.professional.Professional
import br.com.seutempo.api.model.professional.request.UsersProfessionalRequestNew
import br.com.seutempo.api.model.users.TypeUser
import br.com.seutempo.api.model.users.Users
import br.com.seutempo.api.repository.professional.ProfessionalRepository
import br.com.seutempo.api.repository.users.UsersRepository
import br.com.seutempo.api.util.AppUtil.removeAccents
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class ProfessionalService(
    private val professionalRepository: ProfessionalRepository,
    private val usersRepository: UsersRepository,
) {
    fun createUsersProfessional(newUsersProfessionalRequest: UsersProfessionalRequestNew) {
        val userExist = usersRepository.existsByEmailAndActiveIsTrue(newUsersProfessionalRequest.email)

        if (userExist) throw UserAlreadyExistsException("User with email '${newUsersProfessionalRequest.email}' already exists.")

        val cpfExist = usersRepository.existsByCpfAndActiveIsTrue(newUsersProfessionalRequest.cpf)

        if (cpfExist) throw UserAlreadyExistsException("User with cpf '${newUsersProfessionalRequest.cpf}' already exists.")

        val newUser =
            Users(
                name = newUsersProfessionalRequest.name,
                lastName = newUsersProfessionalRequest.lastName,
                email = newUsersProfessionalRequest.email,
                password = newUsersProfessionalRequest.password,
                cpf = newUsersProfessionalRequest.cpf,
                phone = newUsersProfessionalRequest.phone,
                dateAnniversary = newUsersProfessionalRequest.dateAnniversary,
                typeUser = TypeUser.PROFESSIONAL,
            )

        val user = usersRepository.save(newUser)

        val urlSeuTempo = "https://seutempo.com.br/st/"

        val nameUrl = "${user.name} ${user.lastName}"

        val urlProfessional = "$urlSeuTempo${removeAccents(nameUrl)}"

        val urlProfessionalRandom = "$urlSeuTempo${removeAccents(nameUrl)}-${Random.nextInt(999)}"

        val existsUrlProfessional = professionalRepository.existsByLinkProfessional(urlProfessional)

        val linkProfessional = if (existsUrlProfessional) urlProfessionalRandom else urlProfessional

        val newProfessional =
            Professional(
                user = user,
                linkProfessional = linkProfessional,
                valueHour = newUsersProfessionalRequest.valueHour,
            )

        professionalRepository.save(newProfessional)
    }
}
