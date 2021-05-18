package by.free.home.touristtelegrambot.resource;

import by.free.home.touristtelegrambot.entity.City;
import by.free.home.touristtelegrambot.entity.CityDTO;
import by.free.home.touristtelegrambot.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/city")
@Slf4j
public class CityResource {
    @Autowired
    private CityService cityService;

    @PostMapping(path = "/add")
    public ResponseEntity<City> createNewCity(@Valid @RequestBody City city) {
        City savedCity = (City) cityService.saveCityToDatabase(city);
        log.info("Request to add new city to database " + city + ".");
        return new ResponseEntity<>(savedCity, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{cityName}")
    public ResponseEntity<String> deleteCityByName(@PathVariable String cityName) {
        String regex = "^[A-Z]?[a-z]{3,15}$";
        if (cityName.matches(regex)) {
            cityService.deleteFromDatabaseByName(cityName);
            log.info("Request for delete city from database by name! City's name [" + cityName + "].");
            return new ResponseEntity<>(cityName.toUpperCase() + " - DELETED!", HttpStatus.OK);
        } else {
            log.error("Name is not valid! City's name [" + cityName + "].");
            return new ResponseEntity<>("Invalid name supplied!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{cityName}")
    public ResponseEntity<City> getCityByName(@PathVariable String cityName) {
        String regex = "^[A-Z]?[a-z]{3,15}$";
        if (cityName.matches(regex)) {
            City cityByName = (City) cityService.getCityByName(cityName);
            log.info("Request for find city in database by name! City's name [" + cityName + "].");
            return new ResponseEntity<>(cityByName, HttpStatus.OK);
        } else {
            log.error("City's name is not valid! Name [" + cityName + "].");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/update/{cityName}")
    public ResponseEntity<City> updateAllCityInfo(@PathVariable String cityName, @Valid @RequestBody City newCity) {
        City updatedCity = (City) cityService.updateCity(cityName, newCity);
        log.info("Request for update existing city in database by name!" +
                " City's name [" + cityName + "].");
        return new ResponseEntity<>(updatedCity, HttpStatus.OK);
    }

    @PutMapping(path = "/update/name/{cityName}")
    public ResponseEntity<City> updateNameOfCity(@PathVariable String cityName,@RequestBody CityDTO cityDTO) {
        String nameRegex = "^[A-Z]?[a-z]{3,15}$";
        String newName = (String) cityDTO.getName();
        if (cityName.matches(nameRegex) && newName.matches(nameRegex)) {
            City updatedCity = (City) cityService.updateCityName(cityName, newName);
            log.info("Request for update name of existing city in database by name!" +
                    " City's name [" + cityName + "].");
            return new ResponseEntity<>(updatedCity, HttpStatus.OK);
        } else {
            log.error("City's old name or new name or is not valid! Old name [" + cityName + "]." +
                    " New name [" + newName + "].");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/update/year/{cityName}")
    public ResponseEntity<City> updateYearOfFoundation(@PathVariable String cityName, @RequestBody CityDTO cityDTO) {
        String yearRegex = "^[0-9]{3,4}$";
        String nameRegex = "^[A-Z]?[a-z]{3,15}$";
        String newYear = (String) cityDTO.getYearOfFoundation();
        if (cityName.matches(nameRegex) && newYear.matches(yearRegex)) {
            City updatedCity = (City) cityService.updateYearOfFoundation(cityName, newYear);
            log.info("Request for update year of existing city in database by name!" +
                    " City's name [" + cityName + "].");
            return new ResponseEntity<>(updatedCity, HttpStatus.OK);
        } else {
            log.error("City's name or year is not valid! City's name [" + cityName + "]." +
                    " New year [" + newYear + "].");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/update/population/{cityName}")
    public ResponseEntity<City> updatePopulationOfCity(@PathVariable String cityName, @RequestBody CityDTO cityDTO) {
        String nameRegex = "^[A-Z]?[a-z]{3,15}$";
        long newPopulation = (long) cityDTO.getPopulation();
        if (cityName.matches(nameRegex) && newPopulation > 0) {
            City updatedCity = (City) cityService.updatePopulation(cityName, newPopulation);
            log.info("Request for update population of existing city in database by name!" +
                    " City's name [" + cityName + "].");
            return new ResponseEntity<>(updatedCity, HttpStatus.OK);
        } else {
            log.error("City's name or new population is not valid! City's name [" + cityName + "]." +
                    " New population [" + newPopulation + "].");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/update/information/{cityName}")
    public ResponseEntity<City> updateInfoOfCity(@PathVariable String cityName, @RequestBody CityDTO cityDTO) {
        String nameRegex = "^[A-Z]?[a-z]{3,15}$";
        String newInformation = (String) cityDTO.getInformation();
        if (cityName.matches(nameRegex)) {
            City updatedCity = (City) cityService.updateInformation(cityName, newInformation);
            log.info("Request for update information of existing city in database by name!" +
                    " City's name [" + cityName + "].");
            return new ResponseEntity<>(updatedCity, HttpStatus.OK);
        } else {
            log.error("City's name is not valid! City's name [" + cityName + "]." +
                    " New information [" + newInformation + "].");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(path = "/all")
    public ResponseEntity<List<City>> getAllCities() {
        List<City> allCitiesFromDatabase = (List<City>) cityService.getAllCitiesFromDatabase();
        log.info("Request for get all cities from database! Amount of cities :"+allCitiesFromDatabase.size());
        return new ResponseEntity<>(allCitiesFromDatabase, HttpStatus.OK);
    }


}
