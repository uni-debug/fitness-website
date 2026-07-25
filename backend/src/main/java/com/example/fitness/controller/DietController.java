package com.example.fitness.controller;

import com.example.fitness.common.Result;
import com.example.fitness.entity.DietRecord;
import com.example.fitness.service.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/diet")
public class DietController {

    @Autowired
    private DietService dietService;

    @PostMapping
    public Result<DietRecord> createDiet(@RequestBody DietRecord record, @RequestAttribute("userId") Long userId) {
        record.setUserId(userId);
        DietRecord created = dietService.createDiet(record);
        return Result.success(created);
    }

    @PutMapping("/{id}")
    public Result<DietRecord> updateDiet(@PathVariable Long id, @RequestBody DietRecord record, @RequestAttribute("userId") Long userId) {
        record.setUserId(userId);
        DietRecord updated = dietService.updateDiet(id, record);
        return Result.success(updated);
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteDiet(@PathVariable Long id) {
        dietService.deleteDiet(id);
        return Result.success(null);
    }

    @GetMapping("/{id}")
    public Result<DietRecord> getDietById(@PathVariable Long id) {
        DietRecord record = dietService.getDietById(id);
        return Result.success(record);
    }

    @GetMapping
    public Result<List<DietRecord>> getDietList(@RequestAttribute("userId") Long userId,
                                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<DietRecord> records = dietService.getDietList(userId, startDate, endDate);
        return Result.success(records);
    }

    @GetMapping("/daily")
    public Result<Map<String, Object>> getDailyStats(@RequestAttribute("userId") Long userId,
                                                      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Map<String, Object> stats = dietService.getDailyStats(userId, date);
        return Result.success(stats);
    }
}
