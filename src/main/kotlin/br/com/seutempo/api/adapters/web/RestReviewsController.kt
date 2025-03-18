package br.com.seutempo.api.adapters.web

import br.com.seutempo.api.adapters.web.doc.ReviewOpenAPI
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("reviews")
class RestReviewsController : ReviewOpenAPI
