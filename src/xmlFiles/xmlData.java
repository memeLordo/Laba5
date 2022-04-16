package xmlFiles;

import Exceptions.EmptyLineException;
import SetOfCommands.Command;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.lang.System.in;
import static java.nio.charset.StandardCharsets.UTF_8;

public abstract class xmlData {

    protected static String path = "example.xml";

    public static String getDirectory() {
        return directory+"/";
    }

    protected static String directory = "xmlFilesPack";


    public static String setPath() {
        try {
            System.out.print("Введите название xml-файла: ");
            path = new Scanner(in).nextLine();
            if (path.equals("")) throw new EmptyLineException("Имя файла");
        } catch (EmptyLineException e) {
            System.out.println(e.getMessage());
            setPath();
        }
        return getDirectory() + path + ".xml";
//        return path + ".xml";
    }

    static void write(String text) {
        try (Writer translator = new OutputStreamWriter(new FileOutputStream(getDirectory()+path), UTF_8)) {
            translator.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String read() {
        StringBuilder line = new StringBuilder();
        try {
            checkDirectory(directory);
            path = setPath();
            Scanner fr = new Scanner(Paths.get(path));

            while (fr.hasNext()){
                line.append(fr.nextLine()).append("\n");
            }
        } catch (NoSuchFileException e1) {

            System.out.println("Такого файла не существует. Хотите создать файл? (y/n)");
            try {
                String choice = new Scanner(System.in).next("[yn]");
                if ("y".equals(choice)){
                    Command.CreateFile(path);
                }
            } catch (NoSuchElementException ignored) {
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return line.toString();
    }

    public static boolean checkDirectory(String directory) throws IOException {
        if (!Files.exists(Paths.get(directory))) Files.createDirectory(Paths.get(directory));
        return Files.exists(Paths.get(directory));
    }
}
