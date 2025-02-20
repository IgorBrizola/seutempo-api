package br.com.seutempo.api.integration.client

import br.com.seutempo.api.integration.response.GeolocationResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "GoogleMapsClient", url = "https://maps.googleapis.com/maps/api/geocode/json")
interface GoogleMapsClient {
    @GetMapping
    fun getGeolocationUser(
        @RequestParam("address") address: String,
        @RequestParam("key") key: String,
    ): GeolocationResponse
}
