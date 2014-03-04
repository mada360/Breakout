package breakout;


/**
 * Major constants used in the game
 */
public class Global
{
  // Name of colours used
  enum Colour  { RED, BLUE, GRAY };

  // Playing area 
  public static final int H            = 800; // Height of window
  public static final int W            = 600; // Width of window
  
  public static final int B            = 6;  // Border offset
  public static final int M            = 40; // Menu offset
  
  // Size of things
  public static final int BALL_SIZE    = 30; // Ball side
  public static final int BRICK_WIDTH  = 50; // Brick size
  public static final int BRICK_HEIGHT = 25;
  public static final int BRICKS       = 32; // Bricks

  
  public static final int BAT_MOVE     = 25;  // Distance to move bat
   
  // Scores
  public static final int HIT_BRICK    = 50;  // Score
  public static final int HIT_BOTTOM   = -200;// Score

  public static final boolean DEBUG    = false;
}

