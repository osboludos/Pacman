package ghosts.states;

import pacman.Game;
import pacman.Move;

public interface State<GhostType> {

    void Enter(Game game, int ghost, GhostType ghostType);
    Move Run(Game game, int ghost, GhostType ghostType);
    void Exit(Game game, int ghost, GhostType ghostType);

}
