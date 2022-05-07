<template>
  <div class="wrap">
    <Navigation></Navigation>
    <div class="page-header text-center" style="margin-top: 20px; margin-left: 380px; margin-right: 380px">
      <el-input v-model="moduleName" placeholder="请输入模块名"></el-input>
    </div>
    <el-container style="height: 700px; border: 1px solid #eee ;margin-top: 20px;">
      <el-container>
<!--        <el-aside width="230px" style="height: 700px; border: 1px solid #000;">-->
<!--        </el-aside>-->
        <el-main style="height: 700px; border: 1px solid #000; padding:0; margin:0 20px; background-color: rgba(185,185,185,0.31)" class="content">
          <Edit_area></Edit_area>
        </el-main>
        <el-aside style="height: 700px; border: 1px solid #000; margin-right: 20px; background-color: rgba(185,185,185,0.31)">
          <el-header>节点属性配置</el-header>
          <Node></Node>
        </el-aside>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Navigation from "../components/Navigation";
import Edit_area from "../components/Edit_area";
import Node from "../components/Node";
import {mapState} from "vuex";
export default {
  name: "Edit_view",
  components:{
    Navigation,
    Node,
    Edit_area
  },
  computed:{
    ...mapState(['moduleName']),
    moduleName:{
      get(){
        return this.$store.state.moduleName;
      },
      set(value){
        return this.$store.commit('setModuleName', value);
      }
    }
  }
}
</script>

<style scoped>
.el-header{
  background-color: #B3C0D1;
  text-align: center;
  line-height:60px;
}

.content{
  height: calc(100vh - 90px)
}
</style>