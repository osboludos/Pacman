package ghosts;

import pacman.*;
import util.Pair;

import java.util.ArrayList;
import java.util.List;

public class TheGhost4 extends GhostPlayer {

    private boolean firstSpawn = true;

    @Override
    public Move chooseMove(Game game, int ghostIndex) {


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

        game.getCurrentState().getGhostLocations().set(ghostIndex, tpLocations.get(ghostIndex % tpLocations.size()).first);

        return tpLocations.get(ghostIndex % tpLocations.size()).second;
        //State state = new State(game.getCurrentState().getPacManLocation(), locations, game.getCurrentState().getDotLocations(), game.getCurrentState().getPreviousGhostMoves(), game.getCurrentState());
        //game.setCurrentState(state);

    }
}
