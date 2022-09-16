/**----------------------------------------------------------------------------
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             
/* Open Source Software - may be modified and shared by FRC teams. The code   
/* must be accompanied by the FIRST BSD license file in the root directory of 
/* the project.                                                               
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
/**
 * The thing which stops the ball, and then flips the ball out...
 */
public class FlipperStopper extends SubsystemBase {

  CANSparkMax motor1;
  //Initializes a DigitalInput on DIO 0
  DigitalInput input;
  Counter counter;

  //frw.setIdleMode(IdleMode.kBrake);

  public FlipperStopper() {
    motor1 = new CANSparkMax(RobotMap.spinnerflipper, CANSparkMaxLowLevel.MotorType.kBrushless);
    motor1.restoreFactoryDefaults();
    input = new DigitalInput(0);
    counter = new Counter(input);
    counter.reset();
    
  }

  public void resetCounter() {
    counter.reset();
  }

  public int getSpinCount() {
    return counter.get();
  }

  public void resetPosition() {
    while(input.get()) {
      motor1.set(0.4);
    }
    motor1.set(0);
  }

  public void spin() {
     motor1.set(-0.9);
  }

  public void stop() {
    motor1.stopMotor();
  }
}