package com.example.fitness.controller;

import com.example.fitness.common.Result;
import com.example.fitness.entity.ExerciseLibrary;
import com.example.fitness.service.ExerciseLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercise")
public class ExerciseLibraryController {

    @Autowired
    private ExerciseLibraryService exerciseLibraryService;

    @GetMapping("/search")
    public Result<List<ExerciseLibrary>> searchExercises(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String equipment,
            @RequestParam(required = false) String target) {
        List<ExerciseLibrary> exercises = exerciseLibraryService.searchExercises(keyword, category, equipment, target);
        return Result.success(exercises);
    }

    @GetMapping("/{id}")
    public Result<ExerciseLibrary> getExerciseById(@PathVariable Long id) {
        ExerciseLibrary exercise = exerciseLibraryService.getExerciseById(id);
        return Result.success(exercise);
    }

    @GetMapping("/categories")
    public Result<List<String>> getCategories() {
        List<ExerciseLibrary> result = exerciseLibraryService.getAllCategories();
        List<String> categories = result.stream().map(ExerciseLibrary::getCategory).toList();
        return Result.success(categories);
    }

    @GetMapping("/equipments")
    public Result<List<String>> getEquipments() {
        List<ExerciseLibrary> result = exerciseLibraryService.getAllEquipments();
        List<String> equipments = result.stream().map(ExerciseLibrary::getEquipment).toList();
        return Result.success(equipments);
    }

    @GetMapping("/targets")
    public Result<List<String>> getTargets() {
        List<ExerciseLibrary> result = exerciseLibraryService.getAllTargets();
        List<String> targets = result.stream().map(ExerciseLibrary::getTarget).toList();
        return Result.success(targets);
    }
}
