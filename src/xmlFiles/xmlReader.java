package xmlFiles;

import Parameters.Color;
import Parameters.Coordinates;
import Parameters.Location;
import Parameters.Person;
import SetOfCommands.InfoData;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.Stack;

import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class xmlReader extends xmlData {
    public static Stack<Person> go() {
        Stack<Person> result = new Stack<>();
        Scanner sc = new Scanner(read());
        String[] type = InfoData.getColumns();
        String[] fill = new String[9];
        while (sc.hasNext()) {
            String input = sc.nextLine();
            for (int i = 0; i < fill.length; i++)
                fill[i] = input.contains(type[i]) ? subString(input, type[i]) : fill[i];
            if (input.contains("</person>")){
                result.push(setPerson(fill));
            }
        }
        return result;
    }
    static Person setPerson(String[]fill){
       Person lol = new Person(fill[1],
                parseCoordinates(fill[2]),
                parseLong(fill[4]),
                parseDouble(fill[5]),
                parseLDate(fill[6]),
                parseColor(fill[7]),
                parseLocation(fill[8]));
        lol.setId(parseInt(fill[0]));
        return lol;
    }
    static String subString(String s, String input) {
        int inputIndex = s.indexOf(input) + input.length() + 1;
        StringBuilder result = new StringBuilder();
        for (int i = inputIndex; ; i++) {
            if (s.charAt(i) == '<') break;
            result.append(s.charAt(i));
        }
        return result.toString();
    }
    static Coordinates parseCoordinates(String input) {
        String[] s = input.replaceAll("°", "").split(" . ");
        return new Coordinates(parseInt(s[0]), parseInt(s[1]));
    }
    static LocalDate parseLDate(String input) {
        return LocalDate.parse(input);
    }
    static Color parseColor(String input) {
        return Color.valueOf(input);
    }
    static Location parseLocation(String input) {
        String[] r = input.trim().replaceAll("Место", "").split(":");
        return new Location(parseInt(r[0]), parseInt(r[1]), parseFloat(r[2]), r[3]);
    }

}
