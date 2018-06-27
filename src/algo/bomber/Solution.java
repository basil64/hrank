package algo.bomber;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

  public static final long NO_BOMB = -1L;

  private static List<Point> noBombList = new ArrayList<>();
  private static Queue<Point> bombQueue = new PriorityQueue<>(100, new PointComparator());

  static Point[][] initMineField(String[] grid) {
    int r = grid.length + 2;
    int c = grid[0].length() + 2;
    Point[][] mineField = new Point[r][c];
    for (int j = 0; j < grid.length; j++) {
      for (int i = 0; i < grid[j].length(); i++) {
        char chr = grid[j].charAt(i);
        Point point;
        if (chr == 'O') {
          point = new Point(j + 1, i + 1, 0L, true);
          bombQueue.offer(point);
          mineField[j + 1][i + 1] = point;
        } else {
          point = new Point(j + 1, i + 1, 0L, false);
          noBombList.add(point);
          mineField[j + 1][i + 1] = point;
        }
      }
    }
    return mineField;
  }


  static void plantBombs(long time) {
    Iterator<Point> bombIterator = noBombList.iterator();
    while (bombIterator.hasNext()) {
      Point point = bombIterator.next();
      point.time = time;
      point.bomb = true;
      bombQueue.offer(point);
      bombIterator.remove();
    }
  }


  static public boolean inRange(int x, int y, int c, int r) {
    return x >= 0 && x < c && y >= 0 && y < r;
  }

  static public void explodeMineField(Point[][] mineField, long time, int c, int r) {

    int xy[][] = new int[4][4];
    while (true) {
      Point point = bombQueue.peek();
      if (point != null && time - point.time == 3L) {
        point = bombQueue.poll();
        point.bomb = false;
        int x = point.x;
        int y = point.y;
        noBombList.add(point);
        int k = 0;
        if (inRange(x - 1, y, c, r)) {
          xy[k][0] = x - 1;
          xy[k][1] = y;
          k++;
        }
        if (inRange(x + 1, y, c, r)) {
          xy[k][0] = x + 1;
          xy[k][1] = y;
          k++;
        }
        if (inRange(x, y - 1, c, r)) {
          xy[k][0] = x;
          xy[k][1] = y - 1;
          k++;
        }
        if (inRange(x, y + 1, c, r)) {
          xy[k][0] = x;
          xy[k][1] = y + 1;
          k++;
        }

        for (int i = 0; i < xy[0].length; i++) {
          int xc = xy[i][0];
          int yc = xy[i][1];
          if (mineField[yc][xc].bomb == false || mineField[yc][xc].bomb && time - mineField[yc][xc].time != 3) {
            mineField[yc][xc].bomb = false;
            noBombList.add(mineField[yc][xc]);
            bombQueue.remove(mineField[yc][xc]);
          }
        }
      } else {
        break;
      }
    }
  }

  public static void main(String[] args) {
    int n = 200;
    Point[][] mf = new Point[n][n];
    Point p = new Point(0, 0, 0L, true);
    mf[p.x][p.y] = p;
    List<Point> lst = new ArrayList<>();
    lst.add(p);
    p.time = 100;
    System.out.println(lst.get(0));
    System.out.println(mf[0][0]);
    mf[0][0].bomb = false;
    System.out.println(lst.get(0));
    System.out.println(mf[0][0]);
  }

  static class Point {
    int x;
    int y;
    long time;
    boolean bomb;
    int hCode;

    public Point(int x, int y, long time, boolean bomb) {
      this.x = x;
      this.y = y;
      this.time = time;
      this.bomb = bomb;
      hCode = 31 * x + y;
    }

    @Override
    public String toString() {
      return "Point{" +
          "x=" + x +
          ", y=" + y +
          ", time=" + time +
          ", bomb=" + bomb +
          '}';
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Point point = (Point) o;

      if (x != point.x) return false;
      return y == point.y;
    }

    @Override
    public int hashCode() {
      return hCode;
    }
  }

  static class PointComparator implements Comparator<Point> {

    @Override
    public int compare(Point p1, Point p2) {
      return p1.time > p2.time ? 1 : p1.time == p2.time ? 0 : -1;
    }
  }
}
