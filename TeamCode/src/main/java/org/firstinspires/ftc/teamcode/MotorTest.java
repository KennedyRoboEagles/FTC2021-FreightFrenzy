package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Autonomous
public class MotorTest extends OpMode {

    private DcMotorEx motor;

    @Override
    public void init() {
        motor = hardwareMap.get(DcMotorEx.class, "motor");

        // Setup motor
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void loop() {
        motor.setPower(0.5);
        motor.setTargetPosition(28*40); // 1120 counts
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("encoder", motor.getCurrentPosition());
        telemetry.update();
    }
}