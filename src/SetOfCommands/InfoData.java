package SetOfCommands;

public class InfoData extends Command{

    public InfoData() {
        super("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)",false);
    }
    private static final String[] columns ={"id","name", "coordinates","creation_date", "height", "weight","birthday", "eyeColor", "location"};
    @Override
    public void go() {
        System.out.println("Поля:");
        for (String x:columns) {
            System.out.println(x);
        }
    }

    public static String[] getColumns() {
        return columns;
    }
}
