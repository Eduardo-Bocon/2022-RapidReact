// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveRobot extends CommandBase {

  private final DriveTrain driveTrain;
  private final XboxController xboxController;

  public DriveRobot(DriveTrain driveTrain, XboxController xboxController) {
    this.driveTrain = driveTrain;
    this.xboxController = xboxController;
    addRequirements(driveTrain);
  }

  @Override
  public void initialize() {
    driveTrain.invert();
  }

  @Override
  public void execute() {
    double moveLeft = xboxController.getRawAxis(XboxController.Axis.kLeftY.value);
    double turnLeft = xboxController.getRawAxis(XboxController.Axis.kLeftX.value);
    double moveRight = xboxController.getRawAxis(XboxController.Axis.kRightY.value);
    double turnRight = xboxController.getRawAxis(XboxController.Axis.kRightX.value);

    double moveSpeed = Math.abs(moveLeft) > Math.abs(moveRight) ? moveLeft : moveRight;
    double rotateSpeed = Math.abs(turnLeft) > Math.abs(turnRight) ? turnLeft : turnRight;

    moveSpeed*=0.7;
    rotateSpeed*=0.7;

    //A inversão usa o moveSpeed sem sinal negativo porque o robô já está invertido por padrão
    if(driveTrain.isInverted()){
      driveTrain.arcadeDrive(moveSpeed, rotateSpeed);
    } else {
      driveTrain.arcadeDrive(-moveSpeed, rotateSpeed);
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
