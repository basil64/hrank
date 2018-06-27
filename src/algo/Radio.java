package algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Radio {
  static int hackerlandRadioTransmitters(int[] x, int k) {
    List<Integer> list = new ArrayList<Integer>();
    for (int e : x) {
      list.add(e);
    }
    Set<Integer> sourceSet = new TreeSet(list);
    Integer[] houses = sourceSet.toArray(new Integer[sourceSet.size()]);
    int cnt = 0;
    int i = 0;
    int start = 0;
    int diff = 0;
    int prv = 0;
    int r = 2 * k;
    while (i < houses.length) {
      diff = houses[i] - houses[prv];
      if (diff > r) {
        int len = houses[prv] - houses[start];
        if (len > 0) {
          cnt += len / r + (len % r > 0 ? 1 : 0);
        } else {
          cnt++;
        }
        start = i;
        if (start == houses.length - 1) {
          cnt++;
        }
      } else if (diff == r) {
        System.out.println("diff = " + diff + " " + houses[i]);
        cnt++;
        start = i + 1;
        if (start == houses.length - 1) {
          cnt++;
        }
      } else if (diff < r) {
        int len = houses[i] - houses[start];
        if (len == r) {
          cnt++;
          start = i + 1;
          if (start == houses.length - 1) {
            cnt++;
          }
        } else {
          if (i == houses.length - 1) {
            cnt++;
          }
        }
      }
      prv = i;
      i++;
    }
    System.out.println(sourceSet);
    return cnt;
  }

  public static void main(String[] argv) {
    //int[] x = {1, 2, 3, 4, 5};
    //int k = 1;
    int[] x = {7, 2, 4, 6, 5, 9, 12, 11};
    int k = 2;
    int cnt = hackerlandRadioTransmitters(x, k);
    System.out.println("cnt = " + cnt);
  }
}
