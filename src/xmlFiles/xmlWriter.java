package xmlFiles;
import Parameters.Person;
import SetOfCommands.InfoData;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Stack;

import static SetOfCommands.CommandsPack.getPeople_data;
import static SetOfCommands.CommandsPack.setPeople_data;


public class xmlWriter extends xmlData {

    public static void go() {
        DecimalFormat dF = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
        String[] type = InfoData.getColumns();
        String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        Stack<Person> s1 = getPeople_data();
        Stack<Person> s2 = new Stack<>();
        input = input + "<set>\n";
        while (!s1.empty()) {
            input = input + "\t<person>\n";
            input = input + printXML(type[0], s1.peek().getId());
            input = input + printXML(type[1], s1.peek().getName());
            input = input + printXML(type[2], s1.peek().getCoordinates());
            input = input + printXML(type[3], s1.peek().getCreationDate());
            input = input + printXML(type[4], dF.format(s1.peek().getHeight()));
            input = input + printXML(type[5], dF.format(s1.peek().getWeight()));
            input = input + printXML(type[6], s1.peek().getBirthday());
            input = input + printXML(type[7], s1.peek().getEyeColor());
            input = input + printXML(type[8], s1.peek().getLocation());
            input = input + "\t</person>\n";
            s2.push(s1.pop());
        }
        input = input + "</set>\n";
        write(input);
        setPeople_data(s2);
    }
    private static String printXML(String tag,Object value){
        return "\t\t<" + tag + ">" + value.toString() + "</" + tag+ ">\n";
    }



}
