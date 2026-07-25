package com.example.fitness.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.fitness.entity.BodyData;
import com.example.fitness.mapper.BodyDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BodyDataService {

    @Autowired
    private BodyDataMapper bodyDataMapper;

    public BodyData createBodyData(BodyData data) {
        bodyDataMapper.insert(data);
        return data;
    }

    public BodyData updateBodyData(Long id, BodyData data) {
        data.setId(id);
        bodyDataMapper.updateById(data);
        return data;
    }

    public void deleteBodyData(Long id) {
        bodyDataMapper.deleteById(id);
    }

    public BodyData getBodyDataById(Long id) {
        return bodyDataMapper.selectById(id);
    }

    public List<BodyData> getBodyDataList(Long userId) {
        LambdaQueryWrapper<BodyData> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BodyData::getUserId, userId)
                    .orderByDesc(BodyData::getRecordDate);
        return bodyDataMapper.selectList(queryWrapper);
    }

    public Map<String, Object> getWeightTrend(Long userId) {
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minusMonths(3);
        
        LambdaQueryWrapper<BodyData> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BodyData::getUserId, userId)
                    .ge(BodyData::getRecordDate, startDate)
                    .orderByAsc(BodyData::getRecordDate);
        
        List<BodyData> records = bodyDataMapper.selectList(queryWrapper);
        
        Map<String, Object> trend = new HashMap<>();
        trend.put("records", records);
        
        if (!records.isEmpty()) {
            Double firstWeight = records.get(0).getWeight().doubleValue();
            Double latestWeight = records.get(records.size() - 1).getWeight().doubleValue();
            trend.put("weightChange", latestWeight - firstWeight);
        }
        
        return trend;
    }
}
