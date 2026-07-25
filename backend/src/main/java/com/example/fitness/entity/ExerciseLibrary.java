package com.example.fitness.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

@TableName("exercise_library")
public class ExerciseLibrary {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    @TableField("exercise_id")
    private String exerciseId;
    
    private String name;
    
    private String category;
    
    @TableField("body_part")
    private String bodyPart;
    
    private String equipment;
    
    @TableField("muscle_group")
    private String muscleGroup;
    
    private String target;
    
    @TableField("secondary_muscles")
    private String secondaryMuscles;
    
    @TableField("instructions_en")
    private String instructionsEn;
    
    @TableField("instructions_zh")
    private String instructionsZh;
    
    @TableField("instruction_steps_en")
    private String instructionStepsEn;
    
    @TableField("instruction_steps_zh")
    private String instructionStepsZh;
    
    private String image;
    
    @TableField("gif_url")
    private String gifUrl;
    
    @TableField("media_id")
    private String mediaId;
    
    private String attribution;
    
    @TableField("created_at")
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getSecondaryMuscles() {
        return secondaryMuscles;
    }

    public void setSecondaryMuscles(String secondaryMuscles) {
        this.secondaryMuscles = secondaryMuscles;
    }

    public String getInstructionsEn() {
        return instructionsEn;
    }

    public void setInstructionsEn(String instructionsEn) {
        this.instructionsEn = instructionsEn;
    }

    public String getInstructionsZh() {
        return instructionsZh;
    }

    public void setInstructionsZh(String instructionsZh) {
        this.instructionsZh = instructionsZh;
    }

    public String getInstructionStepsEn() {
        return instructionStepsEn;
    }

    public void setInstructionStepsEn(String instructionStepsEn) {
        this.instructionStepsEn = instructionStepsEn;
    }

    public String getInstructionStepsZh() {
        return instructionStepsZh;
    }

    public void setInstructionStepsZh(String instructionStepsZh) {
        this.instructionStepsZh = instructionStepsZh;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGifUrl() {
        return gifUrl;
    }

    public void setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
