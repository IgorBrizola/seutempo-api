package br.com.seutempo.api.adapters.web.model.request.specialty

import io.swagger.v3.oas.annotations.media.Schema

data class NewSpecialtyRequest(
    @Schema(defaultValue = "Pedreiro")
    val name: String,
    @Schema(defaultValue = "1")
    val categoryId: Int,
)
