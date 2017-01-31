package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	Constants constants;
	Controller driverJoy = new Controller(Constants.DRIVER_JOYSTICK_PORT);
	Controller operatorJoy = new Controller(Constants.OPERATOR_JOYSTICK_PORT);
	DriveTrain drive = new DriveTrain(constants);

	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
	}

	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	@Override
	public void teleopPeriodic() {
		if (driverJoy.getLeftYAxis() != 0) {
			drive.drive(driverJoy.getLeftYAxis());
		}
		if (driverJoy.getRightBumpAxis() > 0) {
			drive.turnRight(driverJoy.getRightBumpAxis());
		}
		if (driverJoy.getLeftBumpAxis() > 0) {
			drive.turnLeft(driverJoy.getLeftBumpAxis());
		}
		if (driverJoy.getButtonLTrig()) {
			drive.shiftHigh();
		}
		if (driverJoy.getButtonRTrig()) {
			drive.shiftLow();
		}
		if (operatorJoy.getButtonA()) {
			collector.intake();
		}
		if (operatorJoy.getButtonB()) {
			collector.stopCollector();
		}
	}

	@Override
	public void testPeriodic() {
	}
}