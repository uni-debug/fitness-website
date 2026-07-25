# 🏋️ Fitness Website - 健身管理网站

基于 Vue 3 + Spring Boot 的现代化健身管理网站，集成 exercises-dataset 数据集，提供完整的训练记录、饮食管理和进度追踪功能。

## ✨ 功能特性

### 📊 仪表盘
- 实时训练统计展示
- 支持周/月/年数据切换
- 图表可视化训练趋势

### 📝 训练记录
- 添加/编辑/删除训练记录
- 搜索选择动作（支持 1324+ 动作）
- 动图预览和详细动作说明
- 记录组数、次数、重量、时长

### 🥗 饮食管理
- 饮食记录管理
- 每日营养统计
- 热量摄入追踪

### 📈 进度追踪
- 身体数据记录
- 体重变化趋势
- 训练目标管理

### 🎓 课程管理
- 课程浏览和预约
- 教练信息展示

## 🛠️ 技术栈

### 前端
| 技术 | 版本 | 说明 |
|------|------|------|
| Vue | 3.x | 渐进式 JavaScript 框架 |
| TypeScript | 5.x | 类型安全 |
| Element Plus | 2.x | UI 组件库 |
| Vue Router | 4.x | 路由管理 |
| Pinia | 2.x | 状态管理 |
| Axios | 1.x | HTTP 客户端 |
| Chart.js | 4.x | 图表库 |
| Lucide Vue | 0.x | 图标库 |
| Vite | 6.x | 构建工具 |

### 后端
| 技术 | 版本 | 说明 |
|------|------|------|
| Spring Boot | 3.x | Java 后端框架 |
| MyBatis Plus | 3.5.x | ORM 框架 |
| MySQL | 8.x | 数据库 |
| JWT | 0.12.x | 身份认证 |
| Druid | 1.2.x | 连接池 |
| Jackson | 2.x | JSON 处理 |

## 📁 项目结构

```
fitness-website/
├── backend/                 # Spring Boot 后端
│   ├── src/main/java/com/example/fitness/
│   │   ├── FitnessApplication.java    # 启动类
│   │   ├── common/                    # 公共类
│   │   │   ├── Result.java           # 统一响应
│   │   │   └── AuthUtil.java         # 认证工具
│   │   ├── config/                    # 配置类
│   │   │   ├── JwtUtil.java          # JWT 工具
│   │   │   ├── JwtInterceptor.java   # JWT 拦截器
│   │   │   ├── WebConfig.java        # Web 配置
│   │   │   ├── JacksonConfig.java    # JSON 配置
│   │   │   ├── MybatisPlusConfig.java# MyBatis 配置
│   │   │   └── DataInitializer.java  # 数据初始化
│   │   ├── controller/                # 控制器
│   │   ├── service/                   # 服务层
│   │   ├── mapper/                    # 数据访问层
│   │   └── entity/                    # 实体类
│   ├── src/main/resources/
│   │   ├── application.yml            # 应用配置
│   │   └── exercises-dataset/         # 动作数据集
│   └── pom.xml                        # Maven 配置
├── frontend/                # Vue 3 前端
│   ├── src/
│   │   ├── api/                       # API 接口
│   │   ├── components/                # 组件
│   │   ├── router/                    # 路由配置
│   │   ├── stores/                    # 状态管理
│   │   ├── utils/                     # 工具函数
│   │   ├── views/                     # 页面视图
│   │   ├── App.vue                    # 根组件
│   │   ├── main.ts                    # 入口文件
│   │   └── style.scss                 # 全局样式
│   ├── index.html                     # HTML 模板
│   ├── package.json                   # 依赖配置
│   ├── vite.config.ts                 # Vite 配置
│   └── tsconfig.json                  # TypeScript 配置
└── .gitignore                         # Git 忽略配置
```

## 🚀 快速开始

### 环境要求

- **Java**: JDK 21+
- **Node.js**: 18+
- **MySQL**: 8.0+

### 1. 数据库配置

创建数据库并配置用户：

```sql
CREATE DATABASE fitness_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'fitness_user'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON fitness_db.* TO 'fitness_user'@'localhost';
FLUSH PRIVILEGES;
```

### 2. 后端运行

```bash
cd backend

# 编译项目
mvn compile

# 运行项目
mvn spring-boot:run
```

后端服务启动后访问：`http://localhost:8081`

### 3. 前端运行

```bash
cd frontend

# 安装依赖
npm install

# 开发模式运行
npm run dev
```

前端服务启动后访问：`http://localhost:5176`

### 4. 默认用户

| 用户名 | 密码 |
|--------|------|
| testuser | 123456 |

## 🔌 API 接口

### 认证接口
| 方法 | 路径 | 说明 |
|------|------|------|
| POST | `/api/auth/login` | 用户登录 |
| POST | `/api/auth/register` | 用户注册 |

### 训练记录接口
| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/api/workout/list` | 获取训练记录列表 |
| POST | `/api/workout` | 创建训练记录 |
| PUT | `/api/workout/{id}` | 更新训练记录 |
| DELETE | `/api/workout/{id}` | 删除训练记录 |
| GET | `/api/workout/weekly` | 获取周统计 |
| GET | `/api/workout/monthly` | 获取月统计 |
| GET | `/api/workout/yearly` | 获取年统计 |

### 动作库接口
| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/api/exercise/search` | 搜索动作 |

### 饮食接口
| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/api/diet/list` | 获取饮食记录 |
| POST | `/api/diet` | 创建饮食记录 |
| GET | `/api/diet/daily` | 获取每日统计 |

## 📊 数据集

本项目使用 [exercises-dataset](https://github.com/hasaneyldrm/exercises-dataset) 数据集，包含：

- **1324+ 动作**：涵盖全身各部位训练
- **动作分类**：力量、有氧、拉伸等
- **动图演示**：每个动作配有 GIF 动图
- **中文说明**：动作说明和步骤已翻译为中文

## 📝 开发说明

### 前端开发

```bash
# 启动开发服务器
npm run dev

# 构建生产版本
npm run build

# 代码检查
npm run lint
```

### 后端开发

```bash
# 编译
mvn compile

# 运行
mvn spring-boot:run

# 打包
mvn package

# 运行打包后的 Jar
java -jar target/fitness-website-1.0.0.jar
```

## 📄 License

MIT License

## 🤝 贡献

欢迎提交 Issue 和 Pull Request！

---

**健身管理网站** - 让运动更科学，让健康更简单 💪