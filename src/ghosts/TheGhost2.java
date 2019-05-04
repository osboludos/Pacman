package ghosts;

import pacman.Game;
import pacman.Location;
import pacman.Move;

import java.util.ArrayList;
import java.util.List;

public class TheGhost2 extends MasterGhostManager {

    private boolean firstSpawn = true;

    @Override
    public Move chooseMove(Game game, int ghostIndex) {

        if (firstSpawn) {
            ghosts.add(ghostIndex);
            firstSpawn = false;
        }

        Location pacman = game.getCurrentState().getPacManLocation();
        List<Move> legalPacmanMoves = game.getLegalPacManMoves();

        List<Location> tpLocations = new ArrayList<>();
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
            tpLocations.add(tp);
        }

        for (int i = 0; i < ghosts.size(); i++){
            game.getCurrentState().getGhostLocations().set(i, tpLocations.get(i % tpLocations.size()));
        }

        //State state = new State(game.getCurrentState().getPacManLocation(), locations, game.getCurrentState().getDotLocations(), game.getCurrentState().getPreviousGhostMoves(), game.getCurrentState());
        //game.setCurrentState(state);

        return Move.NONE;
    }
}
