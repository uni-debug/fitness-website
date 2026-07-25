-- 创建数据库
CREATE DATABASE IF NOT EXISTS fitness_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE fitness_db;

-- 用户表
CREATE TABLE IF NOT EXISTS user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(50),
    avatar VARCHAR(255),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 动作库表（存储1324个健身动作）
CREATE TABLE IF NOT EXISTS exercise_library (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    exercise_id VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(200) NOT NULL,
    category VARCHAR(50),
    body_part VARCHAR(50),
    equipment VARCHAR(100),
    muscle_group VARCHAR(100),
    target VARCHAR(100),
    secondary_muscles TEXT,
    instructions_en TEXT,
    instructions_zh TEXT,
    instruction_steps_en TEXT,
    instruction_steps_zh TEXT,
    image VARCHAR(255),
    gif_url VARCHAR(255),
    media_id VARCHAR(50),
    attribution VARCHAR(255),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_category (category),
    INDEX idx_body_part (body_part),
    INDEX idx_equipment (equipment),
    INDEX idx_target (target)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 训练记录表
CREATE TABLE IF NOT EXISTS workout_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    exercise_name VARCHAR(100) NOT NULL,
    sets INT NOT NULL,
    reps INT NOT NULL,
    weight DECIMAL(10,2),
    muscle_group VARCHAR(50),
    duration INT,
    notes TEXT,
    workout_date DATE NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    INDEX idx_user_date (user_id, workout_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 饮食记录表
CREATE TABLE IF NOT EXISTS diet_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    meal_type VARCHAR(20) NOT NULL,
    food_name VARCHAR(100) NOT NULL,
    quantity VARCHAR(50),
    calories DECIMAL(10,2),
    protein DECIMAL(10,2),
    carbs DECIMAL(10,2),
    fat DECIMAL(10,2),
    photo_url VARCHAR(255),
    notes TEXT,
    diet_date DATE NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    INDEX idx_user_date (user_id, diet_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 身体数据表
CREATE TABLE IF NOT EXISTS body_data (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    weight DECIMAL(10,2) NOT NULL,
    body_fat_rate DECIMAL(5,2),
    muscle_mass DECIMAL(10,2),
    chest_circumference DECIMAL(10,2),
    waist_circumference DECIMAL(10,2),
    hip_circumference DECIMAL(10,2),
    photo_before_url VARCHAR(255),
    photo_after_url VARCHAR(255),
    record_date DATE NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    INDEX idx_user_date (user_id, record_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 课程表
CREATE TABLE IF NOT EXISTS course (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL DEFAULT 0,
    course_name VARCHAR(100) NOT NULL,
    description TEXT,
    duration INT,
    level VARCHAR(20),
    category VARCHAR(50),
    is_preset TINYINT NOT NULL DEFAULT 0,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_user_is_preset (user_id, is_preset)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 预约表
CREATE TABLE IF NOT EXISTS appointment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    appointment_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'pending',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE,
    INDEX idx_user_date (user_id, appointment_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 预设课程数据
INSERT INTO course (user_id, course_name, description, duration, level, category, is_preset) VALUES
(0, '全身力量训练', '针对全身主要肌群的综合性力量训练，适合初学者和进阶训练者', 60, '中级', '力量训练', 1),
(0, '胸肌专项训练', '专注于胸肌发展的训练课程，包含多种推胸动作', 45, '初级', '力量训练', 1),
(0, '背部训练', '针对背部肌群的训练，提升背部厚度和宽度', 45, '初级', '力量训练', 1),
(0, '腿部训练', '深蹲、硬拉等腿部复合动作，打造强健下肢', 60, '中级', '力量训练', 1),
(0, '核心训练', '腹肌、背阔肌等核心肌群训练，增强核心稳定性', 30, '初级', '核心训练', 1),
(0, '有氧运动', '跑步、跳绳、HIIT等有氧运动，提升心肺功能', 30, '初级', '有氧运动', 1),
(0, '瑜伽入门', '基础瑜伽体式练习，提升柔韧性和身体平衡', 45, '初级', '瑜伽', 1),
(0, 'HIIT高强度间歇训练', '短时间高强度训练，快速燃烧卡路里', 20, '中级', '有氧运动', 1),
(0, '哑铃全身训练', '使用哑铃进行全身性力量训练，适合家庭训练', 45, '初级', '力量训练', 1),
(0, '肩部训练', '针对三角肌的专项训练，打造3D肩部', 40, '初级', '力量训练', 1);
