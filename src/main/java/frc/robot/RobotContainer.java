package frc.robot;

import commands.Allarmsdown;
import commands.Down;
import commands.FlipBall;
import commands.LockedStrafeCommand;
import commands.MecanumDriveCommand;
import commands.RaiseArms;
import commands.UPS;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Climberarms;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.FlipperStopper;

/**
 * Contains subsystem instances, command bindings, etc.
 * to be used in {@link Robot}.
 */
public class RobotContainer {
    public final FlipperStopper flipper = new FlipperStopper();
    public static Drivetrain drive = new Drivetrain();
    public final OI oi = new OI();
    public final Climberarms arms = new Climberarms();
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
        JoystickButton armsup = new JoystickButton(oi.getJoystick(), 5);
        JoystickButton armsdown = new JoystickButton(oi.getJoystick(), 3);
        JoystickButton raisearm1 = new JoystickButton(oi.getJoystick(),6);
        JoystickButton Allarmsdown = new JoystickButton(oi.getJoystick(), 4);
        // Bindings
        trigger.whenPressed(new FlipBall(flipper));
        povStickTrigger.whenActive(new LockedStrafeCommand(drive, oi::getStickHat, oi::getStickT));
        armsup.whileHeld(new UPS(arms));
        armsdown.whileHeld(new Down(arms));
        raisearm1.whenPressed(new RaiseArms(arms));
        Allarmsdown.whenPressed(new Allarmsdown(arms));
    }
}
