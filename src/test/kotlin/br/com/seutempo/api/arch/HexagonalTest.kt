package br.com.seutempo.api.arch

import br.com.seutempo.api.ApiApplication
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.library.Architectures

@AnalyzeClasses(packagesOf = [ApiApplication::class])
class HexagonalTest {
    @ArchTest
    var archTest =
        Architectures
            .layeredArchitecture()
            .consideringOnlyDependenciesInLayers()
            .layer(DOMAIN_LAYER)
            .definedBy("..domain..")
            .layer(DOMAIN_MODEL_LAYER)
            .definedBy("..domain.model..")
            .layer(USECASE_LAYER)
            .definedBy("..usecases..")
            .layer(ADAPTER_LAYER)
            .definedBy("..adapters..")
            .layer(PORT_LAYER)
            .definedBy("..ports..")
            .layer(CONFIG_LAYER)
            .definedBy("..configuration..")
            .whereLayer(ADAPTER_LAYER)
            .mayOnlyAccessLayers(PORT_LAYER, DOMAIN_LAYER)
            .whereLayer(USECASE_LAYER)
            .mayOnlyAccessLayers(PORT_LAYER, DOMAIN_LAYER)
            .whereLayer(PORT_LAYER)
            .mayOnlyAccessLayers(ADAPTER_LAYER, DOMAIN_MODEL_LAYER)

    companion object {
        private const val DOMAIN_MODEL_LAYER: String = "DomainModel"
        private const val USECASE_LAYER: String = "Usecases"
        private const val ADAPTER_LAYER: String = "Adapters"
        private const val PORT_LAYER: String = "Ports"
        private const val CONFIG_LAYER: String = "Config"
        private const val DOMAIN_LAYER: String = "Domain"
    }
}
