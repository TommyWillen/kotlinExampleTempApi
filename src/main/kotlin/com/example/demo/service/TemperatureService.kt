package com.example.demo.service

import com.example.demo.entity.TemperatureReading
import com.example.demo.repository.TemperatureRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.time.OffsetDateTime

@Service
class TemperatureService(private val temperatureRepository: TemperatureRepository) {

    fun getTemperatureReadingById(id: Int): TemperatureReading {
        return temperatureRepository.findById(id).orElseThrow {
            RuntimeException("No temperature reading with id $id found")
        }
    }

    fun recordCelsiusReading(temp: Double): TemperatureReading {
        val fahrenheitTemp = (temp * 1.8) + 32
        val entity = TemperatureReading(
            celsius = temp,
            fahrenheit = fahrenheitTemp
        ).apply { collectedAt = OffsetDateTime.now() }

        return temperatureRepository.save(entity)
    }

    fun recordFahrenheitReading(temp: Double): TemperatureReading {
        val celsiusTemp = (temp - 32)*5/9
        val entity = TemperatureReading(
            celsius = celsiusTemp,
            fahrenheit = temp
        ).apply { collectedAt = OffsetDateTime.now() }

        return temperatureRepository.save(entity)
    }
}