package com.example.fitness.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.fitness.entity.DietRecord;
import com.example.fitness.mapper.DietRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DietService {

    @Autowired
    private DietRecordMapper dietRecordMapper;

    public DietRecord createDiet(DietRecord record) {
        dietRecordMapper.insert(record);
        return record;
    }

    public DietRecord updateDiet(Long id, DietRecord record) {
        record.setId(id);
        dietRecordMapper.updateById(record);
        return record;
    }

    public void deleteDiet(Long id) {
        dietRecordMapper.deleteById(id);
    }

    public DietRecord getDietById(Long id) {
        return dietRecordMapper.selectById(id);
    }

    public List<DietRecord> getDietList(Long userId, LocalDate startDate, LocalDate endDate) {
        LambdaQueryWrapper<DietRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DietRecord::getUserId, userId);
        
        if (startDate != null) {
            queryWrapper.ge(DietRecord::getDietDate, startDate);
        }
        if (endDate != null) {
            queryWrapper.le(DietRecord::getDietDate, endDate);
        }
        
        queryWrapper.orderByDesc(DietRecord::getDietDate);
        return dietRecordMapper.selectList(queryWrapper);
    }

    public Map<String, Object> getDailyStats(Long userId, LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        
        LambdaQueryWrapper<DietRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DietRecord::getUserId, userId)
                    .eq(DietRecord::getDietDate, date);
        
        List<DietRecord> records = dietRecordMapper.selectList(queryWrapper);
        
        Map<String, Object> stats = new HashMap<>();
        
        BigDecimal totalCalories = records.stream()
                .map(r -> r.getCalories() != null ? r.getCalories() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal totalProtein = records.stream()
                .map(r -> r.getProtein() != null ? r.getProtein() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal totalCarbs = records.stream()
                .map(r -> r.getCarbs() != null ? r.getCarbs() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal totalFat = records.stream()
                .map(r -> r.getFat() != null ? r.getFat() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        stats.put("totalCalories", totalCalories);
        stats.put("totalProtein", totalProtein);
        stats.put("totalCarbs", totalCarbs);
        stats.put("totalFat", totalFat);
        stats.put("records", records);
        
        return stats;
    }
}
