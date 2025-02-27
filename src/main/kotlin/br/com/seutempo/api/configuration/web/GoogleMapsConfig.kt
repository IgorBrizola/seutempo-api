package br.com.seutempo.api.configuration.web

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class GoogleMapsConfig {
    @Value("\${app.service.google.maps.key}")
    lateinit var apiKey: String

    @Value("\${app.service.google.maps.url}")
    lateinit var apiUrl: String
}
