package br.com.seutempo.api.configuration.injection

import br.com.seutempo.api.adapters.integration.GoogleMapsIntegration
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
        googleMapsIntegration: GoogleMapsIntegration,
        googleMapsConfig: GoogleMapsConfig,
    ): ManageUsersInputPort =
        ManageUsersUseCase(
            usersJpaRepository,
            googleMapsIntegration,
            googleMapsConfig,
        )

    @Bean
    fun manageProfessionalInputPort(
        professionalJpaRepository: ManageProfessionalOutputPort,
        usersJpaRepository: ManageUsersOutputPort,
        manageUsersUseCase: ManageUsersInputPort,
        manageClientUseCase: ManageClientInputPort,
    ): ManageProfessionalInputPort =
        ManageProfessionalUseCase(
            professionalJpaRepository,
            usersJpaRepository,
            manageUsersUseCase,
            manageClientUseCase,
        )

    @Bean
    fun manageClientInputPort(
        clientJpaRepository: ManageClientOutputPort,
        usersJpaRepository: ManageUsersOutputPort,
    ): ManageClientInputPort =
        ManageClientUseCase(
            clientJpaRepository,
            usersJpaRepository,
        )

    @Bean
    fun manageSpecialtyInputPort(
        specialtyJpaRepository: ManageSpecialtyOutputPort,
        categoryJpaRepository: ManageCategoryOutputPort,
        professionalJpaRepository: ManageProfessionalOutputPort,
    ): ManageSpecialtyInputPort =
        ManageSpecialtyUseCase(
            specialtyJpaRepository,
            categoryJpaRepository,
            professionalJpaRepository,
        )

    @Bean
    fun manageCategoryInputPort(categoryJpaRepository: ManageCategoryOutputPort): ManageCategoryInputPort =
        ManageCategoryUseCase(
            categoryJpaRepository,
        )
}
