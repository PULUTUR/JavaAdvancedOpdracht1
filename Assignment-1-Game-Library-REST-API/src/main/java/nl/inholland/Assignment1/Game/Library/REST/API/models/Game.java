package nl.inholland.Assignment1.Game.Library.REST.API.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String genre;
    private String platform;

    public Game() {

    }
}
