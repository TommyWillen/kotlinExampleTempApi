package com.example.demo.controller

import com.example.demo.entity.TemperatureReading
import com.example.demo.generated.types.TemperatureType
import com.example.demo.service.TemperatureService
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class TemperatureController(private val temperatureService: TemperatureService) {

    /*
    I also created a query to find the temperature reading by id for user testing purposes
     */
    @DgsQuery
    fun temperatureReadingById(@InputArgument id: Int): TemperatureReading {
        return temperatureService.getTemperatureReadingById(id)
    }

    /* I decided to return the entire TemperatureReading object because the frontend can decide which data they want
    * returned from their mutation. I included the exact mutation I want returned from the mutation in the README for
    * either of the tasks. I also decided to create one endpoint
    */
    @DgsMutation
    fun recordTemperatureReading(@InputArgument temp: Double, @InputArgument type: TemperatureType): TemperatureReading {
        /* since this is only one table I would directly return the entity, but if there were more than one tables with
        * foreign key relationships I would have included a helper function on the TemperatureReading class called
        * .toDto() that would convert the entity to the generated DGS DTO object. The benefit of this is that it
        * allows me to utilize data fetchers on the related tables so only the tables we want are queried and returned
        * which improves performance for queries where fewer tables are queried
         */
        return if (type == TemperatureType.CELSIUS) {
            temperatureService.recordCelsiusReading(temp)
        } else if (type == TemperatureType.FAHRENHEIT) {
            temperatureService.recordFahrenheitReading(temp)
        } else {
            throw RuntimeException("Invalid temperature type provided")
        }
    }
}