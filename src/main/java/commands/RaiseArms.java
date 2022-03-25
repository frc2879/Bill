// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climberarms;


public class RaiseArms extends CommandBase {
 Climberarms arms;
  /** Creates a new Raisearm1. */
  public RaiseArms(Climberarms m1) {
  
    this.arms= m1;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(arms);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    arms.allup();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    arms.allstop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return arms.bothArmsUp();
  }
}