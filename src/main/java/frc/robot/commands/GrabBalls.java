// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class GrabBalls extends CommandBase {

  private final Intake intake;
  private final double speed;

  public GrabBalls(Intake intake, double speed) {
    this.intake = intake;
    this.speed = speed;
    addRequirements(intake);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    intake.grabBalls(speed);
  }

  @Override
  public void end(boolean interrupted) {
    intake.stopIntake();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}