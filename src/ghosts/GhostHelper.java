package ghosts;

import pacman.Game;
import pacman.Location;
import pacman.Move;
import pacman.State;
import util.Counter;

import java.util.*;

import static pacman.Game.*;

public class GhostHelper {

    public static Move ChooseMove(Game game, int ghostIndex, Location target) {

        State s = game.getCurrentState();

        List<Move> legalMoves = GetLegalGhostMoves(s, ghostIndex);

        System.out.println(legalMoves);


        Map<Move, Double> scores = new HashMap<>();


        for (Move m : legalMoves) {
            if (m.equals(Move.NONE)){
                continue;
            }
            List<State> stateList = ProjectGhostLocation(s, m, ghostIndex);

            State last = stateList.get(stateList.size() - 1);

            double dist = EvaluateState(last, ghostIndex, target);

            scores.put(m, dist);

        }

        if (scores.isEmpty()){
            return Move.NONE;
        }

        double minScore = Double.MAX_VALUE;
        Move bestMove = Move.NONE;
        for (Map.Entry<Move, Double> e : scores.entrySet()){
            if (e.getValue() < minScore){
                minScore = e.getValue();
                bestMove = e.getKey();
            }
        }

        return bestMove;
    }

    private static List<Move> GetLegalGhostMoves(State state, int ghostIndex) {
        List<Move> moves = new ArrayList<Move>();
        Location curr = state.getGhostLocations().get(ghostIndex);
        // ghosts in the ghost pen have to wait
        if (isInGhostPen(curr))
            return Collections.singletonList(Move.NONE);
        for (Move m : Move.values()) {
            if (m == Move.NONE)
                continue; // not a valid move
            Location next = getNextLocation(curr, m);
            if (isValidLocation(next))
                moves.add(m);
        }
        return moves;
    }

    private static double EvaluateState(State state, int ghostIndex, Location target){
        return Location.manhattanDistance(state.getGhostLocations().get(ghostIndex), target);
    }

    private static List<State> ProjectGhostLocation(State s, Move ghostMove, int ghostIndex) {

        int maxLength = Integer.MAX_VALUE;
        List<State> projectedStates = new ArrayList<State>();
        State curr = getNextState(s, ghostMove, ghostIndex);
        projectedStates.add(curr);
        List<Move> legalMoves = GetLegalGhostMoves(curr, ghostIndex);
        legalMoves.remove(ghostMove.getOpposite());
        int length = 0;
        while (!isFinal(curr) && legalMoves.size()==1 && length<maxLength) { // repeat this move
            // until the legal moves
            // have some options
            // beside this move and
            // its opposite
            ghostMove = legalMoves.get(0);
            curr = getNextState(curr, ghostMove, ghostIndex);
            projectedStates.add(curr);
            legalMoves = GetLegalGhostMoves(curr, ghostIndex);
            legalMoves.remove(ghostMove.getOpposite());
            length++;
        }
        // System.err.println(s.getPacManLocation() + " was projected to " +
        // projectedStates.get(projectedStates.size()-1).getPacManLocation());
        return projectedStates;
    }

    public static State getNextState(State s, Move move, int ghostIndex) {
        List<Location> newGhosts = new ArrayList<>(s.getGhostLocations());
        newGhosts.set(ghostIndex, getNextLocation(newGhosts.get(ghostIndex), move));
        return new State(s.getPacManLocation(), newGhosts, s.getDotLocations(), s.getPreviousGhostMoves(), s);
    }

}
