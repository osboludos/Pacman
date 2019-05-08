package ghosts.states;

import ghosts.BillyGhost;
import ghosts.GhostHelper;
import pacman.Game;
import pacman.Location;
import pacman.Move;

public class WalkAround implements State<BillyGhost> {


    Location m_target1, m_target2, m_current;

    @Override
    public void Enter(Game game, int ghost, BillyGhost billyGhost) {
        System.out.println("What a nice day to be dead and do nothing!");
        m_target1 = new Location(0, 0);
        m_target2 = new Location(15, 4);

        m_current = m_target1;
    }

    @Override
    public Move Run(Game game, int ghost, BillyGhost billyGhost) {

        if (game.getCurrentState().getGhostLocations().get(ghost).equals(m_current)){
            if (m_current.equals(m_target1)){
                m_current = m_target2;
            } else {
                m_current = m_target1;
            }
        }

        System.out.println("Walking around... going to location " + m_current);
        return GhostHelper.ChooseMove(game,ghost, m_current);
    }

    @Override
    public void Exit(Game game, int ghost, BillyGhost billyGhost) {

    }
}
