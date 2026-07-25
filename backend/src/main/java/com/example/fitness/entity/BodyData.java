package com.example.fitness.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@TableName("body_data")
public class BodyData {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    @TableField("user_id")
    private Long userId;
    
    private BigDecimal weight;
    
    @TableField("body_fat_rate")
    private BigDecimal bodyFatRate;
    
    @TableField("muscle_mass")
    private BigDecimal muscleMass;
    
    @TableField("chest_circumference")
    private BigDecimal chestCircumference;
    
    @TableField("waist_circumference")
    private BigDecimal waistCircumference;
    
    @TableField("hip_circumference")
    private BigDecimal hipCircumference;
    
    @TableField("photo_before_url")
    private String photoBeforeUrl;
    
    @TableField("photo_after_url")
    private String photoAfterUrl;
    
    @TableField("record_date")
    private LocalDate recordDate;
    
    @TableField("created_at")
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getBodyFatRate() {
        return bodyFatRate;
    }

    public void setBodyFatRate(BigDecimal bodyFatRate) {
        this.bodyFatRate = bodyFatRate;
    }

    public BigDecimal getMuscleMass() {
        return muscleMass;
    }

    public void setMuscleMass(BigDecimal muscleMass) {
        this.muscleMass = muscleMass;
    }

    public BigDecimal getChestCircumference() {
        return chestCircumference;
    }

    public void setChestCircumference(BigDecimal chestCircumference) {
        this.chestCircumference = chestCircumference;
    }

    public BigDecimal getWaistCircumference() {
        return waistCircumference;
    }

    public void setWaistCircumference(BigDecimal waistCircumference) {
        this.waistCircumference = waistCircumference;
    }

    public BigDecimal getHipCircumference() {
        return hipCircumference;
    }

    public void setHipCircumference(BigDecimal hipCircumference) {
        this.hipCircumference = hipCircumference;
    }

    public String getPhotoBeforeUrl() {
        return photoBeforeUrl;
    }

    public void setPhotoBeforeUrl(String photoBeforeUrl) {
        this.photoBeforeUrl = photoBeforeUrl;
    }

    public String getPhotoAfterUrl() {
        return photoAfterUrl;
    }

    public void setPhotoAfterUrl(String photoAfterUrl) {
        this.photoAfterUrl = photoAfterUrl;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
