// File [ModelBreakout_java_BREAKOUT.html] MAS - last created: Mon 11 Nov 2013 15:40:29 GMT

package breakout;

import java.util.Observable;
import static breakout.Global.*;
/**
 * Model of the game of breakout
 *  The active object ActiveModel does the work of moving the ball
 */

public class ModelBreakout extends Observable
{
    private GameObject ball;      // The ball
    private GameObject bricks[];  // The bricks
    private GameObject bat;       // The bat

    private ModelActivePart am  = new ModelActivePart( this );
    private Thread activeModel  = new Thread( am );

    private int score = 0;

    public void createGameObjects()
    {
        ball   = new GameObject(W/2, H/2, BALL_SIZE, BALL_SIZE, Colour.RED );
        bat    = new GameObject(W/2, H - BRICK_HEIGHT*4, BRICK_WIDTH*3,
                BRICK_HEIGHT, Colour.GRAY);
        /*BRICKS = size of array */
        bricks = new GameObject[BRICKS];


        int brickX = 20;
        int brickY = 140;
        for(int i=0;i<bricks.length;i++){
            bricks[i] = new GameObject(brickX, brickY, BRICK_WIDTH,
                    BRICK_HEIGHT, Colour.BLUE);
            brickX += 70;

            if(brickX >= W - B - BRICK_WIDTH){
                brickY += 40;
                brickX = 20;
            }
        }
    }

    public void startGame()             { activeModel.start(); }

    public GameObject getBall()         { return ball; }

    public GameObject[] getBricks()     { return bricks; }

    public GameObject getBat()          { return bat; }

    public void addToScore( int n )     { score += n; }

    public int getScore()               { return score; }

    public void stopGame()              { }


    /**
     * Move the bat dist pixels. (-dist) => left or (+dist) => right
     * @param dist - Distance to move
     */
    public void moveBat( float dist )
    {
        float batX = bat.getX();
        if(batX >= W - B - BRICK_WIDTH*3 ){
            dist = dist - 30;
        }else if(batX <= 0 + B ){
            dist = dist + 30;
        }
        Debug.trace( "Model: Move bat = %6.2f", dist );
        bat.moveX(dist);
        //modelChanged();
    }

    /**
     * Model has changed so notify observers so that they
     *  can redraw the current state of the game
     */
    public void modelChanged()
    {
        setChanged(); notifyObservers();
    }

}