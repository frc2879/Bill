// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class MecanumDriveCommand extends CommandBase {
  private final Drivetrain drivetrain;
  private final Supplier<Double> getX;
  private final Supplier<Double> getY;
  private final Supplier<Double> getA;
  private final Supplier<Double> getThrottle;

  /** Creates a new MecanumDriveCommand. */
  public MecanumDriveCommand(Drivetrain drivetrain, Supplier<Double> getX, Supplier<Double> getY, Supplier<Double> getA, Supplier<Double> getThrottle) {
    this.drivetrain = drivetrain;
    this.getThrottle = getThrottle;
    this.getX = () -> {return (getX.get() * this.getThrottle.get());};
    this.getY = () -> {return (getY.get() * this.getThrottle.get());};
    this.getA = () -> {return (getA.get() * this.getThrottle.get());};
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.mecanumMove(this.getX.get(), this.getY.get(), getA.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
