package by.free.home.touristtelegrambot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Pattern(regexp = "^[A-Z]?[a-z]{3,15}$",
            message = "Wrong format! Only characters(3 - 15)!")
    private String name;
    @Pattern(regexp = "^[0-9]{3,4}$", message = "Must be from 3 to 4 digits!")
    private String yearOfFoundation;
    @Positive(message = "Only positive number!")
    private long population;
    private String information;

    @Override
    public String toString() {
        return  name.toUpperCase()+"\n"+
                "Founded : " + yearOfFoundation + " year.\n" +
                "Population : " + population + " th.peoples.\n"+
                "Info: " + information;
    }
}
