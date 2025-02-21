package br.com.seutempo.api.model.specialty.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class UpdateSpecialtyRequest(
    val nameSpecialty: String?,
    val categoryId: Int?,
)
