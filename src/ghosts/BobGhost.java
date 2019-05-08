package ghosts;

import ghosts.states.PersuePacman;
import ghosts.states.StateMachine;
import ghosts.states.WalkAround;
import pacman.*;
import util.Pair;

import java.util.ArrayList;
import java.util.List;

public class BobGhost extends GhostPlayer {

    private boolean m_firstTime = true;

    private StateMachine<BobGhost> m_stateMachine;

    public StateMachine<BobGhost> GetStateMachine() {
        return m_stateMachine;
    }

    public BobGhost() {
        m_stateMachine = new StateMachine<>(this);
    }

    @Override
    public Move chooseMove(Game game, int ghostIndex) {
        if (m_firstTime) {
            m_stateMachine.SetCurrentState(new PersuePacman(), game, ghostIndex);
            m_firstTime = false;
        }

        return m_stateMachine.Evaluate(game, ghostIndex);
    }
}
