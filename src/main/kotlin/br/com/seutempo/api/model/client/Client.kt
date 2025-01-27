package br.com.seutempo.api.model.client

import br.com.seutempo.api.model.address.Address
import br.com.seutempo.api.model.users.Users
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "client")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @OneToOne
    @JoinColumn(name = "id")
    val user: Users,
    @Column(name = "surname")
    val surname: String?,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "address")
    val address: List<Address>,
)
