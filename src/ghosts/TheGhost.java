package ghosts;

import pacman.*;

import java.util.ArrayList;
import java.util.List;

public class TheGhost extends GhostPlayer {

    @Override
    public Move chooseMove(Game game, int ghostIndex) {


        Location pacman = game.getCurrentState().getPacManLocation();
        Location tp;
        if (ghostIndex % 2 == 0){
             tp = new Location(pacman.getX() - 1, pacman.getY());
        } else {
            tp =  new Location(pacman.getX() + 1, pacman.getY());
        }

        game.getCurrentState().getGhostLocations().set(ghostIndex, tp);



        //State state = new State(game.getCurrentState().getPacManLocation(), locations, game.getCurrentState().getDotLocations(), game.getCurrentState().getPreviousGhostMoves(), game.getCurrentState());
        //game.setCurrentState(state);

        return Move.RIGHT;
    }
}
