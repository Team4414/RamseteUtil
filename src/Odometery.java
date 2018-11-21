import logging.Loggable;

public class Odometery extends ForwardKinematics implements Runnable{

    private static final double kWheelRadius = 1;
    private static double kWheelBase = 1;

    public static Loggable positionLogger;

    private ForwardKinematics kine;

    public Odometery(RobotPos initialPos, double timestep, double wheelBase){
        super(kWheelRadius,wheelBase, timestep, initialPos);
        setupLogger();
        kWheelBase = wheelBase;
    }

    @Override
    public void run() {
        kine.update();
        positionLogger.log();
        Main.masterPos = kine.getRobotPos();
    }

    @Override
    protected double getLeftWheelVelocity() {
        return Main.mLeftVelocity;
    }

    @Override
    protected double getRightWheelVelocity() {
        return Main.mRightVelocity;
    }

    private void setupLogger(){
        positionLogger = new Loggable(){
            @Override
            protected LogObject[] collectData() {
                return new LogObject[]{
                        new LogObject("X_Position", Main.masterPos.getX()),
                        new LogObject("Y_Position", Main.masterPos.getY()),
                        new LogObject("Heading", Main.masterPos.getHeading())

                };
            }
        };
    }
}