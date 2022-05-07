<template>
  <div>
      <el-menu
        :default-active="activeIndex2"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b">
        <el-submenu index="2">
          <template slot="title">我的模板</template>
<!--          <el-menu-item index="2-1">模板1</el-menu-item>-->
<!--          <el-menu-item index="2-2">模板2</el-menu-item>-->
<!--          <el-menu-item index="2-3">模板3</el-menu-item>-->
<!--          <el-menu-item index="2-5">更多模板</el-menu-item>-->
          <el-button class="ml-3" type="text" @click="open">更多模块</el-button>
<!--          <el-submenu index="2-4">-->
<!--            <template slot="title">更多</template>-->
<!--            <el-menu-item index="2-4-1">选项1</el-menu-item>-->
<!--            <el-menu-item index="2-4-2">选项2</el-menu-item>-->
<!--            <el-menu-item index="2-4-3">选项3</el-menu-item>-->
<!--          </el-submenu>-->
        </el-submenu>
        <el-menu-item index="1"><router-link to="/create">新模板创建</router-link></el-menu-item>
        <el-menu-item index="3" disabled>消息中心</el-menu-item>
        <el-menu-item index="4"><router-link to="/myself">个人中心</router-link></el-menu-item>
<!--        <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>-->
    </el-menu>
    <router-view></router-view>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "Navigation",
  data() {
    return {
      activeIndex2: '4'
    };
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
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    open() {
      this.$prompt('请输入模块名', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        // inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
        // inputErrorMessage: '邮箱格式不正确'
      }).then(({ value }) => {
        this.$message({
          type: 'success',
          message: '你的模块是: ' + value
        });
        this.$store.state.moduleName = value;
        this.$store.commit('getNormalCount');
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });
    }
  }
}
</script>

<style scoped>

</style>