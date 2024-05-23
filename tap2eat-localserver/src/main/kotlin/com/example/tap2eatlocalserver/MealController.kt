package com.example.tap2eatlocalserver

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@RestController
class MealController(
    private val mealsRepository: MealsRepository
) {

    @PostMapping(value = ["/api/meals"])
    fun createCustomer(
        @RequestBody request: MealRequest
    ): MealClaimedReponse {

        val claimedMeal: Optional<Meal> = this.mealsRepository.findById(request.studentId)

        if (claimedMeal.isEmpty) {
            val now = ZonedDateTime.now()
            val isoDateTime = now.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
            this.mealsRepository.save(
                Meal(
                    request.studentId,
                    request.clientId,
                    isoDateTime
                )
            )
            return MealClaimedReponse(false)
        } else {
            return MealClaimedReponse(true)
        }

    }

}

data class MealClaimedReponse(
    val claimed: Boolean
)