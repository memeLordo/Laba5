package SetOfCommands;

import Parameters.Person;

import java.util.Stack;

import static SetOfCommands.CommandsPack.*;

public class RemoveLower extends Command {
    public RemoveLower() {
        super("removelower", "удалить из коллекции все элементы, меньшие, чем заданный",false);
    }

    @Override
    public void go() {
        if(this.findIn(inputID)){
            System.out.println(inputID);
            sortPeopleData(inputID);
            System.out.println("done");
        }
    }

    void sortPeopleData(int id) {
        Stack<Person> s1 = getPeople_data();
        Stack<Person> s2 = new Stack<>();
        while (!s1.isEmpty()) {
            Person temp = s1.pop();
            if (temp.getId() <= id) s2.push(temp);
        }
        setPeople_data(s2);
    }
}
