package com.example.fitness.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.fitness.entity.Appointment;
import com.example.fitness.mapper.AppointmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentMapper appointmentMapper;

    public Appointment createAppointment(Appointment appointment) {
        appointmentMapper.insert(appointment);
        return appointment;
    }

    public Appointment updateAppointment(Long id, Appointment appointment) {
        appointment.setId(id);
        appointmentMapper.updateById(appointment);
        return appointment;
    }

    public void deleteAppointment(Long id) {
        appointmentMapper.deleteById(id);
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentMapper.selectById(id);
    }

    public List<Appointment> getAppointmentList(Long userId, LocalDate date) {
        LambdaQueryWrapper<Appointment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Appointment::getUserId, userId);
        
        if (date != null) {
            queryWrapper.eq(Appointment::getAppointmentDate, date);
        }
        
        queryWrapper.orderByAsc(Appointment::getAppointmentDate)
                    .orderByAsc(Appointment::getStartTime);
        return appointmentMapper.selectList(queryWrapper);
    }
}
