package br.com.seutempo.api.model.professional

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "professional")
data class Professional(
    @OneToOne
    @JoinColumn(name = "id")
    val id: Int?,
    val linkProfessional: String?,
    val valueHour: BigDecimal?,
)
