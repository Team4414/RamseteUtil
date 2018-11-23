import logging.Loggable;

public class Odometery extends ForwardKinematics implements Runnable{

    private static final double kWheelRadius = 1.5;
    private static double kWheelBase = 3.14;

    public Loggable positionLogger;

    public Odometery(RobotPos initialPos, double timestep, double wheelBase){
        super(kWheelRadius,wheelBase, timestep, initialPos);
        setupLogger();
        kWheelBase = wheelBase;
    }

    @Override
    public void run() {
        this.update();
        positionLogger.log();
//        System.out.println("X Position" + Main.masterPos.getX() + "\t|\tY Position" + Main.masterPos.getY());
        Main.masterPos = this.getRobotPos();
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
                        new LogObject("Time", System.nanoTime()),
                        new LogObject("X_Position", Main.masterPos.getX()),
                        new LogObject("Y_Position", Main.masterPos.getY()),
                        new LogObject("Heading", Main.masterPos.getHeading())
                };
            }
        };
    }
}