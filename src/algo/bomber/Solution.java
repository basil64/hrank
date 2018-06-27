package algo.bomber;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {

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
    mf[0][0].bomb =false;
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
  
  class PointComparator implements Comparator<Point> {

    @Override
    public int compare(Point p1, Point p2) {
      return p1.time > p2.time ? 1 : p1.time == p2.time ? 0 : -1;
    }
  }
}
