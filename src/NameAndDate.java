import java.util.*;

public class NameAndDate implements Comparable<NameAndDate> {
   private String name;
   private String date;
   private Map<Integer, String> months;

   public NameAndDate(String name, String date) {
      this.name = name;
      this.date = date;
      months = new HashMap<>();
      months.put(1, "Jan");
      months.put(2, "Feb");
      months.put(3, "Mar");
      months.put(4, "Apr");
      months.put(5, "May");
      months.put(6, "Jun");
      months.put(7, "Jul");
      months.put(8, "Aug");
      months.put(9, "Sep");
      months.put(10, "Oct");
      months.put(11, "Nov");
      months.put(12, "Dec");
   }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String toString() {
      return name + " ::= " + date;
   }

   public String dateString() {
      String[] dates = date.split("[/]");
      if (dates.length == 1) {
         return dates[0];
      }
      return months.get(Integer.parseInt(dates[0])) + " " + dates[1];
   }

   public int getDateValue() {
      String[] dates = date.split("[/]");
      if (dates.length == 1) {
         return Integer.parseInt(dates[0]) * 100000;
      }
      return Integer.parseInt(dates[2]) * 10000 + Integer.parseInt(dates[0]) * 100 + Integer.parseInt(dates[1]);      
   }

   public int compareTo(NameAndDate other) {
      int diff = getDateValue() - other.getDateValue();
      if (diff == 0) {
         return this.name.compareTo(other.name);
      }
      return diff;
   }
}