package br.com.seutempo.api.adapters.repository

import br.com.seutempo.api.adapters.repository.model.ProfessionalEntity
import br.com.seutempo.api.core.domain.model.users.Users
import org.springframework.data.jpa.domain.Specification
import java.math.BigDecimal

object ProfessionalJpaSpecs {
    fun containsNameProfessional(providedName: String): Specification<ProfessionalEntity> =
        Specification { root, _, criteriaBuilder ->
            val userJoin = root.join<ProfessionalEntity, Users>("user")
            criteriaBuilder.like(
                criteriaBuilder.lower(userJoin.get("user")),
                "%${providedName.lowercase()}%",
            )
        }

    fun containsMaxValueProfessional(providedMaxValue: BigDecimal): Specification<ProfessionalEntity> =
        Specification { root, _, criteriaBuilder ->
            criteriaBuilder.lessThanOrEqualTo(root.get("valueHour"), providedMaxValue)
        }

    fun containsMinValueProfessional(providedMinValue: BigDecimal): Specification<ProfessionalEntity> =
        Specification { root, _, criteriaBuilder ->
            criteriaBuilder.greaterThanOrEqualTo(root.get("valueHour"), providedMinValue)
        }

    fun containsMinValueAndMaxProfessional(
        providedMinValue: BigDecimal,
        providedMaxValue: BigDecimal,
    ): Specification<ProfessionalEntity> =
        Specification { root, _, criteriaBuilder ->
            criteriaBuilder.between(root.get("valueHour"), providedMinValue, providedMaxValue)
        }
}
