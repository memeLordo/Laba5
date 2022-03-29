package SetOfCommands;

public class Exit extends Command{
    public Exit() {
        super("exit", "завершить программу (без сохранения в файл)",false);
    }

    @Override
    public void go() {
        System.exit(0);
    }
}
