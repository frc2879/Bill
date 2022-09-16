// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class LockedStrafeCommand extends CommandBase {
  private final Drivetrain drivetrain;
  private final Supplier<Integer> desiredHeading;
  private final Supplier<Double> throttle;

  /** Creates a new LockedStrafeCommand. */
  public LockedStrafeCommand(Drivetrain drivetrain, Supplier<Integer> desiredHeadingAngle, Supplier<Double> throttle) {
    this.drivetrain = drivetrain;
    this.desiredHeading = desiredHeadingAngle;
    this.throttle = throttle;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.setLock();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.translateWhileLocked(desiredHeading.get(), throttle.get() / 2);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // the POV joystick has a value if -1 when it's not being pressed in any direction.
    return desiredHeading.get() < 0;
  }
}
