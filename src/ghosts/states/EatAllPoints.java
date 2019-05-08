package ghosts.states;

import ghosts.GhostHelper;
import ghosts.HungryGhost;
import pacman.Game;
import pacman.Location;
import pacman.LocationSet;
import pacman.Move;

public class EatAllPoints  implements State<HungryGhost> {

    Location m_target1, m_target2, m_lastTarget, m_currentTarget;
    @Override
    public void Enter(Game game, int ghost, HungryGhost ghostType) {
        game.getCurrentState().getDotLocations().removeAll(game.getCurrentState().getDotLocations());

        LocationSet set = new LocationSet();
        set.add(new Location(0,28));
        m_target1 = new Location(1, 28);
        m_target2 = new Location(0, 27);

        game.getCurrentState().getDotLocations().addAll(set);
        game.getCurrentState().getGhostLocations().set(ghost, m_target1);
        System.out.println("Vim aqui");
        m_lastTarget = m_target1;
        m_currentTarget = m_target2;
    }

    @Override
    public Move Run(Game game, int ghost, HungryGhost ghostType) {

        if (game.getCurrentState().getGhostLocations().get(ghost).equals(m_currentTarget)){
            if (m_currentTarget == m_target1){
                m_currentTarget = m_target2;
            }
            else {
                m_currentTarget = m_target1;
            }
        }
        return GhostHelper.ChooseMove(game, ghost, m_currentTarget);
    }

    @Override
    public void Exit(Game game,int ghost, HungryGhost ghostType) {

    }
}
