package SetOfCommands;

import xmlFiles.xmlData;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static SetOfCommands.CommandsPack.*;

public class ExecuteFile extends Command{
    public ExecuteFile() {
        super("execute", "description", true);
    }
    private static Set<String> paths = new HashSet<>();
    @Override
    public void go() {
        String directory = "executeFilesPack";
        try {
            xmlData.checkDirectory(directory);
        } catch (IOException ignored) {
        }
        String filename  = inputCommand == null ?"commands.txt":inputCommand+".txt";

        if(!paths.contains(filename)){
            paths.add(filename);
        filename = directory +"/"+filename;
        CreateFile(filename);
        ScanFile(filename);}
        else System.out.println("Лол, ваши команды взвали петлю. Больше так не делайте.");
    }
    private void ScanFile(String text){
        Scanner console = null;
        String path, err;
        try {
            console = new Scanner(Path.of(text));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert console != null;
        console.useDelimiter(System.getProperty("line.separator"));
        while(console.hasNext()){
            try {
                path= mapFind(console.nextLine());
//                err = new ExecuteFile().getName();
//                if(path.equals(err)){
//                    System.err.print("Команда "+err+" не может присутствовать в файле.");
//                    continue;
//                }
                getMap().get(path).go();
            }catch (Exception ignored){}

        }
    }
}
