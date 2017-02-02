package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	String autoSelected;
	SendableChooser<String> chooser;
	AutoFunctions functions;
	AutoModes auto;
	VisionTargeting vision;
	DriverStation d;
	DriveTrain drive;
	FlyWheel flywheel;
	GroundCollector collector;
	DriverVision gearCamera;
	DriverVision highGoalCamera;
	Controller driverJoy;
	Controller operatorJoy;
	double rotateToAngleRate;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		d = DriverStation.getInstance();
		drive = new DriveTrain();
		flywheel = new FlyWheel();
		collector = new GroundCollector();
		gearCamera = new DriverVision("gearCamera", 0);
		highGoalCamera = new DriverVision("highGoalCamera", 1);
		driverJoy = new Controller(0);
		operatorJoy = new Controller(1);

		chooser = new SendableChooser<String>();
		chooser.addDefault("Do Nothing", "DoNothing");
		chooser.addObject("Cross Baseline", "CrossBaseline");
		chooser.addObject("Middle Gear", "MiddleGear");
		chooser.addObject("Side Gear, Red or Blue", "SideGear");
		// chooser.addObject("Red: Right Gear", "RedRight");
		// chooser.addObject("Blue: Right Gear", "BlueRight");
		// chooser.addObject("Red: Left Gear", "RedLeft");
		// chooser.addObject("Blue: Left Gear", "BlueLeft");
		chooser.addObject("Red: Middle Gear to Hopper", "RedMiddleHopper");
		chooser.addObject("Blue: Middle Gear to Hopper", "BlueMiddleHopper");
		chooser.addObject("Red: Right Gear to Hopper", "RedRightHopper");
		chooser.addObject("Blue: Right Gear to Hopper", "BlueRightHopper");
		chooser.addObject("Red: Left Gear to Hopper", "RedLeftHopper");
		chooser.addObject("Blue: Left Gear to Hopper", "BlueLeftHopper");
		chooser.addObject("Red: Middle Gear to Boiler", "RedMiddleBoiler");
		chooser.addObject("Blue: Middle Gear to Boiler", "BlueMiddleBoiler");
		chooser.addObject("Red: Right Gear to Boiler", "RedRightBoiler");
		chooser.addObject("Blue: Right Gear to Boiler", "BlueRightBoiler");
		chooser.addObject("Red: Left Gear to Boiler", "RedLeftBoiler");
		chooser.addObject("Blue: Left Gear to Boiler", "BlueLeftBoiler");
		chooser.addObject("Red: Hopper to Boiler", "RedHopperBoiler");
		chooser.addObject("Blue: Hopper to Boiler", "BlueHopperBoiler");
		chooser.addObject("Red: Start at Boiler", "RedStartBoiler");
		chooser.addObject("Blue: Start at Boiler", "BlueStartBoiler");
		SmartDashboard.putData("Auto choices", chooser);
		// if(drive.rightEncoder()){
		// System.out.println("Right Encoder Working");
		// }
		if (drive.leftEncoder()) {
			System.out.println("Left Encoder Working");
		}

	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	public void autonomousInit() {
		autoSelected = (String) chooser.getSelected();
		autoSelected = SmartDashboard.getString("Auto Selector", "Do Nothing");
		vision = new VisionTargeting("10.0.20.9");
		functions = new AutoFunctions(drive, flywheel, collector, vision);
		auto = new AutoModes(functions);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case "DoNothing":
			auto.doNothing();
			break;
		case "CrossBaseline":
			auto.crossBaseline();
			break;
		case "MiddleGear":
			auto.middlePeg();
			break;
		case "SideGear":
			auto.sidePeg();
			break;
		// case "RedRight":
		// auto.rightPegRed();
		// break;
		// case "BlueRight":
		// auto.rightPegBlue();
		// break;
		// case "RedLeft":
		// auto.leftPegRed();
		// break;
		// case "BlueLeft":
		// auto.leftPegBlue();
		// break;
		case "RedMiddleHopper":
			auto.middleHopperRed();
			break;
		case "BlueMiddleHopper":
			auto.middleHopperBlue();
			break;
		case "RedRightHopper":
			auto.rightHopperRed();
			break;
		case "BlueRightHopper":
			auto.rightHopperBlue();
			break;
		case "RedLeftHopper":
			auto.leftHopperRed();
			break;
		case "BlueLeftHopper":
			auto.leftHopperBlue();
			break;
		case "RedMiddleBoiler":
			auto.middleBoilerRed();
			break;
		case "BlueMiddleBoiler":
			auto.middleBoilerBlue();
			break;
		case "RedRightBoiler":
			auto.rightBoilerRed();
			break;
		case "BlueRightBoiler":
			auto.rightBoilerBlue();
			break;
		case "RedLeftBoiler":
			auto.leftBoilerRed();
			break;
		case "BlueLeftBoiler":
			auto.leftBoilerBlue();
			break;
		case "RedHopperBoiler":
			auto.hopperBoilerRed();
			break;
		case "BlueHopperBoiler":
			auto.hopperBoilerBlue();
			break;
		case "RedStartBoiler":
			auto.startBoilerRed();
			break;
		case "BlueStartBoiler":
			auto.startBoilerBlue();
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		// gearCamera.startUSBCamera();
		// highGoalCamera.startUSBCamera();
		// drive.masterLeft.set(.5);
		double speedStraight = driverJoy.getLeftYAxis();
		double speedLeft = driverJoy.getLeftTriggerAxis();
		double speedRight = driverJoy.getRightTriggerAxis();
		if (speedStraight != 0 || speedLeft > 0 || speedRight > 0) {
			drive.drive(speedStraight, speedRight, speedLeft);
		}
		if (driverJoy.getButtonLeftBumper()) {
			drive.shiftHigh();
		}
		if (driverJoy.getButtonRightBumper()) {
			drive.shiftLow();
		}
		if (operatorJoy.getButtonA()) {
			collector.intake();
		}
		if (operatorJoy.getButtonB()) {
			collector.outtake();
		}
		if (operatorJoy.getButtonX()) {
			hopper.intake();
		}
		if (operatorJoy.getButtonY()) {
			hopper.outtake();
		}
		if (operatorJoy.getButtonRightBumper()) {
			gear.in();
		}
		if (operatorJoy.getButtonLeftBumper()) {
			gear.out();
		}
		if (operatorJoy.getButtonDUp()) {
			flywheel.shootWithEncoders();
		}
		if (operatorJoy.getButtonBack()) {
			flywheel.stop();
			hopper.stop();
			gear.stop();
			collector.stop();
		}
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}
}