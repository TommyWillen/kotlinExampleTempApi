package com.example.demo.entity

import jakarta.persistence.*
import java.time.OffsetDateTime

@Table(name = "temperature_reading")
@Entity
class TemperatureReading(
    @Column(name = "fahrenheit", nullable = false) var fahrenheit: Double = 0.0,
    @Column(name = "celsius", nullable = false) var celsius: Double = 0.0
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int = 0

    @Column(name = "collected_at", nullable = false)
    var collectedAt: OffsetDateTime = OffsetDateTime.now()
}