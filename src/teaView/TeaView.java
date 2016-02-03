package teaView;

/**
 * Represents a view which displays information from controllers
 *
 * @author Markovic
 */
public interface TeaView {

    /**
     * Updates this view with information from controllers whom this view is
     * registered to
     *
     * @param message the message do display
     */
    public void update(String message);
}
