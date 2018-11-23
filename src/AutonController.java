public class AutonController extends Ramsete implements Runnable{

    public AutonController(double timeStep, double wheelBase){
        super(timeStep, wheelBase);
    }

    @Override
    public void run(){
        this.update();

//        Main.mLeftVelocity = this.getVels().getLeft();
//        Main.mRightVelocity = this.getVels().getRight();
        Main.mRightVelocity = 1;
        Main.mLeftVelocity = 0;
    }

    @Override
    public RobotPos getRobotPos() {
        return Main.masterPos;
    }
}
