/**----------------------------------------------------------------------------
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             
/* Open Source Software - may be modified and shared by FRC teams. The code   
/* must be accompanied by the FIRST BSD license file in the root directory of 
/* the project.                                                               
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class FlipperStopper extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  ControlMode po = ControlMode.PercentOutput;

  CANSparkMax motor1 = new CANSparkMax(RobotMap.spinnerflipper, CANSparkMaxLowLevel.MotorType.kBrushless);
 
  float lockAngle;

  //frw.setIdleMode(IdleMode.kBrake);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    motor1.restoreFactoryDefaults();
    // setDefaultCommand(new MySpecialCommand());
  }

  public void spin() {
     motor1.set(-0.75);
  }

public void stop() {
  motor1.stopMotor();
}








}