// File [Main_java_BREAKOUT.html] MAS - last created: Mon 11 Nov 2013 15:40:29 GMT

package breakout;


/**
 * Start the game
 *  The call to startGame() in the model starts the actual play of the game
 *  All issues of mutual exclusion on access to objects etc. are ignored
 */
public class Main
{
    public static void main( String args[] )
    {
        Debug.trace("BreakOUT");
        ModelBreakout model = new ModelBreakout();
        ViewBreakout  view  = new ViewBreakout();
        new ControllerBreakout( model, view );
        Sounds.music();

        model.createGameObjects();       // Bricks/ bat/ ball
        model.addObserver( view );       // Add observer to the model

        view.setVisible(true);           // Display view
        model.startGame();               // Start play
    }
}
