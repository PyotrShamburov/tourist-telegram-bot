package by.free.home.touristtelegrambot.service.impl;

import by.free.home.touristtelegrambot.entity.City;
import by.free.home.touristtelegrambot.entity.exception.EntityAlreadyExistException;
import by.free.home.touristtelegrambot.entity.exception.EntityNotFoundException;
import by.free.home.touristtelegrambot.repository.CityRepository;
import by.free.home.touristtelegrambot.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public City saveCityToDatabase(City city) {
        contains(city);
        String formattedName = (String) city.getName().toLowerCase();
        city.setName(formattedName);
        City savedCity = (City) cityRepository.save(city);
        log.info("New saved in database city: "+city);
        return city;
    }

    @Override
    public void deleteFromDatabaseByName(String name) {
        if (cityRepository.existsByName(name.toLowerCase())) {
            City cityByName = (City) getCityByName(name.toLowerCase());
            cityRepository.deleteById(cityByName.getId());
            log.info("City with name ["+name.toUpperCase()+"] has been deleted!");
        } else {
            log.warn("City with name ["+name.toUpperCase()+"] not found!");
            throw new EntityNotFoundException("City with name ["+name.toUpperCase()+"] not found!");
        }
    }

    @Override
    public City updateCity(String name, City newCity) {
        City cityByName = (City) getCityByName(name.toLowerCase());
        long cityId = (long) cityByName.getId();
        log.info("City for update with ID ["+cityId+"] : "+cityByName);
        newCity.setId(cityId);
        String formattedName = (String) newCity.getName().toLowerCase();
        newCity.setName(formattedName);
        City updatedCity = (City) cityRepository.save(newCity);
        log.info("Updated city with ID ["+cityId+"] : "+updatedCity);
        return updatedCity;

    }

    @Override
    public void contains(City city) {
        log.info("Check city for contains in database!");
        if (cityRepository.existsByName(city.getName().toLowerCase())) {
            log.warn("City with name ["+city.getName().toUpperCase()+"] already exists!");
            throw new EntityAlreadyExistException("City with name ["+city.getName().toUpperCase()+"]" +
                    " already exists!");
        }
        log.info("City with name ["+city.getName().toUpperCase()+"] not exists!");
    }

    @Override
    public City getCityByName(String name) {
        Optional<City> cityByName = (Optional<City>) cityRepository.findByName(name.toLowerCase());
        if (cityByName.isPresent()) {
            log.info("City with name ["+name+"] is present! City: "+cityByName.get());
            return cityByName.get();
        }
        log.warn("City with name ["+name.toUpperCase()+"] not found!");
        throw new EntityNotFoundException("City with name ["+name.toUpperCase()+"] not found!");
    }

    @Override
    public City updateCityName(String name, String newName) {
        City cityByName = (City) getCityByName(name.toLowerCase());
        log.info("City for update with old name ["+name+"] : "+cityByName);
        cityByName.setName(newName.toLowerCase());
        City updatedCity = (City) cityRepository.save(cityByName);
        log.info("Updated city with new name ["+newName+"] : "+updatedCity);
        return updatedCity;

    }

    @Override
    public City updateYearOfFoundation(String name, String newYear) {
        City cityByName = (City) getCityByName(name.toLowerCase());
        log.info("City for update with old year of foundation ["+name+"] : "+cityByName);
        cityByName.setYearOfFoundation(newYear);
        City updatedCity = (City) cityRepository.save(cityByName);
        log.info("Updated city with new year of foundation ["+newYear+"] : "+updatedCity);
        return updatedCity;
    }

    @Override
    public City updatePopulation(String name, long newPopulation) {
        City cityByName = (City) getCityByName(name.toLowerCase());
        log.info("City for update with old population ["+name+"] : "+cityByName);
        cityByName.setPopulation(newPopulation);
        City updatedCity = (City) cityRepository.save(cityByName);
        log.info("Updated city with new population ["+newPopulation+"] : "+updatedCity);
        return updatedCity;
    }

    @Override
    public City updateInformation(String name, String newInformation) {
        City cityByName = (City) getCityByName(name.toLowerCase());
        log.info("City for update with old information ["+name+"] : "+cityByName);
        cityByName.setInformation(newInformation);
        City updatedCity = (City) cityRepository.save(cityByName);
        log.info("Updated city with new information ["+newInformation+"] : "+updatedCity);
        return updatedCity;
    }

    @Override
    public List<City> getAllCitiesFromDatabase() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> getOptionalCityByName(String name) {
        return cityRepository.findByName(name.toLowerCase());
    }
}
