package com.example.fitness.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fitness.entity.WorkoutRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkoutRecordMapper extends BaseMapper<WorkoutRecord> {
}
