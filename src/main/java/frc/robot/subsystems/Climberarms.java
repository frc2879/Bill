// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climberarms extends SubsystemBase {
  CANSparkMax motor1;
  CANSparkMax motor2;
  DigitalInput input1;
  DigitalInput input2;
  DigitalInput input4;

  /** Creates a new Climberarms. */
  public Climberarms() {
    motor1 = new CANSparkMax(RobotMap.arm1motor, CANSparkMaxLowLevel.MotorType.kBrushless);
    motor2 = new CANSparkMax(RobotMap.arm2motor, CANSparkMaxLowLevel.MotorType.kBrushless);
    motor1.restoreFactoryDefaults();
    motor2.restoreFactoryDefaults();
    input1 = new DigitalInput(RobotMap.arm1switch);
    input2 = new DigitalInput(RobotMap.arm2switch);
    input4 = new DigitalInput(RobotMap.sensor1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void allup() {

    motor1.set(0.25);
    motor2.set(0.25);

  }

  public void alldown() {
    if (!anyarmdown()) {
      motor1.set(-025);
      motor2.set(-0.25);
    }

  }

  public void up1() {
    motor1.set(0.25);
  }
public boolean isarm1up(){
return input4.get();
}
  public void up2() {
    motor2.set(0.25);
  }

  public void down1() {

    // if (!armdown1()) {
      motor1.set(-0.25);
    // } else {
      // stop1();
    // }
  }

  public void down2() {

    if (!armdown2()) {
      motor2.set(-0.25);
    } else {
      stop2();
    }
  }

  public boolean anyarmdown() {
    return input1.get() || input2.get();
  }

  public boolean armdown1() {
    return input1.get();
  }

  public boolean armdown2() {
    return input2.get();
  }


  public void allstop() {
    motor1.stopMotor();
    motor2.stopMotor();
  }

  public void stop1() {
    motor1.stopMotor();
  }

  public void stop2() {
    motor2.stopMotor();
  }

  public void alldownUnsafe() {
    motor1.set(-0.25);
    motor2.set(-0.25);
  }
  
}
