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
      String jsonRaw = br.readLine();
      Gson gson = new Gson();
      return gson.fromJson(jsonRaw, LevelObject.class);
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
