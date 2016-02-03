package tealist;

import teaIO.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import teaView.TeaView;

/**
 * Handles parsing of options and conversion between different files containing
 * information about tea.
 *
 * @author Thomas Aggesj
 * @version 2011-10-24
 */
public class TeaList implements TeaController {

    private List<TeaView> viewList;

    /**
     * Constructs a TeaList
     */
    public TeaList() {
        viewList = new ArrayList<>();

    }

    /**
     * Process a request containing one or more options. For information on
     * valid options see the readme file.
     *
     * @param args Options
     */
    public void processRequest(String[] args) {
        Map<Options, String> request = this.parseRequest(args);
        request = this.validateRequest(request);

        if (request.containsKey(Options.ERROR)) {
            this.showError(request.get(Options.ERROR));
        } else {
            if (request.containsKey(Options.LIST_FILE_FORMATS)) {
                notify(new HelperTexts().getHelp());
            } else if (request.containsKey(Options.HELP)) {
                notify(new HelperTexts().getHelp());
            } else if (request.containsKey(Options.FROM_FILE_FORMAT)) {
                try {
                    this.convertFile(request);
                } catch (Exception e) {
                    this.showError(e.getMessage());
                }
            }
        }
    }

    /**
     * Parse the request.
     *
     * @param args The options given to the program
     * @return a map with the options and there values when needed
     */
    private Map<Options, String> parseRequest(String[] args) {
        Map<Options, String> request = new HashMap<Options, String>();

        for (int i = 0; i < args.length; i++) {
            if (Options.HELP.equals(args[i])) {
                request.put(Options.HELP, null);
            } else if (Options.LIST_FILE_FORMATS.equals(args[i])) {
                request.put(Options.LIST_FILE_FORMATS, null);
            } else if (Options.FROM_FILE_FORMAT.equals(args[i])) {
                if (request.containsKey(Options.FROM_FILE_FORMAT)) {
                    request.put(Options.ERROR, "-f can only be used once");
                } else if (args.length > i + 1) {
                    request.put(Options.FROM_FILE_FORMAT, args[++i]);
                } else {
                    request.put(Options.ERROR, "-f must be followed by the file format");
                }
            } else if (Options.TO_FILE_FORMAT.equals(args[i])) {
                if (request.containsKey(Options.TO_FILE_FORMAT)) {
                    request.put(Options.ERROR, "-t can only be used once");
                } else if (args.length > i + 1) {
                    request.put(Options.TO_FILE_FORMAT, args[++i]);
                } else {
                    request.put(Options.ERROR, "-t must be followed by the file format");
                }
            } else if (Options.INPUT_FILE.equals(args[i])) {
                if (request.containsKey(Options.INPUT_FILE)) {
                    request.put(Options.ERROR, "-i can only be used once");
                } else if (args.length > i + 1) {
                    request.put(Options.INPUT_FILE, args[++i]);
                } else {
                    request.put(Options.ERROR, "-i must be followed by the name of the input file");
                }
            } else if (Options.OUTPUT_FILE.equals(args[i])) {
                if (request.containsKey(Options.OUTPUT_FILE)) {
                    request.put(Options.ERROR, "-o can only be used once");
                } else if (args.length > i + 1 && !args[i + 1].startsWith("-")) {
                    request.put(Options.OUTPUT_FILE, args[++i]);
                } else {
                    request.put(Options.OUTPUT_FILE, null);
                }
            } else {
                request.put(Options.ERROR, "Invalid option: " + args[i]);
            }
        }
        return request;
    }

    /**
     * Checks if a request is valid. If there is an error an error value is
     * added to the map with information about the error
     *
     * @param request The parsed options given to the program
     * @return a map of the options
     */
    private Map<Options, String> validateRequest(Map<Options, String> request) {
        if (!request.containsKey(Options.ERROR)) {
            Set<Options> params = request.keySet();

            boolean fileConversionExists = false;
            Options[] fileConversion = {Options.FROM_FILE_FORMAT, Options.TO_FILE_FORMAT, Options.INPUT_FILE};

            for (Options param : params) {
                for (Options fileParam : fileConversion) {
                    if (param.equals(fileParam)) {
                        fileConversionExists = true;
                    }
                }
            }
            if (fileConversionExists) {
                if (!params.containsAll(Arrays.asList(fileConversion))) {
                    request.put(Options.ERROR, "-f -t and -i must all be used when converting files");
                }
            }
        }
        return request;
    }

    /**
     * Prints any errors to standard out
     *
     * @param error the error that happened
     */
    private void showError(String error) {
        notify("Error: " + error);
        notify(new HelperTexts().getHelp());
    }

    /**
     * Converts tea data from one file to an other.
     *
     * @param options any options given for the conversion
     * @throws Exception if there was an error while converting
     */
    private void convertFile(Map<Options, String> options) throws Exception {
        ConverterFactory cf = new ConverterFactory();
        List<Tea> teaList = new ArrayList<Tea>();

        String inFileName = options.get(Options.INPUT_FILE);
        String inFileFormat = options.get(Options.FROM_FILE_FORMAT);
        String outFileFormat = options.get(Options.TO_FILE_FORMAT);
        String outFileName = options.get(Options.OUTPUT_FILE);
        if (!FileFormats.isValid(inFileFormat)) {
            throw new Exception("Unknown input file format: " + inFileFormat);
        } else if (!FileFormats.isValid(outFileFormat)) {
            throw new Exception("Unknown output file format: " + outFileFormat);
        }
        teaList = cf.getTeaParser(inFileFormat).readFile(inFileName);
        cf.getTeaWriter(outFileFormat).writeFile(teaList, outFileName);
    }

    /**
     * Notifies subscribed views
     *
     * @param message message to be delivered
     */
    @Override
    public void notify(String message) {
        for (TeaView view : viewList) {
            view.update(message);
        }
    }

    /**
     * Adds a view to a collection of views interested in this controllers
     * updates
     *
     * @param view the view to add
     */
    @Override
    public void addView(TeaView view) {
        viewList.add(view);
    }
}
