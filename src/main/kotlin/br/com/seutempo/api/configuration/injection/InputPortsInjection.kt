package br.com.seutempo.api.configuration.injection

import br.com.seutempo.api.adapters.integration.GoogleMapsIntegration
import br.com.seutempo.api.adapters.web.mapper.users.UsersMapper
import br.com.seutempo.api.configuration.web.GoogleMapsConfig
import br.com.seutempo.api.core.ports.input.ManageUsersInputPort
import br.com.seutempo.api.core.ports.output.ManageUsersOutputPort
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
}
