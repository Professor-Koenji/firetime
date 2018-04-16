package com.koenji.firetime.level;

import com.google.gson.Gson;
import com.koenji.ecs.graph.pathfinding.nodes.Node;

import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class LevelObject {

  // All member variables must be CONCRETE!
  public List<Node> nodes;
  public List<String> connections;

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

}
