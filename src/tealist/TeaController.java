package tealist;

import teaView.TeaView;

/**
 * Verifies and parses requests
 *
 * @author Markovic
 */
public interface TeaController {

    /**
     * Notifies subscribed views
     *
     * @param message message to be delivered
     */
    public void notify(String message);

    /**
     * Adds a view to a collection of views interested in this controllers
     * updates
     *
     * @param view the view to add
     */
    public void addView(TeaView view);
}
