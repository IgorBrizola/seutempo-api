package br.com.seutempo.api.configuration.injection

import br.com.seutempo.api.adapters.integration.GoogleMapsIntegration
import br.com.seutempo.api.adapters.integration.client.GoogleMapsClient
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
import br.com.seutempo.api.adapters.web.mapper.category.CategoryMapper
import br.com.seutempo.api.adapters.web.mapper.client.ClientMapper
import br.com.seutempo.api.adapters.web.mapper.googleMaps.GoogleMapper
import br.com.seutempo.api.adapters.web.mapper.professional.ProfessionalMapper
import br.com.seutempo.api.adapters.web.mapper.specialty.SpecialtyMapper
import br.com.seutempo.api.adapters.web.mapper.users.UsersMapper
import br.com.seutempo.api.core.ports.output.ManageCategoryOutputPort
import br.com.seutempo.api.core.ports.output.ManageClientOutputPort
import br.com.seutempo.api.core.ports.output.ManageGoogleMapsIntegrationOutputPort
import br.com.seutempo.api.core.ports.output.ManageProfessionalOutputPort
import br.com.seutempo.api.core.ports.output.ManageSpecialtyOutputPort
import br.com.seutempo.api.core.ports.output.ManageUsersOutputPort
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OutputPortsInjection {
    @Bean
    fun manageCategoryOutputPort(
        categoryJpaRepository: CategoryJpaRepository,
        categoryMapper: CategoryMapper,
    ): ManageCategoryOutputPort = ManageCategoryRepository(categoryJpaRepository, categoryMapper)

    @Bean
    fun manageProfessionalOutputPort(
        professionalJpaRepository: ProfessionalJpaRepository,
        professionalMapper: ProfessionalMapper,
    ): ManageProfessionalOutputPort = ManageProfessionalRepository(professionalJpaRepository, professionalMapper)

    @Bean
    fun manageSpecialtyOutputPort(
        specialtyJpaRepository: SpecialtyJpaRepository,
        specialtyMapper: SpecialtyMapper,
        categoryMapper: CategoryMapper,
    ): ManageSpecialtyOutputPort = ManageSpecialtyRepository(specialtyJpaRepository, specialtyMapper, categoryMapper)

    @Bean
    fun manageClientOutputPort(
        clientJpaRepository: ClientJpaRepository,
        clientMapper: ClientMapper,
    ): ManageClientOutputPort = ManageClientRepository(clientJpaRepository, clientMapper)

    @Bean
    fun manageUsersOutputPort(
        usersJpaRepository: UsersJpaRepository,
        usersMapper: UsersMapper,
    ): ManageUsersOutputPort = ManageUsersRepository(usersJpaRepository, usersMapper)

    @Bean
    fun manageGoogleMapsOutputPort(
        googleMapsIntegration: GoogleMapsClient,
        googleMapper: GoogleMapper,
    ): ManageGoogleMapsIntegrationOutputPort = GoogleMapsIntegration(googleMapsIntegration, googleMapper)
}
