package ghosts;

import ghosts.states.StateMachine;
import ghosts.states.WalkAround;
import pacman.*;
import util.Pair;

import java.util.ArrayList;
import java.util.List;

public class BillyGhost extends GhostPlayer {

    private boolean m_firstTime = true;

    private StateMachine<BillyGhost> m_stateMachine;

    public StateMachine<BillyGhost> GetStateMachine(){
        return m_stateMachine;
    }

    public BillyGhost(){
        m_stateMachine = new StateMachine<>(this);
    }

    @Override
    public Move chooseMove(Game game, int ghostIndex) {
        if (m_firstTime){
            m_stateMachine.SetCurrentState(new WalkAround(), game, ghostIndex);
            m_firstTime = false;
        }

        return m_stateMachine.Evaluate(game, ghostIndex);
    }
}
