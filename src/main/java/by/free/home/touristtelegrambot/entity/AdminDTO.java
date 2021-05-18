package by.free.home.touristtelegrambot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
    @Pattern(regexp = "^[A-Za-z]{3,15}[0-9]{0,5}$",
            message = "Wrong format! Use characters (3 - 15) and numbers(0 - 5)!")
    private String adminName;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$",
            message = "Min 8 symbols, min 1 upper and lower case, without whitespace!")
    private String password;
}
