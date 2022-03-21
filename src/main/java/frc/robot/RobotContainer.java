package frc.robot;

import commands.FlipBall;
import commands.LockedStrafeCommand;
import commands.MecanumDriveCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.FlipperStopper;

/**
 * Contains subsystem instances, command bindings, etc.
 * to be used in {@link Robot}.
 */
public class RobotContainer {
    private final FlipperStopper flipper = new FlipperStopper();
    public static Drivetrain drive = new Drivetrain();
    public final OI oi = new OI();

    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();

        // Configure Default Commands
        drive.setDefaultCommand(new MecanumDriveCommand(drive, oi::getStickX, oi::getStickY, oi::getStickA, () -> {return ((1 - oi.getStickT()) / 2);}));
    }

    private void configureButtonBindings() {
        // Buttons
        JoystickButton trigger = new JoystickButton(oi.getJoystick(), 1);
        Trigger povStickTrigger = new Trigger(() -> { return oi.getJoystick().getPOV() >= 0; });

        // Bindings
        trigger.whenPressed(new FlipBall(flipper));
        povStickTrigger.whenActive(new LockedStrafeCommand(drive, oi::getStickHat, oi::getStickT));
    }
}
