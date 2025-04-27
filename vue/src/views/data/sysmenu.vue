<template>

  <el-card class="page-container">
    <template #header>
      <div class="header">
        <el-breadcrumb :separator-icon="ArrowRight" >
          <el-breadcrumb-item >菜单管理</el-breadcrumb-item>
        </el-breadcrumb>
        <div class="extra">
          <el-button type="primary"
                     @click="drawVisible = true;title='添加菜单'; categoryModel.categoryName=null;categoryModel.categoryAlias=null;">
            添加菜单
          </el-button>
        </div>
      </div>
    </template>
    <el-table  :data="menuList" style="width: 100%" >
      <el-table-column label="菜单编号" prop="menuid" width="150"></el-table-column>
      <el-table-column label="菜单名称" prop="menutitle"></el-table-column>
      <el-table-column label="路由地址" prop="router"></el-table-column>
      <el-table-column label="操作"  width="100">
        <template #default="{ row }">
          <!--  修改版块-->
          <el-button :icon="Edit" circle plain type="primary" @click="updateCategoryEcho(row)"></el-button>
          <!--   删除版块    -->
          <el-button :icon="Delete" circle plain type="danger" @click="delCategoryEcho(row.id)"></el-button>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="没有数据"/>
      </template>
    </el-table>
  </el-card>
  <!-- 添加版块抽屉 -->
  <el-drawer
   v-model="drawVisible"
   :title="title"
  >
<!--    表单  -->
    <el-form :model="categoryModel" :rules="rules" label-width="100px" style="padding-right: 30px">
      <el-form-item label="菜单编号" prop="categoryName">
        <el-input v-model="categoryModel.categoryName" minlength="1" maxlength="10"></el-input>
      </el-form-item>
      <el-form-item label="菜单名称" prop="categoryName">
        <el-input v-model="categoryModel.categoryName" minlength="1" maxlength="10"></el-input>
      </el-form-item>
      <el-form-item label="路由地址" prop="categoryAlias">
        <el-input v-model="categoryModel.categoryAlias" minlength="1" maxlength="15"></el-input>
      </el-form-item>

    </el-form>

        <span class="draw-footer">

            <el-button type="primary"
                       @click="title==='添加分类'? addCategory():updateCategory()"> {{ title.slice(0, 2) }} </el-button>
          <el-button @click="drawVisible = false">取消</el-button>
        </span>

  </el-drawer>
</template>

<script setup>
import {useRoute} from 'vue-router';
import {onMounted, ref} from 'vue';
import {getSectionListById} from '@/api/section'
import { onBeforeRouteUpdate } from 'vue-router';
import {
  Edit,
  Delete,
ArrowRight
} from '@element-plus/icons-vue'
import {getAllMenuService} from "@/api/menu";


/*
*    获取路由地址 /sys/1
* */
const route = useRoute();
 //index组件传递的参数

const menuId = ref(route.params.id)
const menuName = ref('')
/*
*       存储版块数据
* */
 let menuList = ref([])
/*
*   从其他组件进入时执行onMounted发送请求
* */
onMounted(async ()=>{
  let res = await getAllMenuService()
  menuList.value = res.data

})


//控制添加版块弹窗
const drawVisible = ref(false)

//添加版块数据模型
const categoryModel = ref({
  categoryName: '',
  categoryAlias: ''
})
//添加版块表单校验
const rules = {
  categoryName: [
    {required: true, message: '请输入分类名称', trigger: 'blur'},
  ],
  categoryAlias: [
    {required: true, message: '请输入分类别名', trigger: 'blur'},
  ]
}

/*
*    图片上传
* */






</script>

<style scoped>
.header{
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.draw-footer{
  display: flex;
  justify-content: center;
}

</style>
