package by.free.home.touristtelegrambot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
