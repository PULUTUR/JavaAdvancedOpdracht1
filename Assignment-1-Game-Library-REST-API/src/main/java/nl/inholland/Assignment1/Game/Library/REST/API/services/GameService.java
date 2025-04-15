package nl.inholland.Assignment1.Game.Library.REST.API.services;

import nl.inholland.Assignment1.Game.Library.REST.API.models.Game;

import java.util.List;

public interface GameService {
    Game addGame(Game game);
    void removeGame(Game game);
    Game updateGame(Game game);
    Game getGameById(Long id);
    List<Game> getGames();
}
