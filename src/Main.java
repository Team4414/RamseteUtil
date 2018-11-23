import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import logging.CSVLogger;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static RobotPos masterPos = new RobotPos(0,0,0);
    public static double mLeftVelocity, mRightVelocity;

    public static final long kControllerTimestep = 10;
    public static final long kLocalizerTimestep = 10;

    public static final double kWheelBase = 3.14;

    public static void main(String[] args){
        AutonController mController = new AutonController(kControllerTimestep, kWheelBase);
        Odometery mOdometery = new Odometery(new RobotPos(0,0,0), kLocalizerTimestep, kWheelBase);

        ScheduledExecutorService odometery  = Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService controller = Executors.newSingleThreadScheduledExecutor();

        mController.trackPath(generateTraj());

        odometery.scheduleAtFixedRate(mOdometery, 0, kLocalizerTimestep, TimeUnit.MILLISECONDS);
        controller.scheduleAtFixedRate(mController, 0, kControllerTimestep, TimeUnit.MILLISECONDS);


        odometery.execute(mOdometery);
        controller.execute(mController);

        mController.updateState();
//        while (mController.getStatus().equals(Ramsete.Status.TRACKING));

        try {
            Thread.sleep(1000);
        }catch (Exception e){}

        odometery.shutdown();
        controller.shutdown();

        CSVLogger.logCSV("PositionLog", mOdometery.positionLogger.get());
    }


    private static Trajectory generateTraj(){

        System.out.println("Traj Generated");
        Waypoint[] points = new Waypoint[] {

                new Waypoint(0, 0, Pathfinder.d2r(180)),
                new Waypoint(-2, -2, Pathfinder.d2r(180)),
                new Waypoint(-4, 0, Pathfinder.d2r(90)),
                new Waypoint(-2, 2, Pathfinder.d2r(0)),
                new Waypoint(2, 0, Pathfinder.d2r(-90)),
                new Waypoint(0, -4, Pathfinder.d2r(-180))

        };


        Trajectory.Config config = new Trajectory.Config(
                Trajectory.FitMethod.HERMITE_CUBIC,
                Trajectory.Config.SAMPLES_HIGH,
                kControllerTimestep,
                4, 4, 10000);

        return Pathfinder.generate(points, config);
    }
}
