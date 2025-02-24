package br.com.seutempo.api.adapters.integration.model.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GeolocationResponse(
    val results: List<Result>,
    val status: String,
)

data class Result(
    @JsonProperty("address_components")
    val addressComponents: List<AddressComponent>? = null,
    @JsonProperty("formatted_address")
    val formattedAddress: String? = null,
    val geometry: Geometry,
    @JsonProperty("place_id")
    val placeId: String? = null,
    @JsonProperty("postcode_localities")
    val postcodeLocalities: List<String>? = null,
    val types: List<String>? = null,
)

data class AddressComponent(
    @JsonProperty("long_name")
    val longName: String,
    @JsonProperty("short_name")
    val shortName: String,
    val types: List<String>,
)

data class Geometry(
    val bounds: Bounds,
    val location: Location,
    @JsonProperty("location_type")
    val locationType: String,
    val viewport: Viewport,
)

data class Bounds(
    val northeast: Northeast,
    val southwest: Southwest,
)

data class Northeast(
    val lat: Double,
    val lng: Double,
)

data class Southwest(
    val lat: Double,
    val lng: Double,
)

data class Location(
    val lat: Double,
    val lng: Double,
)

data class Viewport(
    val northeast: Northeast2,
    val southwest: Southwest2,
)

data class Northeast2(
    val lat: Double,
    val lng: Double,
)

data class Southwest2(
    val lat: Double,
    val lng: Double,
)
