package br.com.seutempo.api.adapters.web

import br.com.seutempo.api.adapters.web.doc.ReviewOpenAPI
import br.com.seutempo.api.adapters.web.mapper.review.ReviewMapper
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("reviews")
class RestReviewsController(
    private val reviewMapper: ReviewMapper,
) : ReviewOpenAPI
