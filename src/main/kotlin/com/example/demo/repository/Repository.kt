package com.example.demo.repository

import com.example.demo.entity.TemperatureReading
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TemperatureRepository : JpaRepository<TemperatureReading, Int>, JpaSpecificationExecutor<TemperatureReading> {
    override fun findById(id: Int): Optional<TemperatureReading>


}