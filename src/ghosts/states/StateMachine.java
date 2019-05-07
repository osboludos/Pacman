package ghosts.states;

import pacman.Game;
import pacman.Move;

public class StateMachine<GhostType> {

    private GhostType m_owner;

    private State<GhostType> m_lastState;
    private State<GhostType> m_currentState;
    private State<GhostType> m_globalState;

    public StateMachine(GhostType owner){
        m_owner = owner;
        m_lastState = null;
        m_currentState = null;
        m_globalState = null;
    }

    public void SetCurrentState(State<GhostType> nextState, Game game, int ghostIndex){
        if (m_currentState != null){
            m_currentState.Exit(game, ghostIndex, m_owner);
        }

        m_lastState = m_currentState;
        m_currentState = nextState;
        m_currentState.Enter(game, ghostIndex, m_owner);
    }

    public void SetGlobalState(State<GhostType> state, Game game, int ghostIndex){
        if (m_globalState != null){
            m_globalState.Exit(game, ghostIndex, m_owner);
        }
        m_globalState = state;
        m_globalState.Enter(game, ghostIndex, m_owner);
    }

    public Move Evaluate(Game game, int ghostIndex){
        if (m_globalState != null){
            return m_globalState.Run(game, ghostIndex, m_owner);
        }
        return m_currentState.Run(game, ghostIndex, m_owner);

    }

    public void Revert(Game game, int ghostIndex){
        SetCurrentState(m_lastState, game, ghostIndex);
    }

    public State GetLastState() {
        return m_lastState;
    }

    public State GetCurrentState() {
        return m_currentState;
    }

    public State GetGlobalState() {
        return m_globalState;
    }

}
