package by.free.home.touristtelegrambot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class XToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long adminId;
    private String token;
}
