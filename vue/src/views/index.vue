<script setup>
import {
  User,
  Crop,
  EditPen,
  SwitchButton,
  CaretBottom,
  Expand, Fold, HomeFilled, Document
} from '@element-plus/icons-vue'
import avatar from '@/assets/default.png'
//导入接口函数
import { userInfoGetService } from '@/api/user.js'
//导入pinia
import { useUserInfoStore } from '@/store/userInfo.js'
import { useRouter } from 'vue-router'
import { useTokenStore } from "@/store/token";
import { ElMessage, ElMessageBox } from "element-plus";

const userInfoStore = useUserInfoStore();
import { ref } from 'vue'


//获取个人信息
const getUserInf = async () => {
  let result = await userInfoGetService();
  //存储pinia
  userInfoStore.info = result.data;
}
getUserInf()

//dropDown条目被点击后，回调的函数

const tokenStore = useTokenStore()
const router = useRouter()
const handleCommand = (command) => {
  if (command === 'logout') {
    //退出登录  清除token 回到登录页

    //退出登录
    ElMessageBox.confirm(
      '你确认退出登录码？',
      '温馨提示',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
      }
    )
      .then(async () => {
        //用户点击了确认
        //清空pinia中的token和个人信息
        userInfoStore.info = {}
        tokenStore.token = ''
        //跳转到登录页
        await router.push('/login')
      })
      .catch(() => {
        //用户点击了取消
        ElMessage({
          type: 'info',
          message: '取消退出',
        })
      })
  } else {
    //路由跳转
    router.push('/user/' + command)
  }
}

// 菜单折叠控制
const isCollapse = ref(false)

const toggleCollapse = () => {
  isCollapse.value = !isCollapse.value
}

</script>

<template>

  <el-container class="layout-container">
      <!-- 侧边栏宽度切换 -->
      <el-aside :width="isCollapse ? '64px' : '228px'">
      <div class="collapse-toggle">
        <!-- 折叠状态下的 logo 展示优化 -->
        <h2 v-if="!isCollapse" style="text-align: center">考试系统</h2>
        <h2 v-else style="text-align: center">
          <el-icon :size="30"><element-plus-logo /></el-icon>
        </h2>
      </div>

      <!-- 使用 element plus 的 collapse 功能 -->
      <el-menu
        :collapse="isCollapse"
        :collapse-transition="false"  
        :default-active="$route.path"
        active-text-color="#409EFF"
        background-color="#fff"
        class="el-menu-vertical"
        text-color="#000"
        router
      >
        <!-- 首页菜单项 -->
        <el-menu-item index="/sysMenu">
          <el-icon><HomeFilled /></el-icon>
          <template #title><span>首页</span></template>
        </el-menu-item>

        <!-- 考试管理折叠菜单 -->
        <el-sub-menu index="1">
          <template #title>
            <el-icon><Document /></el-icon>
            <span>考试管理</span>
          </template>

          <!-- 子菜单项会自动响应折叠状态 -->
          <el-menu-item index="/course">
            <el-icon><Opportunity /></el-icon>
            <span>课程信息</span>
          </el-menu-item>
          <el-menu-item index="/exam">
            <el-icon><Edit /></el-icon>
            <span>考试信息</span>
          </el-menu-item>
          <el-menu-item index="/subject">
            <el-icon><Notebook /></el-icon>
            <span>题库信息</span>
          </el-menu-item>
          <el-menu-item index="/scores">
            <el-icon><DataAnalysis /></el-icon>
            <span>成绩信息</span>
          </el-menu-item>
        </el-sub-menu>
      </el-menu>
    </el-aside>



    <el-container>
      <!-- 导航栏 -->
      <el-header>
        <div class="nav-toggle" @click="toggleCollapse">
          <el-icon :size="20">
            <component :is="isCollapse ? Expand : Fold" />
          </el-icon>
        </div>

        <el-dropdown placement="bottom-end" @command="handleCommand">
          <!-- 导航栏头像 -->
          <span class="el-dropdown__box">
            <el-avatar :src="userInfoStore.info.userPic ? userInfoStore.info.userPic : avatar" />
            <el-icon>
              <CaretBottom />
            </el-icon>
          </span>

          <!-- 头像下拉菜单 -->
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item command="info" :icon="User">基本资料</el-dropdown-item>
              <el-dropdown-item command="avatar" :icon="Crop">更换头像</el-dropdown-item>
              <el-dropdown-item command="password" :icon="EditPen">重置密码</el-dropdown-item>
              <el-dropdown-item command="logout" :icon="SwitchButton">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
      <el-footer> ©2025 </el-footer>
    </el-container>
  </el-container>
</template>

<style lang="scss" scoped>
.layout-container {
  height: 100vh;

/* 添加折叠动效 */
.el-aside {
  transition: width 0.3s ease;
}

/* 优化折叠状态下的菜单样式 */
.el-menu-vertical:not(.el-menu--collapse) {
  width: 100%;
  min-height: 400px;
}

/* 调整折叠按钮位置 */
.nav-toggle {
  padding: 0 15px;
  cursor: pointer;
  display: flex;
  align-items: center;
  height: 100%;
}
  // 考试管理下拉菜单
  .exam_sub {
    display: flex;
    align-items: center;
    animation: 0.5s linear 1s infinite alternate slidein;
  }

  .exam_sub img {
    width: 50px;
    height: 25px;
    line-height: 25px;
  }

  .el-aside {
    background-color: #fff;
    transition: width 0.3s ease; // 添加过渡动画

    .collapse-toggle {
      height: 64px;
      display: flex;
      align-items: center;
      justify-content: center;
      cursor: pointer;
      border-right: 1px solid #eee;
      transition: background-color 0.3s ease;

      &:hover {
        background-color: #f5f5f5;
      }
    }

    .el-menu {
      border-right: none;

      // 折叠状态下的样式
      &:not(.el-menu--collapse) {
        width: 228px;
      }
    }
  }

  // 处理折叠时的文字隐藏
  .el-menu--collapse {

    .el-sub-menu__title span,
    .el-menu-item span {
      visibility: hidden;
    }

    .el-sub-menu__icon-arrow {
      display: none;
    }
  }

  .el-header {
    background-color: #fff;
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0;

    // .nav-toggle {
    //   height: 64px;
    //   line-height: 64px;
    //   vertical-align: top;
    //   display: inline-block;
    //   cursor: pointer;
    //   -webkit-transition: all .3s, padding 0s;
    //   transition: all .3s, padding 0s;
    //   text-align: center;
    //    width: 80px;
    //   &:hover {
    //     background-color: #f5f5f5;
       
    //   }
    // }

    .el-dropdown__box {
      display: flex;
      align-items: center;
      padding: 0 20px;

      .el-icon {
        color: #999;
        margin-left: 10px;
      }

      &:active,
      &:focus {
        outline: none;
      }
    }
  }

  .el-footer {
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 14px;
    color: #666;
  }
}
</style>
