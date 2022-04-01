// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.SparkMaxLimitSwitch.Type;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climberarms extends SubsystemBase {
  CANSparkMax motor1;
  SparkMaxLimitSwitch m1Limit;
  CANSparkMax motor2;
  SparkMaxLimitSwitch m2Limit;
  DigitalInput input1;
  DigitalInput input2;
  DigitalInput arm1ExtensionSwitch;
  DigitalInput arm2ExtensionSwitch;
  DigitalInput arm1hooksensor;
  DigitalInput arm2hooksensor;

  /** Creates a new Climberarms. */
  public Climberarms() {
    motor1 = new CANSparkMax(RobotMap.arm1motor, CANSparkMaxLowLevel.MotorType.kBrushless);
    motor2 = new CANSparkMax(RobotMap.arm2motor, CANSparkMaxLowLevel.MotorType.kBrushless);
    motor1.restoreFactoryDefaults();
    motor2.restoreFactoryDefaults();
    motor1.setIdleMode(IdleMode.kBrake);
    motor2.setIdleMode(IdleMode.kBrake);
    m1Limit = motor1.getReverseLimitSwitch(Type.kNormallyOpen);
    m2Limit = motor2.getReverseLimitSwitch(Type.kNormallyOpen);
    m1Limit.enableLimitSwitch(true);
    m2Limit.enableLimitSwitch(true);
    input1 = new DigitalInput(RobotMap.arm1switch);
    input2 = new DigitalInput(RobotMap.arm2switch);
    arm1ExtensionSwitch = new DigitalInput(RobotMap.arm1ExtensionSensor);
    arm2ExtensionSwitch = new DigitalInput(RobotMap.arm2ExtensionSensor);
    arm1hooksensor = new DigitalInput(RobotMap.arm1hooksensor);
    arm2hooksensor = new DigitalInput(RobotMap.arm2hooksensor);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void allup() {
    up1();
    up2();
  }

  public void alldown() {
    down1(); down2();
  }

  public void up1() {
    if(!isArm1Up()) {
      motor1.set(0.25);
    } else {
      stop1();
    }
  }

  public void up2() {
    if(!isArm2Up()) {
      motor2.set(0.25);
    } else {
      stop2();
    }
  }

  public boolean isArm1Up() {
    return arm1ExtensionSwitch.get();
  }

  public boolean isArm2Up() {
    return arm2ExtensionSwitch.get();
  }
  
  public boolean anyArmUp() {
    return isArm2Up() || isArm1Up();
  }

  public boolean bothArmsUp() {
    return isArm1Up() && isArm2Up();
  }

  public void down1() {
      motor1.set(-0.25);
  }

  public void down2() {
    motor2.set(-0.25);
  }
public boolean anyarmdown(){
  return m1Limit.isPressed() || m2Limit.isPressed();
}
public boolean hooked(){
  return !arm1hooksensor.get() && !arm2hooksensor.get();

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

}
