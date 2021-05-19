package by.free.home.touristtelegrambot.service.impl;

import by.free.home.touristtelegrambot.entity.City;
import by.free.home.touristtelegrambot.entity.exception.EntityAlreadyExistException;
import by.free.home.touristtelegrambot.repository.CityRepository;
import by.free.home.touristtelegrambot.service.CityService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CityServiceImplTest {

    @Autowired
    private CityService cityService;
    @Autowired
    private CityRepository cityRepository;
    private City expectedCity;

    @BeforeEach
    void setUp() {
        expectedCity = new City(1, "test", "2021", 2000, "test");
    }

    @Test
    @Order(1)
    void saveCityToDatabase() {
        City actualCity = (City) cityService.saveCityToDatabase(expectedCity);
        assertEquals(expectedCity, actualCity);
    }

    @Test
    @Order(2)
    void deleteFromDatabaseByName() {
        String cityName = (String) expectedCity.getName();
        cityService.deleteFromDatabaseByName(cityName);
        boolean actualExists = (boolean) cityRepository.existsByName(cityName);
        assertFalse(actualExists);
    }

    @Test
    @Order(3)
    void updateCity() {
        cityService.saveCityToDatabase(expectedCity);
        City expectedCityAfterUpdate = new City(2, "Newname", "2030", 3000, "Newinfo");
        String cityName = (String) expectedCity.getName();
        City updatedActualCity = (City) cityService.updateCity(cityName, expectedCityAfterUpdate);
        assertEquals(expectedCityAfterUpdate, updatedActualCity);
    }

    @Test
    @Order(4)
    void contains() {
        cityService.saveCityToDatabase(expectedCity);
        Throwable thrown = assertThrows(EntityAlreadyExistException.class,
                ()->cityService.contains(expectedCity));
        assertNotNull(thrown.getMessage());
    }

    @Test
    @Order(5)
    void getCityByName() {
        expectedCity.setId(3);
        String cityName = (String) expectedCity.getName();
        City actualCity = (City) cityService.getCityByName(cityName);
        assertEquals(expectedCity, actualCity);
    }

    @Test
    @Order(6)
    void updateCityName() {
        String expectedNameAfterUpdate = "City";
        String cityOldName = (String) expectedCity.getName();
        City actualUpdatedCity = (City) cityService.updateCityName(cityOldName, expectedNameAfterUpdate);
        String actualNameAfterUpdate = (String) actualUpdatedCity.getName();
        assertEquals(expectedNameAfterUpdate.toLowerCase(), actualNameAfterUpdate);
    }

    @Test
    @Order(7)
    void updateYearOfFoundation() {
        cityService.saveCityToDatabase(expectedCity);
        String expectedYearAfterUpdate = "3030";
        String cityName = (String) expectedCity.getName();
        City actualUpdatedCity = (City) cityService.updateYearOfFoundation(cityName, expectedYearAfterUpdate);
        String actualYearAfterUpdate = (String) actualUpdatedCity.getYearOfFoundation();
        assertEquals(expectedYearAfterUpdate, actualYearAfterUpdate);
    }

    @Test
    @Order(8)
    void updatePopulation() {
        long expectedPopulationAfterUpdate = 100;
        String cityName = (String) expectedCity.getName();
        City actualUpdatedCity = (City) cityService.updatePopulation(cityName, expectedPopulationAfterUpdate);
        long actualPopulationAfterUpdate = (long) actualUpdatedCity.getPopulation();
        assertEquals(expectedPopulationAfterUpdate, actualPopulationAfterUpdate);
    }

    @Test
    @Order(9)
    void updateInformation() {
        String expectedInfoAfterUpdate = "Test info";
        String cityName = (String) expectedCity.getName();
        City actualUpdatedCity = (City) cityService.updateInformation(cityName, expectedInfoAfterUpdate);
        String actualInfoAfterUpdate = (String) actualUpdatedCity.getInformation();
        assertEquals(expectedInfoAfterUpdate, actualInfoAfterUpdate);
    }

    @Test
    @Order(10)
    void getAllCitiesFromDatabase() {
        int expectedSize = 3;
        int actualSize = (int) cityService.getAllCitiesFromDatabase().size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    @Order(11)
    void getOptionalCityByName() {
        expectedCity.setId(4);
        expectedCity.setInformation("Test info");
        expectedCity.setPopulation(100);
        expectedCity.setYearOfFoundation("3030");
        String cityName = (String) expectedCity.getName();
        Optional<City> optionalCityByName = (Optional<City>) cityService.getOptionalCityByName(cityName);
        City actualCityFromOptional = (City) optionalCityByName.get();
        assertEquals(expectedCity, actualCityFromOptional);

    }
}