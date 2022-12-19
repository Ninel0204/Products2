

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientLog {

    private List<String[]> log=new ArrayList<>();


    public void log1 (String productNum, String amount){
        log.add(((productNum)+ " , "+ amount).split(" "));
    }


    public  void exportAsCSV(File txtFile){
        try(CSVWriter csvWriter=new CSVWriter(new FileWriter(txtFile,true))){
            csvWriter.writeAll(log);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    
}
