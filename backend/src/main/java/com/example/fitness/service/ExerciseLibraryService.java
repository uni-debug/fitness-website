package com.example.fitness.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.fitness.entity.ExerciseLibrary;
import com.example.fitness.mapper.ExerciseLibraryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseLibraryService {

    @Autowired
    private ExerciseLibraryMapper exerciseLibraryMapper;

    public List<ExerciseLibrary> searchExercises(String keyword, String category, String equipment, String target) {
        LambdaQueryWrapper<ExerciseLibrary> queryWrapper = new LambdaQueryWrapper<>();
        
        if (keyword != null && !keyword.isEmpty()) {
            queryWrapper.like(ExerciseLibrary::getName, keyword);
        }
        
        if (category != null && !category.isEmpty()) {
            queryWrapper.eq(ExerciseLibrary::getCategory, category);
        }
        
        if (equipment != null && !equipment.isEmpty()) {
            queryWrapper.eq(ExerciseLibrary::getEquipment, equipment);
        }
        
        if (target != null && !target.isEmpty()) {
            queryWrapper.eq(ExerciseLibrary::getTarget, target);
        }
        
        queryWrapper.orderByAsc(ExerciseLibrary::getExerciseId);
        return exerciseLibraryMapper.selectList(queryWrapper);
    }

    public ExerciseLibrary getExerciseById(Long id) {
        return exerciseLibraryMapper.selectById(id);
    }

    public ExerciseLibrary getExerciseByExerciseId(String exerciseId) {
        LambdaQueryWrapper<ExerciseLibrary> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ExerciseLibrary::getExerciseId, exerciseId);
        return exerciseLibraryMapper.selectOne(queryWrapper);
    }

    public List<ExerciseLibrary> getAllCategories() {
        LambdaQueryWrapper<ExerciseLibrary> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(ExerciseLibrary::getCategory);
        return exerciseLibraryMapper.selectList(queryWrapper);
    }

    public List<ExerciseLibrary> getAllEquipments() {
        LambdaQueryWrapper<ExerciseLibrary> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(ExerciseLibrary::getEquipment);
        return exerciseLibraryMapper.selectList(queryWrapper);
    }

    public List<ExerciseLibrary> getAllTargets() {
        LambdaQueryWrapper<ExerciseLibrary> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(ExerciseLibrary::getTarget);
        return exerciseLibraryMapper.selectList(queryWrapper);
    }
}
