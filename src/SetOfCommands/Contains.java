package SetOfCommands;
import static SetOfCommands.CommandsPack.inputCommand;

public class Contains extends Command {

    public Contains() {
        super("contains", "description",true);
    }
    @Override
    public void go() {
        this.findIn(inputCommand);
    }
}
