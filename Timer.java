package breakout;

import java.util.Date;

/**
 * How long things take
 */
class Timer
{
  private static long startTime;

  public static void startTimer()
  {
    startTime = System.currentTimeMillis();
  }

  public static long readTimeTaken()
  {
    return System.currentTimeMillis() - startTime;
  }

 
  private static long getTimeInMills()
  {
    return System.currentTimeMillis();
  }
}
