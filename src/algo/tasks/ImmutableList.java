package algo.tasks;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ImmutableList {
  public static void main(String[] argv) throws ParseException, IOException, ClassNotFoundException {
    List<Date> ld = new ArrayList<>();
    SimpleDateFormat sd = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
    Date d1 = sd.parse("01-05-2018 10:10:00");
    ld.add(d1);
    Date d2 = sd.parse("03-05-2018 10:10:00");
    ld.add(d2);
    ld.get(0).setTime(sd.parse("02-05-2018 10:10:00").getTime());
    List<Date> ldi = Collections.unmodifiableList(ld);
    ldi.get(0).setTime(sd.parse("04-05-2018 10:10:00").getTime());

    //Serialization of object
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutputStream out = new ObjectOutputStream(bos);
    out.writeObject(ld);

    //De-serialization of object
    ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
    ObjectInputStream in = new ObjectInputStream(bis);
    List<Date> copied = (List<Date>) in.readObject();
    System.out.println(copied);
    System.out.println();
    System.out.println(ld);
    System.out.println();
    d1.setTime(sd.parse("05-05-2018 10:10:00").getTime());
    System.out.println(copied);
    System.out.println();
    System.out.println(ld);
    System.out.println();

    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(bs));
    for (int i = 0; i < 10; i++) {
      pw.println("This is line " + i);
    }
    pw.close();

    ByteArrayInputStream bi = new ByteArrayInputStream(bs.toByteArray());
    BufferedReader bf = new BufferedReader(new InputStreamReader(bi));
    String line;
    while ((line = bf.readLine()) != null) {
      System.out.println(line);
    }
    bf.close();
  }
}
