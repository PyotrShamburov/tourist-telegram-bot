package by.free.home.touristtelegrambot.repository;

import by.free.home.touristtelegrambot.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    boolean existsByName(String name);
    Optional<City> findByName(String name);
}
