package nl.inholland.Assignment1.Game.Library.REST.API.services;

import nl.inholland.Assignment1.Game.Library.REST.API.models.Game;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class GameServiceJPA implements GameService {

    private List<Game> games = new ArrayList<Game>();

    public GameServiceJPA() {
        games.add(new Game(1L, "The Legend of Zelda: Breath of the Wild", "Action-adventure", "Nintendo Switch"));
        games.add(new Game(2L, "The Witcher 3: Wild Hunt", "Action role-playing", "PC, PS4, Xbox One, Nintendo Switch"));
        games.add(new Game(3L, "Minecraft", "Sandbox", "PC, PS4, Xbox One, Nintendo Switch"));
        games.add(new Game(4L, "Dark Souls III", "Action role-playing", "PC, PS4, Xbox One"));
        games.add(new Game(5L, "Helldivers II", "Action", "PS4, PC"));
    }

    @Override
    public Game addGame(Game game) {
        games.add(game);
        return game;
    }

    @Override
    public void removeGame(Game game) {
        games.remove(game);
    }

    @Override
    public Game updateGame(Game game) {
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getId().equals(game.getId())) {
                games.set(i, game);
                return game;
            }
        }
        return game;
    }

    @Override
    public Game getGameById(Long id) {
        return games.stream()
                .filter(game -> game.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Game> getGames() {
        return games;
    }
}
