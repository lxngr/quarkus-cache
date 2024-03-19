package org.acme.cache;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.cache.CacheResult;


@ApplicationScoped
public class CityNameService {

    @CacheResult(cacheName = "city-cache", lockTimeout = 0)
    public String getCity(String cityAbbreviation) {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return getCityName(cityAbbreviation);
    }

    private String getCityName(String cityAbbreviation) {
        switch (cityAbbreviation) {
            case "NY":
                return "New York";
            case "LA":
                return "Los Angeles";
            default:
                throw new IllegalArgumentException();
        }
    }
}
