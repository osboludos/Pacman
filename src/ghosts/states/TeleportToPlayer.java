package ghosts.states;

import ghosts.BullyGhost;
import pacman.Game;
import pacman.Location;
import pacman.Move;
import util.Pair;

import java.util.ArrayList;
import java.util.List;

public class TeleportToPlayer implements State<BullyGhost> {
    @Override
    public void Enter(Game game, int ghost, BullyGhost o) {
        Location pacman = game.getCurrentState().getPacManLocation();
        List<Move> legalPacmanMoves = game.getLegalPacManMoves();

        List<Pair<Location, Move>> tpLocations = new ArrayList<>();
        for (Move m : legalPacmanMoves){
            Location tp = null;
            switch (m) {
                case NONE:
                    continue;
                case UP:
                    tp = new Location(pacman.getX(), pacman.getY() + 1);
                    break;
                case DOWN:
                    tp = new Location(pacman.getX(), pacman.getY() - 1);
                    break;
                case LEFT:
                    tp = new Location(pacman.getX() - 1, pacman.getY());
                    break;
                case RIGHT:
                    tp = new Location(pacman.getX() + 1, pacman.getY());
                    break;
            }
            tpLocations.add(new Pair<>(tp, m.getOpposite()));
        }

        game.getCurrentState().getGhostLocations().set(ghost, tpLocations.get(ghost % tpLocations.size()).first);
    }

    @Override
    public Move Run(Game game, int ghost, BullyGhost o) {
        Location pacman = game.getCurrentState().getPacManLocation();
        List<Move> legalPacmanMoves = game.getLegalPacManMoves();

        List<Pair<Location, Move>> tpLocations = new ArrayList<>();
        for (Move m : legalPacmanMoves){
            Location tp = null;
            switch (m) {
                case NONE:
                    continue;
                case UP:
                    tp = new Location(pacman.getX(), pacman.getY() + 1);
                    break;
                case DOWN:
                    tp = new Location(pacman.getX(), pacman.getY() - 1);
                    break;
                case LEFT:
                    tp = new Location(pacman.getX() - 1, pacman.getY());
                    break;
                case RIGHT:
                    tp = new Location(pacman.getX() + 1, pacman.getY());
                    break;
            }
            tpLocations.add(new Pair<>(tp, m.getOpposite()));
        }

        game.getCurrentState().getGhostLocations().set(ghost, tpLocations.get(ghost % tpLocations.size()).first);

        return tpLocations.get(ghost % tpLocations.size()).second;
    }

    @Override
    public void Exit(Game game, int ghost, BullyGhost o) {

    }
}
