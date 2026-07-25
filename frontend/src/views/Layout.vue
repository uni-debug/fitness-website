<script setup lang="ts">
import { ref, computed } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { 
  LayoutDashboard, 
  Dumbbell, 
  Apple, 
  TrendingUp, 
  BookOpen, 
  LogOut, 
  User,
  Search,
  Bell
} from 'lucide-vue-next'

const router = useRouter()
const route = useRoute()

const user = ref(JSON.parse(localStorage.getItem('user') || '{}'))

const menuItems = [
  { path: '/dashboard', icon: LayoutDashboard, label: '仪表盘' },
  { path: '/workout', icon: Dumbbell, label: '训练记录' },
  { path: '/diet', icon: Apple, label: '饮食追踪' },
  { path: '/progress', icon: TrendingUp, label: '成果展示' },
  { path: '/courses', icon: BookOpen, label: '课程管理' }
]

const activeMenu = computed(() => route.path)

const handleLogout = () => {
  localStorage.removeItem('token')
  localStorage.removeItem('user')
  router.push('/login')
}
</script>

<template>
  <div class="app-layout">
    <aside class="sidebar">
      <div class="logo-section">
        <div class="logo-icon">
          <Dumbbell :size="28" />
        </div>
        <span class="logo-text">CalorieTech</span>
      </div>

      <nav class="menu-nav">
        <button
          v-for="item in menuItems"
          :key="item.path"
          :class="['menu-item', { active: activeMenu === item.path }]"
          @click="router.push(item.path)"
        >
          <component :is="item.icon" :size="20" />
          <span>{{ item.label }}</span>
        </button>
      </nav>

      <div class="sidebar-footer">
        <button class="menu-item logout" @click="handleLogout">
          <LogOut :size="20" />
          <span>退出登录</span>
        </button>
      </div>
    </aside>

    <main class="main-content">
      <header class="header">
        <div class="search-box">
          <Search :size="18" />
          <input type="text" placeholder="搜索训练、饮食..." />
        </div>

        <div class="header-right">
          <button class="notification-btn">
            <Bell :size="20" />
            <span class="badge">3</span>
          </button>
          <div class="user-info">
            <div class="user-avatar">
              <User :size="20" />
            </div>
            <div class="user-details">
              <span class="user-name">{{ user.nickname || user.username }}</span>
              <span class="user-status">在线</span>
            </div>
          </div>
        </div>
      </header>

      <div class="content-wrapper">
        <router-view />
      </div>
    </main>
  </div>
</template>

<style scoped>
.app-layout {
  display: flex;
  min-height: 100vh;
  background: #f5f7fa;
}

.sidebar {
  width: 260px;
  background: linear-gradient(180deg, #1a1a2e 0%, #16213e 100%);
  color: white;
  display: flex;
  flex-direction: column;
  position: fixed;
  left: 0;
  top: 0;
  height: 100vh;
  z-index: 100;
}

.logo-section {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 24px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
}

.logo-icon {
  width: 44px;
  height: 44px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.logo-text {
  font-size: 20px;
  font-weight: 700;
}

.menu-nav {
  flex: 1;
  padding: 16px;
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.menu-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 14px 16px;
  border: none;
  background: transparent;
  color: rgba(255, 255, 255, 0.7);
  font-size: 14px;
  font-weight: 500;
  border-radius: 10px;
  cursor: pointer;
  transition: all 0.3s;
}

.menu-item:hover {
  background: rgba(255, 255, 255, 0.1);
  color: white;
}

.menu-item.active {
  background: linear-gradient(135deg, rgba(102, 126, 234, 0.3) 0%, rgba(118, 75, 162, 0.3) 100%);
  color: white;
  border-left: 3px solid #667eea;
}

.sidebar-footer {
  padding: 16px;
  border-top: 1px solid rgba(255, 255, 255, 0.1);
}

.menu-item.logout:hover {
  background: rgba(239, 68, 68, 0.2);
  color: #ef4444;
}

.main-content {
  flex: 1;
  margin-left: 260px;
  display: flex;
  flex-direction: column;
}

.header {
  padding: 16px 32px;
  background: white;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: sticky;
  top: 0;
  z-index: 50;
}

.search-box {
  display: flex;
  align-items: center;
  gap: 12px;
  background: #f5f7fa;
  padding: 12px 20px;
  border-radius: 12px;
  width: 360px;
}

.search-box input {
  flex: 1;
  border: none;
  background: transparent;
  font-size: 14px;
  outline: none;
}

.search-box input::placeholder {
  color: #999;
}

.header-right {
  display: flex;
  align-items: center;
  gap: 20px;
}

.notification-btn {
  position: relative;
  width: 44px;
  height: 44px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #f5f7fa;
  border: none;
  border-radius: 12px;
  cursor: pointer;
  transition: all 0.3s;
}

.notification-btn:hover {
  background: #e8ebf0;
}

.badge {
  position: absolute;
  top: 8px;
  right: 8px;
  background: #ef4444;
  color: white;
  font-size: 10px;
  font-weight: 600;
  padding: 2px 6px;
  border-radius: 10px;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 12px;
}

.user-avatar {
  width: 44px;
  height: 44px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
}

.user-details {
  display: flex;
  flex-direction: column;
}

.user-name {
  font-size: 14px;
  font-weight: 600;
  color: #1a1a2e;
}

.user-status {
  font-size: 12px;
  color: #22c55e;
}

.content-wrapper {
  flex: 1;
  padding: 32px;
  overflow-y: auto;
}

@media (max-width: 768px) {
  .sidebar {
    width: 80px;
  }
  
  .logo-text {
    display: none;
  }
  
  .menu-item span {
    display: none;
  }
  
  .main-content {
    margin-left: 80px;
  }
  
  .header {
    padding: 12px 16px;
  }
  
  .search-box {
    width: 200px;
  }
  
  .content-wrapper {
    padding: 16px;
  }
}
</style>
