package nl.inholland.Assignment1.Game.Library.REST.API.controllers;

import nl.inholland.Assignment1.Game.Library.REST.API.models.Game;
import nl.inholland.Assignment1.Game.Library.REST.API.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<Game>> getGames() {
        return ResponseEntity.ok(gameService.getGames());
    }


    @GetMapping("{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        Game game = gameService.getGameById(id);
        if (game == null) {
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity.ok(game); // 200
    }


    @PostMapping
    public ResponseEntity<Game> addGame(@RequestBody Game game) {
        if (game.getTitle() == null || game.getTitle().isBlank()) {
            return ResponseEntity.badRequest().build(); // 400
        }

        boolean alreadyExists = gameService.getGames().stream()
                .anyMatch(g -> g.getTitle().equalsIgnoreCase(game.getTitle()) &&
                        g.getPlatform().equalsIgnoreCase(game.getPlatform()));

        if (alreadyExists) {
            return ResponseEntity.badRequest().body(null); // 400
        }

        if (game.getId() == null) {
            List<Game> games = gameService.getGames();
            Long maxId = games.stream()
                    .mapToLong(Game::getId)
                    .max()
                    .orElse(0L);
            game.setId(maxId + 1);
        }

        Game savedGame = gameService.addGame(game);
        return ResponseEntity.status(201).body(savedGame); // 201
    }


    @PutMapping("{id}")
    public ResponseEntity<Game> updateGame(@RequestBody Game game, @PathVariable Long id) {
        Game existingGame = gameService.getGameById(id);
        if (existingGame == null) {
            return ResponseEntity.notFound().build(); // 404
        }

        if (game.getTitle() == null || game.getTitle().isBlank()) {
            return ResponseEntity.badRequest().build(); // 400
        }

        existingGame.setTitle(game.getTitle());
        existingGame.setGenre(game.getGenre());
        existingGame.setPlatform(game.getPlatform());

        return ResponseEntity.ok(gameService.updateGame(existingGame)); // 200
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        Game game = gameService.getGameById(id);
        if (game == null) {
            return ResponseEntity.notFound().build(); // 404
        }

        gameService.removeGame(game);
        return ResponseEntity.noContent().build(); // 204
    }
}
