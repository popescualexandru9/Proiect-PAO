package Etapa1.Files;

import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;

public class readFile {
    private static readFile single_instance = null;

    public static readFile getInstance() {
        if(single_instance == null)
            single_instance = new readFile();
        return single_instance;
    }

    private readFile() {
    }

    public void audit(String action) {

        try {
            FileWriter out = new FileWriter("C:\\Users\\Popi\\OneDrive\\Facultate\\2 year\\Sem 2\\PAO\\Proiect PAO\\src\\Etapa1\\Files\\auditFile.csv",true);
            Instant instant = Instant.now();

            out.append(action+","+instant+"\n");
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        catch (IOException e){
            System.out.println("IOException error!");
        }
    }

    public List<String []> readFromFile(String path){

        try {
            File file = new File(path);
            Scanner in = new Scanner(file);
            List <String []> text= new ArrayList<>();
            while(in.hasNextLine())
            {
                String temp = in.nextLine();
                String[] data = temp.split(",");
                text.add(data);

            }
            in.close();
            return text;
        }
        catch(FileNotFoundException e){
            System.out.println("File not found!");
            return null;
        }
    }

}
