public class Odometery extends ForwardKinematics implements Runnable{

    private static final double kWheelRadius = 1;
    private static double kWheelBase = 1;

    private ForwardKinematics kine;

    public Odometery(RobotPos initialPos, double timestep, double wheelBase){
        super(kWheelRadius,wheelBase, timestep, initialPos);

        kWheelBase = wheelBase;
    }

    @Override
    public void run() {
        kine.update();

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
}