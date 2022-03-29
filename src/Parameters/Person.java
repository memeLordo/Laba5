package Parameters;

import Exceptions.EmptyLineException;
import SetOfCommands.Generator;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.*;

public class Person {


    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private final java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final Long height; //Поле не может быть null, Значение поля должно быть больше 0
    private final java.time.LocalDate birthday; //Поле не может быть null
    private final Double weight; //Поле не может быть null, Значение поля должно быть больше 0
    private final Color eyeColor; //Поле не может быть null
    private final Location location; //Поле не может быть null
    private final Generator generator = new Generator();
    DecimalFormat dF = new DecimalFormat("#.##",new DecimalFormatSymbols(Locale.US));
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    public Person(String name, Coordinates coordinates, Long height, Double weight, LocalDate birthday, Color eyeColor, Location location) {

        this.id = generator.Id();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = new Date();
        this.height = height;
        this.birthday = birthday;
        this.weight = weight;
        this.eyeColor = eyeColor;
        this.location = location;

    }

    public Person() {
        this.id = generator.Id();//изменить на генератор
        this.name = generator.Name();//из перечня имён
        this.coordinates = generator.Coordinates();// генерирует координаты
        this.creationDate = new Date();
        this.height = generator.Height();
        this.birthday = generator.Birthday(); //генератор дат
        this.weight = generator.Weight();
        this.eyeColor = generator.Color();
        this.location = generator.Location();
    }

    public static Person PersonSet() {
        Setter s = new Setter();
        return new Person(s.Name(), s.Coordinates(), s.Height(), s.Weight(), s.Birthday(), s.Color(), s.Location());
    }

    public static Person PersonSet(int id) {
        Setter s = new Setter();
        Person p = new Person(s.Name(), s.Coordinates(), s.Height(), s.Weight(), s.Birthday(), s.Color(), s.Location());
        p.setId(id);
        return p;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getCoordinates() {
        return coordinates.getX() + "° . " + coordinates.getY() + "°";
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Long getHeight() {
        return height;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Double getWeight() {
        return weight;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public String getLocation() {
        return location.getX() + ":" + location.getY() + ":" + dF.format(location.getZ()) + " Место:" + " "+ location.getName();
    }

    private static class Setter implements PersonSet {
        private static boolean flag = true;
        private final Scanner console = new Scanner(System.in);

        @Override
        public Integer Id() {
            return new Generator().Id();
        }

        @Override
        public String Name() {
            String name;
            try {
                System.out.print("Имя: ");
                name = console.nextLine();
                if (name.equals("")) throw new EmptyLineException("Имя");
            } catch (EmptyLineException e) {
                System.out.println(e);
                return new Setter().Name();
            }

            return name;
        }

        @Override
        public Coordinates Coordinates() {
            long x = 0, y = 0;

            try {
                System.out.print("Координаты: ");
                x = Long.parseLong(console.next().strip());
                y = Long.parseLong(console.nextLine().strip());
            } catch (RuntimeException e) {
                System.out.println("Неверный тип ввода. +");
                new Setter().Coordinates();
            }
            return new Coordinates(x, y);
        }

        @Override
        public Long Height() {
            long h = 1;
            try {
                System.out.print("Рост: ");
                h = Long.parseLong(console.nextLine());
                if (h <= 0) throw new RuntimeException("Рост не может быть равен 0 или ниже.");
            } catch (InputMismatchException e) {
                System.out.println("Неверный тип ввода. ");
                new Setter().Height();
            } catch (RuntimeException e1) {
                System.out.println(e1.getMessage());
                return new Setter().Height();
            }
            return h;
        }

        @Override
        public Double Weight() {

            double w = 1;
            try {
                System.out.print("Вес: ");
                w = Float.parseFloat(console.nextLine());
                if (w <= 0) throw new RuntimeException("Вес не может быть равен 0 или ниже.");
            } catch (InputMismatchException e) {
                System.out.println("Неверный тип ввода. ");
                new Setter().Weight();
            } catch (RuntimeException e1) {
                System.out.println(e1.getMessage());
                return new Setter().Weight();
            }

            return w;
        }

        @Override
        public LocalDate Birthday() {

            int day, month, year;

            try {
                System.out.print("Введите дату рождения (день-месяц-год): ");
                day = Integer.parseUnsignedInt(console.next().strip());
                month = Integer.parseUnsignedInt(console.next().strip());
                year = Integer.parseUnsignedInt(console.nextLine().strip());
                if (!(day > 0 && month > 0 && year > 0 && day <= 31 && month <= 12))
                    throw new RuntimeException("Неверные границы дат.");
            } catch (InputMismatchException e) {
                System.out.println("Неверный тип ввода. ");
                return new Setter().Birthday();
            } catch (RuntimeException e1) {
                System.out.println(e1.getMessage());
                return new Setter().Birthday();
            }
            return LocalDate.of(year, month, day);

        }

        @Override
        public Color Color() {
            Color answer;
            if (flag){
                System.out.println("Введите цвет глаз из доступных: ");
                for (Color colors : Color.values()) {
                    System.out.println(colors.getName());
                }
                flag = false;
            }

            try {
                System.out.print("Цвет: ");
                console.reset();
                answer = Color.find(console.nextLine());
                if (answer == null)
                    throw new RuntimeException("Нет такого цвета.");
                flag = true;

            } catch (RuntimeException e1) {
                System.out.println(e1.getMessage());
                return new Setter().Color();
            }
            return answer;
        }

        @Override
        public Location Location() {
            String name;
            try {
                System.out.print("Введите название локации: ");
                name = console.nextLine();
                if (name.equals("")) throw new EmptyLineException("Локация");
            } catch (EmptyLineException e) {
                System.out.println(e);
                return new Setter().Location();
            }
            int x = new Random().nextInt(180);
            int y = new Random().nextInt(180);
            float z = new Random().nextFloat() * 100;

            System.out.printf("%s° : %s° : %s \n", x, y, z);
            return new Location(x, y, z, name);
        }
    }
}
