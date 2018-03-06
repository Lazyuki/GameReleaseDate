import java.io.*;
import java.util.*;

/**
* Release date interactive
*/
public class ReleaseDate {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("data.txt"));
        Scanner console = new Scanner(System.in);
        Set<NameAndDate> games = new TreeSet<NameAndDate>();
        Set<String> ansSet = new HashSet<String>() {{add("a");add("c");add("r");add("q");}};
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] nameNdate = line.split("::=");
            games.add(new NameAndDate (nameNdate[0].trim(), nameNdate[1].trim()));
        }
        boolean quit = false;
        // Repeats until quit.
        while (!quit) {
            System.out.println();
            System.out.println("Here's the list so far");
            for (NameAndDate game : games) {
                System.out.println("  " + game.getName() + " " + game.getDate());
            }
            System.out.println();
            System.out.println("(a)dd, (c)hange, (r)emove, or (q)uit?");
            String ans = console.nextLine().toLowerCase();
            while(!ansSet.contains(ans.substring(0,1))) { // Makes sure to get a, c, r, or q
                System.out.println("Please type \'a\', \'c\', \'r\' or \'q\' for add, change, remove or quit.");
                ans = console.nextLine().toLowerCase();
            }
            if (ans.startsWith("a")) { // add
                add(console, games);
            } else if (ans.startsWith("r") || ans.startsWith("c")) { //remove or change
                remove(console,  games, ans.startsWith("r"));
            } else { //quit
                quit = true;
            }
        }
        quit(console, games);
    }

    public static void add(Scanner console, Set<NameAndDate> games) {
        System.out.println("Type in the name of the game");
        String name = console.nextLine();
        System.out.println("Type in the release date in the format mm/dd/yyyy or just the year");
        String date = console.nextLine();
        games.add(new NameAndDate(name, date));
    }

    public static void remove(Scanner console,  Set<NameAndDate> games, boolean remove) {
        System.out.println("Type in the first few letters of the title.");
        String name = console.nextLine().toLowerCase();
        boolean contains = false;
        NameAndDate removeThis = null;
        for (NameAndDate game : games) {
            if (game.getName().toLowerCase().startsWith(name)) {
                String chORdel = "Delete ";
                if (!remove) {
                    chORdel = "Change ";
                }
                System.out.println(chORdel + game.getName() + "? y or n");
                String answer = console.nextLine();
                while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
                    System.out.println("y or n");
                    answer = console.nextLine();
                }
                if (answer.equalsIgnoreCase("y")) {
                    removeThis = game;
                    if (!remove) {
                        System.out.println("Type in the new release date in the format mm/dd/yyyy or just the year");
                        String date = console.nextLine();
                        games.add(new NameAndDate(game.getName(), date));
                    }
                    contains = true;
                    break;
                }
            }
        }
        if (contains) {
            games.remove(removeThis);
        } else {
            System.out.println("There was no match");
        }
    }

    public static void quit(Scanner console, Set<NameAndDate> games) throws FileNotFoundException {
        System.out.println("Save changes? y or n");
        String answer = console.nextLine();
        while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
            System.out.println("Save changes? y or n");
            answer = console.nextLine();
        }
        if (answer.equalsIgnoreCase("y")) {
            PrintStream source = new PrintStream(new File("data.txt"));
            String titles = "";
            String dates = "";
            for (NameAndDate game : games) {
                source.println(game);
                titles += game.getName() + "#CRLF#";
                dates += game.dateString() + "#CRLF#";
            }
            PrintStream out = new PrintStream(new File("../text.inc"));
            out.println(";#CRLF# is a line-breaker");
            out.println("[variables]");
            out.println("text1=\"" + titles.substring(0, titles.length() - 6) + "\"");
            out.println("text2=\"" + dates.substring(0, dates.length() - 6) + "\"");
        }
    }
}
