package SetOfCommands;
import java.util.Map;

import static SetOfCommands.CommandsPack.*;

public class Help extends Command{

    public Help() {
        super("help", "вывести справку по доступным командам",false);
    }

    @Override
    public void go() {
        for(Map.Entry<String, Command> set: getMap().entrySet()) {
            System.out.print(set.getKey());
            if (set.getKey().equals(new Add().getName()) | set.getKey().equals(new UpdateID().getName()))
                System.out.print(" <element>");
            System.out.println(" : " + set.getValue().getDescription());
            if(set.getKey().equals(new Add().getName())) System.out.println(new Add().getName()+" def : добавить новый стандартный элемент в коллекцию");
        }
    }
}
