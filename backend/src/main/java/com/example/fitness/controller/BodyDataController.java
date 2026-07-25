package com.example.fitness.controller;

import com.example.fitness.common.Result;
import com.example.fitness.entity.BodyData;
import com.example.fitness.service.BodyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/body-data")
public class BodyDataController {

    @Autowired
    private BodyDataService bodyDataService;

    @PostMapping
    public Result<BodyData> createBodyData(@RequestBody BodyData data, @RequestAttribute("userId") Long userId) {
        data.setUserId(userId);
        BodyData created = bodyDataService.createBodyData(data);
        return Result.success(created);
    }

    @PutMapping("/{id}")
    public Result<BodyData> updateBodyData(@PathVariable Long id, @RequestBody BodyData data, @RequestAttribute("userId") Long userId) {
        data.setUserId(userId);
        BodyData updated = bodyDataService.updateBodyData(id, data);
        return Result.success(updated);
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteBodyData(@PathVariable Long id) {
        bodyDataService.deleteBodyData(id);
        return Result.success(null);
    }

    @GetMapping("/{id}")
    public Result<BodyData> getBodyDataById(@PathVariable Long id) {
        BodyData data = bodyDataService.getBodyDataById(id);
        return Result.success(data);
    }

    @GetMapping
    public Result<List<BodyData>> getBodyDataList(@RequestAttribute("userId") Long userId) {
        List<BodyData> records = bodyDataService.getBodyDataList(userId);
        return Result.success(records);
    }

    @GetMapping("/trend")
    public Result<Map<String, Object>> getWeightTrend(@RequestAttribute("userId") Long userId) {
        Map<String, Object> trend = bodyDataService.getWeightTrend(userId);
        return Result.success(trend);
    }
}
