package commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FlipperStopper;

public class FlipBall extends CommandBase {
    private final FlipperStopper flipper;

    public FlipBall(FlipperStopper flipper) {
        this.flipper = flipper;
        addRequirements(flipper);
    }

    @Override
    public void initialize() {
        flipper.resetCounter();
        flipper.spin();
    }

    @Override
    public void execute() {
        // Not much else to do...
    }

    @Override
    public void end(boolean interrupted) {
        flipper.stop();
    }

    @Override
    public boolean isFinished() {
        return flipper.getSpinCount() >= 2;
    }
}
