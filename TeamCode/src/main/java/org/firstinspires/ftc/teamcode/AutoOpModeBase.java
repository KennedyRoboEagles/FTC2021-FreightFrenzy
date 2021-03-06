package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.TiltIntakeRampUpCommand;
import org.firstinspires.ftc.teamcode.commands.TurnInPlace;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.DuckSpinner;
import org.firstinspires.ftc.teamcode.subsystems.IntakeRamp;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSpinner;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;

// @Autonomous(name = "Auto Storage")

public abstract class AutoOpModeBase extends CommandOpMode {


    private DuckSpinner duckSpinner;
    private Drive drive;
    private Outtake outtake;
    private IntakeRamp intakeramp;
    private IntakeSpinner intakeSpinner;
    private Arm arm;
    private final double forwardOne;
    private final double forwardTwo;
    private final double angle;

    protected AutoOpModeBase(double forwardOne, double angle, double forwardTwo) {
        this.angle = angle;
        this.forwardOne = forwardOne;
        this.forwardTwo = forwardTwo;
    }

    @Override
    public void initialize(){
        drive = new Drive(hardwareMap,telemetry);
        duckSpinner = new DuckSpinner(hardwareMap);
        outtake = new Outtake(hardwareMap);
        arm = new Arm(hardwareMap, telemetry);
        intakeramp = new IntakeRamp(hardwareMap);
        intakeSpinner = new IntakeSpinner(hardwareMap);

        intakeramp.setDefaultCommand(
                new TiltIntakeRampUpCommand(intakeramp)
        );

        outtake.stow();
        sleep(1500);
        intakeramp.down();
        intakeSpinner.stow();

        schedule(

                new SequentialCommandGroup(
                        new DriveForwardCommand(telemetry, drive, forwardOne, 0.5),
                        new InstantCommand(() -> intakeSpinner.stop(), intakeSpinner),
                        new TurnInPlace(drive, angle, telemetry),
                        new DriveForwardCommand(telemetry, drive, forwardTwo, 0.5)
                )
        );
    }
}
