package com.example.tap2eatlocalserver

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.jpa.repository.JpaRepository


interface MealsRepository : JpaRepository<Meal, String> {
}

@Entity
class Meal(
    @Id val studentId: String,
    val clientId: String,
    val timestamp: String
)