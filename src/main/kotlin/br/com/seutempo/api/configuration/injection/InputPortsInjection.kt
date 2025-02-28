package br.com.seutempo.api.configuration.injection

import br.com.seutempo.api.adapters.integration.GoogleMapsIntegration
import br.com.seutempo.api.adapters.web.mapper.category.CategoryMapper
import br.com.seutempo.api.adapters.web.mapper.client.ClientMapper
import br.com.seutempo.api.adapters.web.mapper.professional.ProfessionalMapper
import br.com.seutempo.api.adapters.web.mapper.specialty.SpecialtyMapper
import br.com.seutempo.api.adapters.web.mapper.users.UsersMapper
import br.com.seutempo.api.configuration.web.GoogleMapsConfig
import br.com.seutempo.api.core.ports.input.ManageCategoryInputPort
import br.com.seutempo.api.core.ports.input.ManageClientInputPort
import br.com.seutempo.api.core.ports.input.ManageProfessionalInputPort
import br.com.seutempo.api.core.ports.input.ManageSpecialtyInputPort
import br.com.seutempo.api.core.ports.input.ManageUsersInputPort
import br.com.seutempo.api.core.ports.output.ManageCategoryOutputPort
import br.com.seutempo.api.core.ports.output.ManageClientOutputPort
import br.com.seutempo.api.core.ports.output.ManageProfessionalOutputPort
import br.com.seutempo.api.core.ports.output.ManageSpecialtyOutputPort
import br.com.seutempo.api.core.ports.output.ManageUsersOutputPort
import br.com.seutempo.api.core.useCases.ManageCategoryUseCase
import br.com.seutempo.api.core.useCases.ManageClientUseCase
import br.com.seutempo.api.core.useCases.ManageProfessionalUseCase
import br.com.seutempo.api.core.useCases.ManageSpecialtyUseCase
import br.com.seutempo.api.core.useCases.ManageUsersUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InputPortsInjection {
    @Bean
    fun manageUsersInputPort(
        usersJpaRepository: ManageUsersOutputPort,
        usersMapper: UsersMapper,
        googleMapsIntegration: GoogleMapsIntegration,
        googleMapsConfig: GoogleMapsConfig,
    ): ManageUsersInputPort =
        ManageUsersUseCase(
            usersJpaRepository,
            usersMapper,
            googleMapsIntegration,
            googleMapsConfig,
        )

    @Bean
    fun manageProfessionalInputPort(
        professionalJpaRepository: ManageProfessionalOutputPort,
        usersJpaRepository: ManageUsersOutputPort,
        usersMapper: UsersMapper,
        professionalMapper: ProfessionalMapper,
        manageSpecialtyUseCase: ManageSpecialtyUseCase,
        manageUsersUseCase: ManageUsersInputPort,
        manageClientUseCase: ManageClientInputPort,
    ): ManageProfessionalInputPort =
        ManageProfessionalUseCase(
            professionalJpaRepository,
            usersJpaRepository,
            usersMapper,
            professionalMapper,
            manageSpecialtyUseCase,
            manageUsersUseCase,
            manageClientUseCase,
        )

    @Bean
    fun manageClientInputPort(
        clientJpaRepository: ManageClientOutputPort,
        usersJpaRepository: ManageUsersOutputPort,
        clientMapper: ClientMapper,
        usersMapper: UsersMapper,
        manageUsersUseCase: ManageUsersInputPort,
    ): ManageClientInputPort =
        ManageClientUseCase(
            clientJpaRepository,
            usersJpaRepository,
            clientMapper,
            usersMapper,
            manageUsersUseCase,
        )

    @Bean
    fun manageSpecialtyInputPort(
        specialtyJpaRepository: ManageSpecialtyOutputPort,
        categoryJpaRepository: ManageCategoryOutputPort,
        professionalJpaRepository: ManageProfessionalOutputPort,
        specialtyMapper: SpecialtyMapper,
    ): ManageSpecialtyInputPort =
        ManageSpecialtyUseCase(
            specialtyJpaRepository,
            categoryJpaRepository,
            professionalJpaRepository,
            specialtyMapper,
        )

    @Bean
    fun manageCategoryInputPort(
        categoryJpaRepository: ManageCategoryOutputPort,
        categoryMapper: CategoryMapper,
    ): ManageCategoryInputPort =
        ManageCategoryUseCase(
            categoryJpaRepository,
            categoryMapper,
        )
}
