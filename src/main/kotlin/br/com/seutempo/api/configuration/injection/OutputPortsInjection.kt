package br.com.seutempo.api.configuration.injection

import br.com.seutempo.api.adapters.repository.ManageCategoryRepository
import br.com.seutempo.api.adapters.repository.ManageProfessionalRepository
import br.com.seutempo.api.adapters.repository.ManageSpecialtyRepository
import br.com.seutempo.api.adapters.repository.jpa.category.CategoryJpaRepository
import br.com.seutempo.api.adapters.repository.jpa.professional.ProfessionalJpaRepository
import br.com.seutempo.api.adapters.repository.jpa.specialty.SpecialtyJpaRepository
import br.com.seutempo.api.core.ports.output.ManageCategoryOutputPort
import br.com.seutempo.api.core.ports.output.ManageProfessionalOutputPort
import br.com.seutempo.api.core.ports.output.ManageSpecialtyOutputPort
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
}
