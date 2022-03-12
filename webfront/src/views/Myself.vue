<template>
  <div>
    <Navigation></Navigation>
    <div style="position: absolute;
         left: 50%;
         top: 50%;
          transform: translate(-50%,-50%);">
      <el-card class="box-card text-center">
        <div slot="header" class="clearfix headertext">
          <span>个人中心</span>
        </div>
  <!--      <div v-for="o in 4" :key="o" class="text item">-->
  <!--        {{'列表内容 ' + o }}-->
  <!--      </div>-->
        <div class="text item" style="white-space: pre" v-html="'管理员ID   R52125'"></div>
        <div class="text item">
          <pre class="text item">模板个数   5<el-button style="float: right; padding: 3px 0" type="text">详情</el-button></pre>
        </div>

        <el-row>
  <!--        <el-button type="primary" class="mr-5">返回上一步</el-button>-->
          <el-button type="danger">退出登录</el-button>
        </el-row>
      </el-card>

    </div>
  </div>
</template>

<script>

import Navigation from "../components/Navigation";

export default {
  name: "Myself",
  components:{
    Navigation
  }
}
</script>

<style scoped>
.text {
  font-size: 20px;
}

.item {
  margin-top: 25px;
  margin-bottom: 25px;
}



.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.headertext{
  font-size: 25px;
}

.box-card {
  width: 480px;
}
</style>