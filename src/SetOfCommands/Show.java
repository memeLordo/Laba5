package SetOfCommands;

import Parameters.Person;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Show extends Command {
    public Show() {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении",false);
    }

    public static void showPerson(Person person) {
        format(person.getId());
        System.out.println("Имя: " + person.getName());
        System.out.println("Координаты: "+person.getCoordinates());
        System.out.println("Дата создания: "+dateFormat.format(person.getCreationDate()));
        System.out.printf("Рост: %s см\n",person.getHeight());
        System.out.printf("Вес: %.1f кг\n",person.getWeight());
        System.out.println("День рождения: "+person.getBirthday());
        System.out.println("Цвет глаз: "+person.getEyeColor().getName());
        System.out.println("Местоположение: "+person.getLocation());
        System.out.println();
    }
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy H:mm:ss");
    private static void format(Object f) {
        System.out.println("id: " + new DecimalFormat("000000000").format(f));
    }

    @Override
    public void go() {
        if (CommandsPack.isPeopleDataEmpty())
            for (Person stack : CommandsPack.getPeople_data())
                showPerson(stack);
    }
}

