package com.example.fitness.controller;

import com.example.fitness.common.Result;
import com.example.fitness.entity.WorkoutRecord;
import com.example.fitness.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/workout")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @PostMapping
    public Result<WorkoutRecord> createWorkout(@RequestBody WorkoutRecord record, @RequestAttribute("userId") Long userId) {
        record.setUserId(userId);
        WorkoutRecord created = workoutService.createWorkout(record);
        return Result.success(created);
    }

    @PutMapping("/{id}")
    public Result<WorkoutRecord> updateWorkout(@PathVariable Long id, @RequestBody WorkoutRecord record, @RequestAttribute("userId") Long userId) {
        record.setUserId(userId);
        WorkoutRecord updated = workoutService.updateWorkout(id, record);
        return Result.success(updated);
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteWorkout(@PathVariable Long id) {
        workoutService.deleteWorkout(id);
        return Result.success(null);
    }

    @GetMapping("/{id}")
    public Result<WorkoutRecord> getWorkoutById(@PathVariable Long id) {
        WorkoutRecord record = workoutService.getWorkoutById(id);
        return Result.success(record);
    }

    @GetMapping
    public Result<List<WorkoutRecord>> getWorkoutList(@RequestAttribute("userId") Long userId,
                                                       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<WorkoutRecord> records = workoutService.getWorkoutList(userId, startDate, endDate);
        return Result.success(records);
    }

    @GetMapping("/weekly")
    public Result<Map<String, Object>> getWeeklyStats(@RequestAttribute("userId") Long userId) {
        Map<String, Object> stats = workoutService.getWeeklyStats(userId);
        return Result.success(stats);
    }
}
