import java.awt.*;

/**
 * Forward Kinematics Class.
 *
 * <P>Utility to handle the mathematics of Forward Kinematics</P>
 * 
 * @author Avidh Bavkar <avidh@team4414.com>
 */
public abstract class ForwardKinematics {
    /**
    * Radius of the drive wheels
    */
   private final double kRadius;
    /**
    * Distance between the centers of both wheels
    */
   private final double kWheelBase;
    /**
    * The amount, in seconds, between calls of the "update" method
    */
   private final double kTimeStep;
    /**
    * Robot X Position
    */
   private RobotPos mRobotPos;
    /**
    * Constructor.
    *
    * @param wheelRadius Radius of the drive wheels.
    * @param wheelBaseLength Distance between the centers of both wheels.
    */
   public ForwardKinematics(double wheelRadius, double wheelBaseLength, double timestep){
       kRadius = wheelRadius;
       kWheelBase = wheelBaseLength;
       kTimeStep = timestep;
   }
    /**
    * Constructor.
    *
    * @param wheelRadius Radius of the drive wheels.
    * @param wheelBaseLength Distance between the centers of both wheels.
    * @param initPos The initial {@link RobotPos} of the robot.
    */
   public ForwardKinematics(double wheelRadius, double wheelBaseLength, double timestep, RobotPos initPos){
       this(wheelRadius, wheelBaseLength, timestep);
       mRobotPos = initPos;
   }
    /**
    * Update Method.
    *
    * <P>Integrates coordinates and heading of the robot. Expected to be called once per specified TIMESTEP</P>
    */
   public void update(){
       mRobotPos = new RobotPos(
               mRobotPos.getX() + kTimeStep * getDeltaX(),
               mRobotPos.getY() + kTimeStep * getDeltaY(),
               mRobotPos.getHeading() + kTimeStep * getDeltaHeading()
       );
   }
    /**
    * Get X Position Method.
    * @return The current X position of the robot.
    */
   public RobotPos getRobotPos(){
       return new RobotPos(mRobotPos);
   }
    /**
    * Get Delta X Method.
    *
    * Calculates the change in the X Position of the Robot with respect to wheel velocities and heading.
    *
    * @return The change in X Position.
    */
   private double getDeltaX(){
//       System.out.println((kRadius/2) + "\t"+
//               getLeftWheelVelocity() + "\t" +
//               getRightWheelVelocity() + "\t" +
//               Math.cos(mRobotPos.getHeading()));
       return (kRadius/2) * (getLeftWheelVelocity() + getRightWheelVelocity()) * Math.cos(mRobotPos.getHeading());
   }
    /**
    * Get Delta Y Method.
    *
    * Calculates the change in the Y Position of the Robot with respect to wheel velocities and heading.
    *
    * @return The change in Y Position.
    */
   private double getDeltaY(){
       return (kRadius/2) * (getLeftWheelVelocity() + getRightWheelVelocity()) * Math.sin(mRobotPos.getHeading());
   }
    /**
    * Get Delta Heading Method.
    *
    * Calculates the change in the Heading of the Robot with respect to wheel velocities.
    *
    * @return The change in heading.
    */
   private double getDeltaHeading(){
       return (kRadius/kWheelBase) * (getRightWheelVelocity() - getLeftWheelVelocity());
   }
    /**
    * Get Left Wheel Velocity Method.
    *
    * @return The current measured velocity of the left wheel.
    */
   protected abstract double getLeftWheelVelocity();
    /**
    * Get Left Wheel Velocity Method.
    *
    * @return The current measured velocity of the right wheel.
    */
   protected abstract double getRightWheelVelocity();
}