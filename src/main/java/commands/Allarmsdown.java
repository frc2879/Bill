// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Climberarms;

public class Allarmsdown extends CommandBase {
private CommandScheduler sch;

  /** Creates a new Allarmsdown. */
  Climberarms arms;
  public Allarmsdown(Climberarms m) {
    this.arms = m;
    sch = CommandScheduler.getInstance();
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(arms);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    arms.alldown();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if(!arms.hooked()) {
      sch.schedule(new UPS(arms));
    }else{
      arms.allstop();
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !arms.hooked() || arms.anyarmdown();


      
  }
}
