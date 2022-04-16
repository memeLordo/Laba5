package SetOfCommands;

import Parameters.Person;
import xmlFiles.xmlData;

import java.io.File;

import static SetOfCommands.CommandsPack.getPeople_data;
import static SetOfCommands.Show.showPerson;

public abstract class Command {
    private final String name;
    private final String description;
    private final boolean isDefault;

    public Command(String name, String description, boolean isDefault) {
        this.name = name;
        this.description = description;
        this.isDefault = isDefault;
    }

    public static void CreateFile(String name) {
        try {
            File f = new File(name);
            if (f.createNewFile())
                System.out.println("Инициализация файла...");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public boolean findIn(Object obj) {
        String line = obj.toString();
        boolean trigger = true;
        for (Person p : getPeople_data()) {
            if (ifPerson(p, line)){
//                if (this.getDefault()) showPerson(p);
                trigger = false;
            }
        }
        if (trigger) System.err.println("Нет такого элемента.");
        return !trigger;
    }

    public boolean ifPerson(Person p, String inputText) {
        return p.getId().toString().equals(inputText) |
                p.getName().contains(inputText) |
                p.getCoordinates().contains(inputText) |
                p.getHeight().toString().equals(inputText) |
                p.getWeight().toString().equals(inputText) |
                p.getCreationDate().toString().equals(inputText) |
                p.getBirthday().toString().equals(inputText) |
                p.getEyeColor().toString().equals(inputText) |
                p.getLocation().contains(inputText);
    }

    public boolean getDefault() {
        return isDefault;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract void go();
}
