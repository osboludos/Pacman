package ghosts.states;

import ghosts.BobGhost;
import ghosts.GhostHelper;
import pacman.Game;
import pacman.Move;

public class PersuePacman implements State<BobGhost> {
    @Override
    public void Enter(Game game, int ghost, BobGhost bobGhost) {
        System.out.println("Going to kill pacman...");
    }

    @Override
    public Move Run(Game game, int ghost, BobGhost bobGhost) {
        return GhostHelper.ChooseMove(game,ghost, game.getCurrentState().getPacManLocation());
    }

    @Override
    public void Exit(Game game, int ghost, BobGhost bobGhost) {

    }
}
