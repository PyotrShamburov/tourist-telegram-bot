package by.free.home.touristtelegrambot.service;

import by.free.home.touristtelegrambot.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityService {
    City saveCityToDatabase(City city);
    void deleteFromDatabaseByName(String name);
    City updateCity(String name, City newCity);
    void contains(City city);
    City getCityByName(String name);
    City updateCityName(String name, String newName);
    City updateYearOfFoundation(String name, String newYear);
    City updatePopulation(String name, long newPopulation);
    City updateInformation(String name, String newInformation);
    List<City> getAllCitiesFromDatabase();
    Optional<City> getOptionalCityByName(String name);
}
