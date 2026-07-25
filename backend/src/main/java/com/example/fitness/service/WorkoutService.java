package com.example.fitness.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.fitness.entity.WorkoutRecord;
import com.example.fitness.mapper.WorkoutRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRecordMapper workoutRecordMapper;

    public WorkoutRecord createWorkout(WorkoutRecord record) {
        workoutRecordMapper.insert(record);
        return record;
    }

    public WorkoutRecord updateWorkout(Long id, WorkoutRecord record) {
        record.setId(id);
        workoutRecordMapper.updateById(record);
        return record;
    }

    public void deleteWorkout(Long id) {
        workoutRecordMapper.deleteById(id);
    }

    public WorkoutRecord getWorkoutById(Long id) {
        return workoutRecordMapper.selectById(id);
    }

    public List<WorkoutRecord> getWorkoutList(Long userId, LocalDate startDate, LocalDate endDate) {
        LambdaQueryWrapper<WorkoutRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WorkoutRecord::getUserId, userId);
        
        if (startDate != null) {
            queryWrapper.ge(WorkoutRecord::getWorkoutDate, startDate);
        }
        if (endDate != null) {
            queryWrapper.le(WorkoutRecord::getWorkoutDate, endDate);
        }
        
        queryWrapper.orderByDesc(WorkoutRecord::getWorkoutDate);
        return workoutRecordMapper.selectList(queryWrapper);
    }

    public Map<String, Object> getWeeklyStats(Long userId) {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1);
        
        LambdaQueryWrapper<WorkoutRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WorkoutRecord::getUserId, userId)
                    .ge(WorkoutRecord::getWorkoutDate, startOfWeek)
                    .le(WorkoutRecord::getWorkoutDate, today);
        
        List<WorkoutRecord> records = workoutRecordMapper.selectList(queryWrapper);
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalWorkouts", records.size());
        
        int totalSets = records.stream().mapToInt(r -> r.getSets()).sum();
        int totalReps = records.stream().mapToInt(r -> r.getSets() * r.getReps()).sum();
        int totalDuration = records.stream().mapToInt(r -> r.getDuration() != null ? r.getDuration() : 0).sum();
        
        stats.put("totalSets", totalSets);
        stats.put("totalReps", totalReps);
        stats.put("totalDuration", totalDuration);
        stats.put("records", records);
        
        return stats;
    }
}
