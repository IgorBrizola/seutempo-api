package br.com.seutempo.api.configuration.injection

import br.com.seutempo.api.adapters.repository.ManageCategoryRepository
import br.com.seutempo.api.adapters.repository.jpa.category.CategoryJpaRepository
import br.com.seutempo.api.core.ports.output.ManageCategoryOutputPort
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OutputPortsInjection {
    @Bean
    fun manageCategoryOutputPort(categoryJpaRepository: CategoryJpaRepository): ManageCategoryOutputPort =
        ManageCategoryRepository(categoryJpaRepository)
}
