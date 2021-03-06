/**
 * RobotPos Class.
 *
 * <p>A simple object to hold the position of a robot that can only be set, not modified.</p>
 */
public class RobotPos{

    /**
     * The X position of the Robot
     */
    private double x;

    /**
     * The Y position of the Robot
     */
    private double y;

    /**
     * The heading of the Robot
     */
    private double heading;

    /**
     * RobotPos Constructor.
     *
     * @param x The X position of the robot.
     * @param y The Y position of the robot.
     * @param heading The heading of the robot.
     */
    public RobotPos(double x, double y, double heading){
        this.x = x;
        this.y = y;
        this.heading = heading;
    }

    /**
     * RobotPos Constructor
     *
     * @param toClone The RobotPos to be cloned.
     */
    public RobotPos(RobotPos toClone){
        this(toClone.getX(), toClone.getY(), toClone.getHeading());
    }

    /**
     * Get X Method.
     *
     * @return The X Position of the Robot
     */
    public double getX(){
        return x;
    }

    /**
     * Get Y Method.
     *
     *
     * @return The Y Position of the Robot
     */
    public double getY(){
        return y;
    }

    /**
     * Get Heading Method.
     *
     * @return The Heading of the Robot
     */
    public double getHeading(){
        return heading;
    }
}