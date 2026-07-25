package com.example.fitness.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.fitness.entity.Course;
import com.example.fitness.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;

    public Course createCourse(Course course) {
        courseMapper.insert(course);
        return course;
    }

    public Course updateCourse(Long id, Course course) {
        course.setId(id);
        courseMapper.updateById(course);
        return course;
    }

    public void deleteCourse(Long id) {
        courseMapper.deleteById(id);
    }

    public Course getCourseById(Long id) {
        return courseMapper.selectById(id);
    }

    public List<Course> getCourseList(Long userId) {
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.and(w -> w.eq(Course::getUserId, userId).or().eq(Course::getIsPreset, 1))
                    .orderByDesc(Course::getIsPreset)
                    .orderByDesc(Course::getCreatedAt);
        return courseMapper.selectList(queryWrapper);
    }

    public List<Course> getPresetCourses() {
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Course::getIsPreset, 1)
                    .orderByDesc(Course::getCreatedAt);
        return courseMapper.selectList(queryWrapper);
    }
}
