<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <el-breadcrumb :separator-icon="ArrowRight">
          <el-breadcrumb-item>考试管理</el-breadcrumb-item>
          <el-breadcrumb-item>成绩列表</el-breadcrumb-item>
        </el-breadcrumb>
        <div class="extra">
          <el-button type="primary"
                     @click="drawVisible = true;title='添加成绩'; courseModel.courseCode=null;courseModel.courseName=null;courseModel.courseDesc=null;">
            添加成绩
          </el-button>
        </div>
      </div>
    </template>
    <el-table :data="courseList" style="width: 100%">
      <el-table-column label="成绩编号" prop="courseCode" width="150"></el-table-column>
      <el-table-column label="成绩名称" prop="courseName"></el-table-column>
      <el-table-column label="成绩简介" prop="courseDesc"></el-table-column>
      <el-table-column label="操作" width="100">
        <template #default="{ row }">
          <el-button :icon="Edit" circle plain type="primary" @click="updateCourseEcho(row)"></el-button>
          <el-button :icon="Delete" circle plain type="danger" @click="delCourseEcho(row.id)"></el-button>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="暂无成绩数据"/>
      </template>
    </el-table>
  </el-card>

  <el-drawer
    v-model="drawVisible"
    :title="title"
  >
    <el-form :model="courseModel" :rules="rules" label-width="100px" style="padding-right: 30px">
      <el-form-item label="成绩编号" prop="courseCode">
        <el-input v-model="courseModel.courseCode" minlength="1" maxlength="10"></el-input>
      </el-form-item>
      <el-form-item label="成绩名称" prop="courseName">
        <el-input v-model="courseModel.courseName" minlength="1" maxlength="20"></el-input>
      </el-form-item>
      <el-form-item label="成绩简介" prop="courseDesc">
        <el-input 
          v-model="courseModel.courseDesc" 
          type="textarea"
          :rows="4"
          maxlength="100"
          placeholder="请输入成绩简介（最多100字）"
        ></el-input>
      </el-form-item>
    </el-form>

    <span class="draw-footer">
      <el-button type="primary"
                 @click="title==='添加成绩'? addCourse():updateCourse()">
        {{ title.slice(0, 2) }}
      </el-button>
      <el-button @click="drawVisible = false">取消</el-button>
    </span>
  </el-drawer>
</template>

<script setup>
import { useRoute } from 'vue-router';
import { onMounted, ref } from 'vue';

import { Edit, Delete, ArrowRight } from '@element-plus/icons-vue';

const route = useRoute();
const courseId = ref(route.params.id);
const courseName = ref('');

let courseList = ref([]);

onMounted(async () => {

});

const drawVisible = ref(false);

const courseModel = ref({
courseCode: '',
courseName: '',
courseDesc: ''
});

const rules = {
courseCode: [
  { required: true, message: '请输入成绩编号', trigger: 'blur' }
],
courseName: [
  { required: true, message: '请输入成绩名称', trigger: 'blur' }
],
courseDesc: [
  { required: true, message: '请输入成绩简介', trigger: 'blur' }
]
};
</script>

<style scoped>
.header {
display: flex;
align-items: center;
justify-content: space-between;
}

.draw-footer {
display: flex;
justify-content: center;
gap: 20px;
}
</style>