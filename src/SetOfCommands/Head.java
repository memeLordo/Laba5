package SetOfCommands;

import Parameters.Person;

import static SetOfCommands.CommandsPack.*;

public class Head extends Command{

    public Head() {
        super("head", "вывести первый элемент коллекции",false);
    }

    @Override
    public void go() {
        Person jew = null;
        if(isPeopleDataEmpty()){
            for (Person person: getPeople_data()) jew = person;
            assert jew != null;
            Show.showPerson(jew);
        }
    }
}
