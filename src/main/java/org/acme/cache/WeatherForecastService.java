package org.acme.cache;

import java.time.LocalDate;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import io.quarkus.cache.CacheResult;

@ApplicationScoped
public class WeatherForecastService {

    @Inject
    CityNameService cityNameService;

    @CacheResult(cacheName = "weather-cache", lockTimeout = 0)
    public String getDailyForecast(LocalDate date, String cityAbbreviation) {

        final String city = cityNameService.getCity(cityAbbreviation);

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return date.getDayOfWeek() + " will be " + getDailyResult(date.getDayOfMonth() % 4) + " in " + city;
    }

    private String getDailyResult(int dayOfMonthModuloFour) {
        switch (dayOfMonthModuloFour) {
            case 0:
                return "sunny";
            case 1:
                return "cloudy";
            case 2:
                return "chilly";
            case 3:
                return "rainy";
            default:
                throw new IllegalArgumentException();
        }
    }
}
