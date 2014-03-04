package breakout;

import static breakout.Global.*;

/**
 * A class used by the model to give it an active part which continuously moves
 * the ball and decides what to do on a collision
 */
public class ModelActivePart implements Runnable
{
  private ModelBreakout model;
  private boolean runGame = true;           // Assume write to is atomic

  public ModelActivePart(ModelBreakout aBreakOutModel)
  {
    model = aBreakOutModel;
  }
  
  /**
   * Stop game, thread will finish
   */
  public void stopGame() { runGame = false; }
  
  /**
   * Code to position the ball after time interval
   *  and work out what happens next
   */
  
  @Override
  public void run()
  {
    final float S = 3; // Units to move
    try
    {
      GameObject ball     = model.getBall();     // Ball in game
      GameObject bricks[] = model.getBricks();   // Bricks
      GameObject bat      = model.getBat();      // Bat

      while (runGame)
      {
        double x = ball.getX();
        double y = ball.getY();
        // Deal with possible edge of board hit
        if (x >= W - B - BALL_SIZE)  ball.changeDirectionX();
        if (x <= 0 + B            )  ball.changeDirectionX();
        if (y >= H - B - BALL_SIZE) 
        { 
          ball.changeDirectionY(); model.addToScore( HIT_BOTTOM ); 
        }
        if (y <= 0 + M            )  ball.changeDirectionY();

        ball.moveX(S);  ball.moveY(S);

        // As only a hit on the bat/ball is detected it is assumed to be
        // on the top or bottom of the object
        // A hit on the left or right of the object has an interesting affect

          boolean hit = false;

          for(int i=0;i<bricks.length;i++){
              if(bricks[i].hitBy(ball) == GameObject.Collision.HIT){
                  model.addToScore(HIT_BRICK);
                  ball.changeDirectionY();
                  try {
                      Thread.sleep(1000);
                  }
                  catch (InterruptedException ie) {
                      // Handle the exception
                  }
                  bricks[i] = null;

              }else if(bricks[i].hitBy(ball) == GameObject.Collision.HIT_SIDE){
                  model.addToScore(HIT_BRICK);
                  ball.changeDirectionX();
              }
          }
          // * Fill in code to check if a brick has been hit              *
        // *  Remember to remove a brick in the array  (if hit)         *
        // *    [remove] - set the array element to null                *
        // **************************************************************


        if (bat.hitBy(ball) == GameObject.Collision.HIT){
          ball.changeDirectionY();
        }else if(bat.hitBy(ball) == GameObject.Collision.HIT_SIDE){
            ball.changeDirectionX();
        }

        model.modelChanged(); // Model changed refresh screen

        Thread.sleep(10);     // About 50 Hz
      }
    } catch (Exception e) { } ;
  }
}

