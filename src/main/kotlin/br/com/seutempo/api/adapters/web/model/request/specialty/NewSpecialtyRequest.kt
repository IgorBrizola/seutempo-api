package br.com.seutempo.api.adapters.web.model.request.specialty

data class NewSpecialtyRequest(
    val name: String,
    val categoryId: Int,
)
