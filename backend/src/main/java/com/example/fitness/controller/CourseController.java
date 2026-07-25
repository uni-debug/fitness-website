package com.example.fitness.controller;

import com.example.fitness.common.Result;
import com.example.fitness.entity.Course;
import com.example.fitness.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public Result<Course> createCourse(@RequestBody Course course, @RequestAttribute("userId") Long userId) {
        course.setUserId(userId);
        course.setIsPreset(0);
        Course created = courseService.createCourse(course);
        return Result.success(created);
    }

    @PutMapping("/{id}")
    public Result<Course> updateCourse(@PathVariable Long id, @RequestBody Course course, @RequestAttribute("userId") Long userId) {
        course.setUserId(userId);
        Course updated = courseService.updateCourse(id, course);
        return Result.success(updated);
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return Result.success(null);
    }

    @GetMapping("/{id}")
    public Result<Course> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        return Result.success(course);
    }

    @GetMapping
    public Result<List<Course>> getCourseList(@RequestAttribute("userId") Long userId) {
        List<Course> courses = courseService.getCourseList(userId);
        return Result.success(courses);
    }

    @GetMapping("/preset")
    public Result<List<Course>> getPresetCourses() {
        List<Course> courses = courseService.getPresetCourses();
        return Result.success(courses);
    }
}
