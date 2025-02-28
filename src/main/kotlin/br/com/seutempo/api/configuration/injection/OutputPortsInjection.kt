package br.com.seutempo.api.configuration.injection

import br.com.seutempo.api.adapters.repository.ManageCategoryRepository
import br.com.seutempo.api.adapters.repository.ManageClientRepository
import br.com.seutempo.api.adapters.repository.ManageProfessionalRepository
import br.com.seutempo.api.adapters.repository.ManageSpecialtyRepository
import br.com.seutempo.api.adapters.repository.ManageUsersRepository
import br.com.seutempo.api.adapters.repository.jpa.category.CategoryJpaRepository
import br.com.seutempo.api.adapters.repository.jpa.client.ClientJpaRepository
import br.com.seutempo.api.adapters.repository.jpa.professional.ProfessionalJpaRepository
import br.com.seutempo.api.adapters.repository.jpa.specialty.SpecialtyJpaRepository
import br.com.seutempo.api.adapters.repository.jpa.users.UsersJpaRepository
import br.com.seutempo.api.core.ports.output.ManageCategoryOutputPort
import br.com.seutempo.api.core.ports.output.ManageClientOutputPort
import br.com.seutempo.api.core.ports.output.ManageProfessionalOutputPort
import br.com.seutempo.api.core.ports.output.ManageSpecialtyOutputPort
import br.com.seutempo.api.core.ports.output.ManageUsersOutputPort
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OutputPortsInjection {
    @Bean
    fun manageCategoryOutputPort(categoryJpaRepository: CategoryJpaRepository): ManageCategoryOutputPort =
        ManageCategoryRepository(categoryJpaRepository)

    @Bean
    fun manageProfessionalOutputPort(professionalJpaRepository: ProfessionalJpaRepository): ManageProfessionalOutputPort =
        ManageProfessionalRepository(professionalJpaRepository)

    @Bean
    fun manageSpecialtyOutputPort(specialtyJpaRepository: SpecialtyJpaRepository): ManageSpecialtyOutputPort =
        ManageSpecialtyRepository(specialtyJpaRepository)

    @Bean
    fun manageClientOutputPort(clientJpaRepository: ClientJpaRepository): ManageClientOutputPort =
        ManageClientRepository(clientJpaRepository)

    @Bean
    fun manageUsersOutputPort(usersJpaRepository: UsersJpaRepository): ManageUsersOutputPort = ManageUsersRepository(usersJpaRepository)
}
