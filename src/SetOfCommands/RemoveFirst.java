package SetOfCommands;
import static SetOfCommands.CommandsPack.*;
public class RemoveFirst extends Command {
    public RemoveFirst() {
        super("removefirst", "удалить первый элемент из коллекции",false);
    }
    @Override
    public void go() {
        getPeople_data().pop();
    }
}
