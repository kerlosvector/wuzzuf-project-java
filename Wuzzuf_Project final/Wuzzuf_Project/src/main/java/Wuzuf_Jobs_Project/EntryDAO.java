package Wuzuf_Jobs_Project;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class EntryDAO {
    List<Entry> entries;

    public EntryDAO(String rawFileName) {
        this.entries = new ArrayList<Entry>();

        // Access the CSV data file
        File entryFile = new File(rawFileName);

        //real all lines in the CSV file and save them in a list
        List<String> lines = new ArrayList<String>();

        try{
            lines = Files.readAllLines(entryFile.toPath());
        } catch (Exception e) {
            System.out.println("An issue happened while reading the file");
        }

        // extract all entries and save them to a list
        for (int lineIdx = 1; lineIdx < lines.size(); lineIdx++){
            String line = lines.get(lineIdx);

            String[] fields = line.split(",(?=(?:[^\"]\"[^\"]\")[^\"]$)", -1);
            for (int fieldIndex=0; fieldIndex<lines.size();fieldIndex++){
                fields[fieldIndex] = fields[fieldIndex].trim();
            }

            Entry entry = new Entry(fields[0], fields[1],fields[2],fields[3],fields[4],fields[5],fields[6],fields[7],fields[8]);
//            List<String> entries.add(entry);

        }

        System.out.println("Total Vacancies imported are:"+entries.size());




    }
}
