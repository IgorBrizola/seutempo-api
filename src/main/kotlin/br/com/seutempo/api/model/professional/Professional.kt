package br.com.seutempo.api.model.professional

import br.com.seutempo.api.model.specialty.Specialty
import br.com.seutempo.api.model.users.Users
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "professional")
data class Professional(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @OneToOne(cascade = [CascadeType.ALL], optional = false)
    @JoinColumn(name = "id_users")
    val user: Users,
    @Column(name = "link_professional")
    val linkProfessional: String,
    @Column(name = "value_hour")
    val valueHour: BigDecimal,
    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinTable(
        name = "professional_specialty",
        joinColumns = [JoinColumn(name = "id_professional")],
        inverseJoinColumns = [JoinColumn(name = "id_specialty")],
    )
    val specialties: List<Specialty>,
)
