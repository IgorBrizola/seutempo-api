package br.com.seutempo.api.model.address

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "address")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    @Column(name = "cep")
    val cep: String?,
    @Column(name = "state")
    val state: String?,
    @Column(name = "city")
    val city: String?,
    @Column(name = "neighborhood")
    val neighborhood: String?,
    @Column(name = "street")
    val street: String?,
    @Column(name = "number")
    val number: Int?,
    @Column(name = "complement")
    val complement: String?,
    @Column(name = "additional_address")
    val additionalAddress: String?,
    @Column(name = "type_address")
    @Enumerated(EnumType.STRING)
    val typeAddress: TypeAddress?,
)

enum class TypeAddress { RESIDENTIAL, COMMERCIAL }
