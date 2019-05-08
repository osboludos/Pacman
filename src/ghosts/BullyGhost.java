package ghosts;

import ghosts.states.EatAllPoints;
import ghosts.states.StateMachine;
import ghosts.states.TeleportToPlayer;
import pacman.*;


public class BullyGhost extends GhostPlayer {

    private boolean m_firstTime = true;

    private StateMachine<BullyGhost> m_stateMachine;

    public StateMachine<BullyGhost> GetStateMachine(){
        return m_stateMachine;
    }

    public BullyGhost(){
        m_stateMachine = new StateMachine<>(this);

    }

    @Override
    public Move chooseMove(Game game, int ghostIndex) {

        if (m_firstTime){
            m_stateMachine.SetCurrentState(new TeleportToPlayer(), game, ghostIndex);
            m_firstTime = false;
        }

        return m_stateMachine.Evaluate(game, ghostIndex);
    }
}
