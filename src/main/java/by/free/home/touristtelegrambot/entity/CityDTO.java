package by.free.home.touristtelegrambot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {
    private String name;
    private String yearOfFoundation;
    private long population;
    private String information;
}
