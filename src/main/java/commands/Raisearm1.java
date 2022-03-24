// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climberarms;


public class Raisearm1 extends CommandBase {
 Climberarms arm1;
  /** Creates a new Raisearm1. */
  public Raisearm1(Climberarms m1) {
  
    this.arm1= m1;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(arm1);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   arm1.up1(); 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    arm1.stop1();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return arm1.isarm1up();
  }
}
