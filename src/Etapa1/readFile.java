package Etapa1.Files;

import java.io.*;
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

    public ArrayList<String []> readFromFile(String path){

        try {
            File file = new File(path);
            Scanner in = new Scanner(file);
            ArrayList <String []> text= new ArrayList<String[]>();
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
            System.out.println("Error!");
            return null;
        }
    }

}
