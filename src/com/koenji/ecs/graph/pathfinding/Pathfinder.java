package com.koenji.ecs.graph.pathfinding;

import processing.core.PVector;

import java.util.*;

public class Pathfinder {

  private INode start;

  public Pathfinder(INode start) {
    this.start = start;
  }

  public List<INode> findPath(INode target) {
    List<IPathNode> open = new ArrayList<>();
    List<IPathNode> closed = new ArrayList<>();

    Map<INode, IPathNode> pathNodes = new HashMap<>();

    pathNodes.put(start, new PathNode(start, target));

    open.add(pathNodes.get(start));

    while (open.size() > 0) {
      // 1. Sort the open list
      open.sort(new Comparator<IPathNode>() {
        @Override
        public int compare(IPathNode o1, IPathNode o2) {
          return o1.getF() > o2.getF() ? -1 : 1;
        }
      });
      IPathNode current = open.get(0);
      // 2. If current is goal, perfection!
      if (current.getNode() == target) {
        // Perfection!
        return createPath(current);
      }
      // 3. Mutate lists
      closed.add(open.remove(0));
      // 4. Loop thru all neighbours of current node
      for (INode n : current.getNode().getNeighbours()) {
        // Is there already a path node for this node?
        if (!pathNodes.containsKey(n)) pathNodes.put(n, new PathNode(n, target));
        // Get pathnode
        IPathNode pn = pathNodes.get(n);
        // 5. If pathNode in closed set, then continue
        if (closed.contains(pn)) continue;
        // 6. If pathNode is not in open set, then add it
        if (!open.contains(pn)) open.add(pn);
        // 7. Get a 'tentative' g score
        float gScore = current.getG() + getDistance(current.getNode(), n);
        // 8. If we have found a faster route there already, just continue
        if (pn.getG() != -1 && gScore > pn.getG()) continue;
        // 9. This is fastest path so far, save it!
        pn.setPrevious(current);
        pn.setG(gScore);
      }
    }
    // Open list exhausted, no path found!
    return null;
  }

  private float getDistance(INode a, INode b) {
    return (float) Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
  }

  private List<INode> createPath(IPathNode end) {
    List<INode> path = new ArrayList<>();
    path.add(end.getNode());
    while (end.getPrevious() != null) {
      end = end.getPrevious();
      path.add(end.getNode());
    }
    return path;
  }

}
