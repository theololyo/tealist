package teaIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import tealist.Tea;

/**
 * Extracts information about teas from a text file
 *
 * @author Markovic
 */
public class TextParser extends TeaParser {

    /**
     * Extracts information about teas from a text file. Text file must be
     * formated as followed category;name;price;description[new line]
     *
     * @param file the file to process
     * @return a list of tea objects
     * @throws IOException if something goes wrong while parsing
     */
    @Override
    protected List<Tea> parseFile(File file) throws IOException {

        List<Tea> teaList = new ArrayList<Tea>();
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);

        String line;

        try {
            while ((line = br.readLine()) != null) {
                Tea tea = new Tea();
                String[] teaLine = line.split(";");
                tea.category = teaLine[0];
                tea.name = teaLine[1];
                tea.price = Integer.valueOf(teaLine[2]);
                tea.description = teaLine[3];
                teaList.add(tea);

            }
            br.close();
        } catch (Exception e) {
            throw new IOException("Input file (" + file.getName() + ") not correct format");
        }
        return teaList;
    }

}
