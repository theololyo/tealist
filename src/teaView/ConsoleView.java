package teaView;

/**
 * Displays updated information in System.out
 *
 * @author Markovic
 */
public class ConsoleView implements TeaView {

    /**
     * Updates this view with information from controllers whom this view is
     * registered to
     *
     * @param message the message do display
     */
    @Override
    public void update(String message) {
        System.out.println(message);
    }
}
