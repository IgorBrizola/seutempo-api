package br.com.seutempo.api.integration

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class GoogleMapsConfig {
    @Value("\${service.google.maps.key}")
    lateinit var apiKey: String
}
