package SetOfCommands;

import Parameters.Color;
import Parameters.Coordinates;
import Parameters.Location;
import Parameters.PersonSet;

import java.time.LocalDate;
import java.util.Random;

public class Generator implements PersonSet {
    private static final String[] names =
            {"Василий", "Пётр", "Саша", "Николай", "Валентин", "Даниил", "Иван", "Евгений"};
    private static final String[] surnames = {"Иванов", "Сидоров", "Никитин", "Серебрянский", "Петров"};
    private static final String[] places = {"Россия", "Албания", "США", "Китай", "Великобритания", "Германия", "Чехия"};
    Random random = new Random();
    private long height;

    @Override
    public Integer Id() {
        return random.nextInt(999999998) + 1;
    }

    @Override
    public String Name() {
        return names[random.nextInt(names.length)] + " " + surnames[random.nextInt(surnames.length)];
    }

    @Override
    public Coordinates Coordinates() {
        return new Coordinates(random.nextInt(180), random.nextInt(180));
    }

    @Override
    public Long Height() {
        height = random.nextLong(150L, 200L);
        return height;
    }

    @Override
    public LocalDate Birthday() {
        return LocalDate.ofEpochDay(random.nextInt(10000));
    }

    @Override
    public Double Weight() {
        return Math.pow((height * 0.01d), 2) * random.nextInt(18, 26);
    }

    @Override
    public Color Color() {
        Color[] color = Color.values();
        return color[random.nextInt(4)];
    }

    @Override
    public Location Location() {
        return new Location(random.nextInt(-180, 180), random.nextInt(-180, 181), random.nextFloat(), places[random.nextInt(places.length)]);
    }
}
