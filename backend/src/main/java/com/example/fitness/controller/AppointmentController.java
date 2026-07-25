package com.example.fitness.controller;

import com.example.fitness.common.Result;
import com.example.fitness.entity.Appointment;
import com.example.fitness.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public Result<Appointment> createAppointment(@RequestBody Appointment appointment, @RequestAttribute("userId") Long userId) {
        appointment.setUserId(userId);
        Appointment created = appointmentService.createAppointment(appointment);
        return Result.success(created);
    }

    @PutMapping("/{id}")
    public Result<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment, @RequestAttribute("userId") Long userId) {
        appointment.setUserId(userId);
        Appointment updated = appointmentService.updateAppointment(id, appointment);
        return Result.success(updated);
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return Result.success(null);
    }

    @GetMapping("/{id}")
    public Result<Appointment> getAppointmentById(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        return Result.success(appointment);
    }

    @GetMapping
    public Result<List<Appointment>> getAppointmentList(@RequestAttribute("userId") Long userId,
                                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Appointment> appointments = appointmentService.getAppointmentList(userId, date);
        return Result.success(appointments);
    }
}
