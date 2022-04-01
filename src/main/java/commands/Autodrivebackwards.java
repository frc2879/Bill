// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package commands;

import com.ctre.phoenix.time.StopWatch;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class Autodrivebackwards extends CommandBase {
  StopWatch stopwatch = new StopWatch();
  Drivetrain drivingSubsystem;
  /** Creates a new Autodriveforward. */
  public Autodrivebackwards(Drivetrain subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    drivingSubsystem = subsystem;
    addRequirements(drivingSubsystem);
    
  }

public void start(){}
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  
    stopwatch.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    drivingSubsystem.mecanumMove(0, -0.1, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivingSubsystem.mecanumMove(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return stopwatch.getDuration() >=2;
  }
}
