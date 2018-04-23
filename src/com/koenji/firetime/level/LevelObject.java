package com.koenji.firetime.level;

import com.google.gson.Gson;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.graph.pathfinding.nodes.INode;
import com.koenji.ecs.graph.pathfinding.nodes.Node;
import com.koenji.firetime.entities.Guard;
import com.koenji.firetime.entities.Player;
import com.koenji.firetime.entities.Wall;
import processing.core.PVector;

import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LevelObject {

  // All member variables must be CONCRETE!
  public List<Node> nodes;
  public List<String> connections;

  public List<WallObject> walls;
  public List<GuardObject> guards;

  public PVector playerPosition;
  public PVector exit;

  public static LevelObject fromPath(String path) {
    try(BufferedReader br = new BufferedReader(new FileReader("data/levels/" + path + ".json"))) {
      StringBuilder jsonRaw = new StringBuilder();
      String l = br.readLine();
      while (l != null) {
        jsonRaw.append(l);
        l = br.readLine();
      }
      Gson gson = new Gson();
      return gson.fromJson(jsonRaw.toString(), LevelObject.class);
    } catch (IOException e) {
      System.out.println("Cannot load level!");
    }
    System.exit(1);
    return null;
  }

  public LevelObject() {
    nodes = new ArrayList<>();
    connections = new ArrayList<>();
  }

  public void setup() {
    for (String cnx : connections) {
      String[] is = cnx.split("-");
      int a = Integer.parseInt(is[0]);
      int b = Integer.parseInt(is[1]);
      nodes.get(a).addNeighbour(nodes.get(b));
    }
  }

  public List<IEntity> getWalls() {
    List<IEntity> realWalls = new ArrayList<>();
    for (WallObject wo : walls) {
      Wall w = new Wall(wo.x, wo.y, wo.w, wo.h, wo.angle);
      realWalls.add(w);
    }
    // Make boundary walls
    int[] bounds = {Integer.MAX_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MAX_VALUE};
    for (INode node : nodes) {
      int x = node.getX();
      int y = node.getY();
      if (y < bounds[0]) bounds[0] = y;
      else if (y > bounds[2]) bounds[2] = y;
      if (x > bounds[1]) bounds[1] = x;
      else if (x < bounds[3]) bounds[3] = x;
    }
    float w = bounds[1] - bounds[3];
    float h = bounds[2] - bounds[0];
    float midX = bounds[3] + w * .5f;
    float midY = bounds[0] + h * .5f;
    float GAP = 400f;
    // Top wall
    realWalls.add(new Wall(midX, bounds[0] - GAP, w + GAP * 2, 60, 0));
    // Bottom wall
    realWalls.add(new Wall(midX, bounds[0] + h + GAP, w + GAP * 2, 60, 0));
    // Left wall
    realWalls.add(new Wall(bounds[3] - GAP, midY, 60, h + GAP * 2, 0));
    // Right wall
    realWalls.add(new Wall(bounds[3] + w + GAP, midY, 60, h + GAP * 2, 0));
    return realWalls;
  }

  public List<IEntity> getGuards(PVector chasePoint) {
    List<IEntity> realGuards = new ArrayList<>();
    for (GuardObject go : guards) {
      List<INode> pathNodes = new ArrayList<>();
      for (int i : go.path) pathNodes.add(nodes.get(i));
      Guard g = new Guard(pathNodes, chasePoint);
      realGuards.add(g);
    }
    return realGuards;
  }

  public void addNodes(List<Node> newNodes) {
    nodes.addAll(newNodes);
  }

  public void addConnections(List<String> newConnections) {
    connections.addAll(newConnections);
  }

  public void export(String filename) {
    String unix = Long.toString(Instant.now().getEpochSecond());
    try (Writer w = new FileWriter("data/levels/" + filename + "-" + unix + ".json")) {
      Gson gson = new Gson();
      gson.toJson(this, w);
    } catch (IOException e) {
      System.out.println("Something bad happened");
    }
  }

  private class WallObject {
    public float x;
    public float y;
    public float w;
    public float h;
    public float angle;
  }

  private class GuardObject {
    public float x;
    public float y;
    public List<Integer> path;
  }

}
