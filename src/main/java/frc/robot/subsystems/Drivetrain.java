  /**----------------------------------------------------------------------------
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             
/* Open Source Software - may be modified and shared by FRC teams. The code   
/* must be accompanied by the FIRST BSD license file in the root directory of 
/* the project.                                                               
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SPI;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Drivetrain extends SubsystemBase {
  ControlMode po = ControlMode.PercentOutput;

  // NavX
  private static AHRS ahrs;
  private float yaw = 0;
  private float lockAngle;

  TalonFX frw = new TalonFX(RobotMap.frw);
  TalonFX flw = new TalonFX(RobotMap.flw);
  TalonFX brw = new TalonFX(RobotMap.brw);
  TalonFX blw = new TalonFX(RobotMap.blw);


  //frw.setIdleMode(IdleMode.kBrake);

  public Drivetrain() {
    super();
    try {
      /* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
      /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
      /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
      ahrs = new AHRS(SPI.Port.kMXP); 
    } catch (RuntimeException ex ) {
        // TODO: Properly report this error to the driverstation, how should we do this in 2022?
        //DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
    }
  }

  /**
   * Runs every time the scheduler runs
   */
  @Override
  public void periodic() {
    updateYaw();
  }

  /**
   * Updates our 'total' yaw value using our current deg/s rotation.
   * This can be inaccurate due to the fact that our rotation rate will only
   * be sampled once every 20ms (when the scheduler runs)
   */
  private void updateYaw() {
    this.yaw += ahrs.getRawGyroY() / 60;
    yaw = yaw % 360;
  }

  public void setEachWheel(double fr,double fl,double br,double bl){
    frw.set(po,fr);
    flw.set(po,-fl);
    brw.set(po,br);
    blw.set(po,-bl);
  }

  public void setWheels(double[] speeds){
    frw.set(po,speeds[0]);
    flw.set(po,-speeds[1]);
    brw.set(po,speeds[2]);
    blw.set(po,-speeds[3]);
  }

  public void setWheel(TalonFX wheel, double speed){
    wheel.set(po,speed);
  }

  public double[] mecanumSpeeds(double x,double y, double a){
    double[] motion = {x,y,a};
    int[][] wheelBool = {{-1,1,-1},{1,1,1},{1,1,-1},{-1,1,1}};
    double[] wheelSpeeds = {0,0,0,0};
    for(int w = 0;w < 4;w ++){
      for(int m = 0;m < 3;m ++){
        wheelSpeeds[w]+=wheelBool[w][m]*motion[m];
      }
    }
    return wheelSpeeds;
  }

  public void mecanumMove(double x,double y,double a){
    setWheels(mecanumSpeeds(x,y,a));
  }

  public void setLock(){
    lockAngle = yaw;
  }

  public double lock(double rate){
    return (lockAngle-yaw)*rate;
  }

  public void translateWhileLocked(double intendedHeading, double rate) {
    double angle = Math.PI * intendedHeading / 180;
    mecanumMove(rate * Math.sin(angle), rate * Math.cos(angle), lock(-0.01));
  }
}