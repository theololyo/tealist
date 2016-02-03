package tealist;

import teaView.ConsoleView;

/**
 * Program entry point. See readme file for information about valid options.
 *
 * @author Thomas Aggesj
 * @version 2011-10-10
 *
 */
public class TeaListMain {

    /**
     * Program entry point. See readme file for information about valid options.
     *
     * @param args Program parameters
     */
    public static void main(String[] args) {
        TeaList tl = new TeaList();
        tl.addView(new ConsoleView());
        tl.processRequest(args);
    }
}
