package algo.cracking;

public class Cycle {

  static boolean hasCycle(Node head) {
    Node ptr1 = head;
    Node ptr2 = head.next;
    while (ptr1 != null && ptr2 != null) {
      ptr1 = ptr1.next;
      int cnt = 0;
      while (cnt < 2) {
        if (ptr2 == null) {
          return false;
        }
        ptr2 = ptr2.next;
        if (ptr1 == ptr2) {
          return true;
        }
        cnt++;
      }
    }
    return false;
  }

  public static void main(String[] argv) {
    Node n1 = new Node();
    Node n2 = new Node();
    Node n3 = new Node();
    Node n4 = new Node();
    Node n5 = new Node();
    Node n6 = new Node();
    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
    n4.next = n5;
    n5.next = n4;
    boolean cycle = hasCycle(n1);
    System.out.println("Has cycle = " + cycle);
  }

  static class Node {
    int data;
    Node next;
  }

}
