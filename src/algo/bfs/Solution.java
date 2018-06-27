package algo.bfs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;



public class Solution {

  private static final int W = 6;
  private static Scanner scanner;

  static int[] bfs1(int n, int m, int[][] edges, int s) {
    boolean[][] ar = new boolean[n + 1][n + 1];
    for (int i = 0; i < edges.length; i++) {
      int x = edges[i][0];
      int y = edges[i][1];
      ar[x][y] = true;
      ar[y][x] = true;
    }
    int[] distance = new int[n + 1];
    boolean[] visited = new boolean[n + 1];

    Arrays.fill(distance, -1);
    Queue<Node> queue = new LinkedList<>();
    queue.offer(new Node(s, 0));
    distance[s] = 0;
    while (!queue.isEmpty()) {
      Node nd = queue.poll();
      int v = nd.getId();
      if (!visited[v]) {
        visited[v] = true;
        for (int i = 1; i <= n; i++) {
          if (ar[v][i]) {
            if (distance[v] + W < distance[i] || distance[i] == -1) {
              distance[i] = distance[v] + W;
            }
            Node node = new Node(i, distance[i]);
            queue.offer(node);
          }
        }
      }
    }
    System.out.println();
    int[] result = new int[n - 1];
    for (int i = 1; i <= n; i++) {
      if (i < s) {
        result[i - 1] = distance[i];
      }
      if (i > s) {
        result[i - 2] = distance[i];
      }
    }

    return result;
  }

  static int[] bfs(int n, int m, int[][] edges, int s) {
    boolean[] visited = new boolean[n + 1];
    boolean[] calculated = new boolean[n + 1];
    int[] distance = new int[n + 1];
    Arrays.fill(distance, -1);
    Map<Integer, List<Integer>> adjMap = new HashMap<>();
    for (int i = 0; i < edges.length; i++) {
      List<Integer> list;
      int x = edges[i][0];
      int y = edges[i][1];
      if (!adjMap.containsKey(x)) {
        list = new ArrayList<>();
        adjMap.put(x, list);
      } else {
        list = adjMap.get(x);
      }
      list.add(y);
      if (adjMap.containsKey(y)) {
        adjMap.get(y).add(x);
      }
    }

    for (Map.Entry<Integer, List<Integer>> entry : adjMap.entrySet()) {
      List<Integer> lst = entry.getValue();
      Set<Integer> set = new TreeSet<>(lst);
      entry.setValue(new ArrayList(set));
    }

    if (!adjMap.containsKey(s)) {
      List<Integer> startList = new ArrayList<>();
      for (Map.Entry<Integer, List<Integer>> entry : adjMap.entrySet()) {
        if (entry.getValue().contains(s)) {
          startList.add(entry.getKey());
        }
      }
      adjMap.put(s, startList);
    }


    Queue<Node> queue = new LinkedList<>();
    queue.offer(new Node(s, 0));
    distance[s] = 0;
    while (!queue.isEmpty()) {
      Node nd = queue.poll();
      int v = nd.getId();
      if (!visited[v]) {
        visited[v] = true;
        if (adjMap.containsKey(v)) {
          List<Integer> list = adjMap.get(v);
          for (int e : list) {
            if (distance[v] + W < distance[e] || distance[e] == -1) {
              distance[e] = distance[v] + W;
            }
            Node node = new Node(e, distance[e]);
            queue.offer(node);
          }
        }
      } else {
        if (adjMap.containsKey(v)) {
          if (nd.getDistance() < distance[v]) {
            distance[v] = nd.getDistance();
          }
          for (int e : adjMap.get(v)) {
            if (distance[v] + W < distance[e]) {
              distance[e] = distance[v] + W;
            }
            if (e == s) {
              distance[v] = W;
            }
          }
        }
      }
    }


    System.out.println();
    int[] result = new int[n - 1];
    for (int i = 1; i <= n; i++) {
      if (i < s) {
        result[i - 1] = distance[i];
      }
      if (i > s) {
        result[i - 2] = distance[i];
      }
    }
    return result;
  }


  public static int getNext(int s, boolean visited[]) {
    int i = s + 1;
    while (i < visited.length && visited[i]) {
      i++;
    }
    if (i < visited.length) {
      return i;
    }
    i = 0;
    while (i < s && visited[i]) {
      i++;
    }
    return i != s ? i : -1;
  }


  static class Node {
    int id;
    int distance;

    public Node(int id, int distance) {
      this.id = id;
      this.distance = distance;
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public int getDistance() {
      return distance;
    }

    public void setDistance(int distance) {
      this.distance = distance;
    }
  }


  public static void main(String[] args) throws IOException {

    PrintWriter bufferedWriter = new PrintWriter(System.out);
    FileInputStream fs = new FileInputStream("C:\\work\\hrank\\src\\algo\\bfs\\case4.txt");
    scanner = new Scanner(fs);
    int q = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int qItr = 0; qItr < q; qItr++) {
      String[] nm = scanner.nextLine().split(" ");

      int n = Integer.parseInt(nm[0]);

      int m = Integer.parseInt(nm[1]);

      int[][] edges = new int[m][2];

      for (int i = 0; i < m; i++) {
        String[] edgesRowItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int j = 0; j < 2; j++) {
          int edgesItem = Integer.parseInt(edgesRowItems[j]);
          edges[i][j] = edgesItem;
        }
      }

      int s = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      int[] result = bfs1(n, m, edges, s);

      for (int i = 0; i < result.length; i++) {
        bufferedWriter.write(String.valueOf(result[i]));

        if (i != result.length - 1) {
          bufferedWriter.write(" ");
        }
      }

      bufferedWriter.println();
    }

    bufferedWriter.close();

    scanner.close();
  }

}