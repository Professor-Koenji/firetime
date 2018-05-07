package com.koenji.ecs.graph.pathfinding.strategy;

import com.koenji.ecs.graph.pathfinding.nodes.INode;
import com.koenji.ecs.graph.pathfinding.nodes.IPathNode;
import com.koenji.ecs.graph.pathfinding.nodes.PathNode;

import java.util.*;

/**
 * Breadth first search algorithm to find the path between INodes, explores all neighbouring nodes before moving out
 *
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */

public class BFS implements IPathStrategy {
  /**
   * Method: find the path between the start and target using bfs
   * @param start   - staring node
   * @param target  - target node
   * @return
   */
  public List<INode> findPath(INode start, INode target) {
    // Open and Closed lists
    List<IPathNode> open = new ArrayList<>();
    List<IPathNode> closed = new ArrayList<>();

    // Mappings from standard INode to a IPathNode
    Map<INode, IPathNode> pathNodes = new HashMap<>();

    // Create the PathNode for our starting node
    pathNodes.put(start, new PathNode(start));

    // Add the first PathNode to our open list
    open.add(pathNodes.get(start));
    // Set the first nodes G value to 0
    pathNodes.get(start).setG(0);

    // While there are still open PathNode's to search
    while (open.size() > 0) {
      // Get the next PathNode in the queue
      IPathNode current = open.get(0);
      // 2. If current is goal node, perfection!
      if (current.getNode() == target) {
        // Return the constructed path
        return current.createPath();
      }
      // 3. Mutate lists
      closed.add(open.remove(0));
      // 4. Loop thru all neighbours of current node
      for (INode n : current.getNode().getNeighbours()) {
        // Is there already a path node for this node?
        // If not, then make one and add it to our PathNode map.
        if (!pathNodes.containsKey(n)) pathNodes.put(n, new PathNode(n));
        // Get the PathNode from the map
        IPathNode pn = pathNodes.get(n);
        // 5. If PathNode is in closed set, then skip
        if (closed.contains(pn)) continue;
        // 6. If PathNode is not in open set, then add it
        if (!open.contains(pn)) open.add(pn);
        // 7. Get a 'tentative' g score
        float gScore = current.getG();
        // 8. If we have found a faster route there already, just skip
        if (gScore > pn.getG()) continue;
        // 9. This is fastest path so far to this node, save it!
        pn.setPrevious(current);
        pn.setG(gScore);
      }
    }
    // Open list exhausted, no path found!
    return null;
  }
}
