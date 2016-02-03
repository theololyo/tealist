package teaIO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import tealist.Tea;

/**
 * Writes the tea information to a text file.
 *
 * @author Markovic
 */
public class TextWriter implements TeaWriter {

    /**
     * Writes the tea information to a text file. The information will be saved
     * to the file as following: category;name;price;description[new line]
     *
     * @param teaList A list of the tea
     * @param fileName Name of the file, if it is null the file will be written
     * to system.out
     * @throws IOException If there was an I/O error
     */
    @Override
    public void writeFile(List<Tea> teaList, String fileName) throws IOException {
        if (fileName == null) {
            for (Tea tea : teaList) {
                System.out.println(tea.category + ";" + tea.name + ";" + tea.price + ";" + tea.description);
            }
        } else {
            File file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);

            for (int i = 0; i < teaList.size(); i++) {
                Tea tea = teaList.get(i);
                bw.write(tea.category + ";" + tea.name + ";" + tea.price + ";" + tea.description);

                if (i + 1 != teaList.size()) {
                    bw.newLine();
                }
            }
            bw.close();
        }
    }
}
