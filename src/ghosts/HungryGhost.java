package ghosts;

import ghosts.states.EatAllPoints;
import ghosts.states.StateMachine;
import pacman.*;
import util.Pair;

import java.util.ArrayList;
import java.util.List;

public class HungryGhost extends GhostPlayer {


    private boolean m_firstTime = true;

    private StateMachine<HungryGhost> m_stateMachine;

    public StateMachine<HungryGhost> GetStateMachine(){
        return m_stateMachine;
    }

    public HungryGhost(){
        m_stateMachine = new StateMachine<>(this);

    }

    @Override
    public Move chooseMove(Game game, int ghostIndex) {

        if (m_firstTime){
            m_stateMachine.SetCurrentState(new EatAllPoints(), game, ghostIndex);
            m_firstTime = false;
        }

        return m_stateMachine.Evaluate(game, ghostIndex);
//        Location pacman = game.getCurrentState().getPacManLocation();
//        List<Move> legalPacmanMoves = game.getLegalPacManMoves();
//
//        List<Pair<Location, Move>> tpLocations = new ArrayList<>();
//        for (Move m : legalPacmanMoves){
//            Location tp = null;
//            switch (m) {
//                case NONE:
//                    continue;
//                case UP:
//                    tp = new Location(pacman.getX(), pacman.getY() + 1);
//                    break;
//                case DOWN:
//                    tp = new Location(pacman.getX(), pacman.getY() - 1);
//                    break;
//                case LEFT:
//                    tp = new Location(pacman.getX() - 1, pacman.getY());
//                    break;
//                case RIGHT:
//                    tp = new Location(pacman.getX() + 1, pacman.getY());
//                    break;
//            }
//            tpLocations.add(new Pair<>(tp, m.getOpposite()));
//        }
//        game.getCurrentState().getGhostLocations().set(ghostIndex, tpLocations.get(ghostIndex % tpLocations.size()).first);

        //tpLocations.get(ghostIndex % tpLocations.size()).second;

        //game.setCurrentState(state);

    }
}
