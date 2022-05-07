<template>
  <div>
    <Navigation></Navigation>
    <div style="position: absolute;
         left: 50%;
         top: 50%;
         transform: translate(-50%,-50%);"
          class="text-center">
    <div class="text-center" v-for="(d, index) in moduleCount" :key="index">
      <br>
      <div class="text-center" slot="body">
        <el-card class="box-card shadow-lg" style="border-color: #B3C0D1">
          <!--            nodeName-->
          <div class="mb-2">
            模板名称
          </div>
          <div class="mr-4 mb-2">
            <el-input
                placeholder=""
                v-model="d['moduleName']"
                clearable>
            </el-input>
          </div>
          <!--            nodeContent-->
          <div class="mb-2">
            描述
          </div>
          <div class="mr-4 mb-2">
            <el-input
                type="textarea"
                :autosize="{ minRows: 2, maxRows: 4}"
                placeholder=""
                v-model="d['description']">
            </el-input>
          </div>
        </el-card>
      </div>
    </div>
      <br>
      <el-row>
        <!--          <el-button type="primary" class="mr-3" @click="open2">修改</el-button>-->
        <el-button type="danger" class="ml-3" @click="open1">删除</el-button>
      </el-row>
    </div>
  </div>
</template>

<script>

import Navigation from "../components/Navigation";
import {mapMutations, mapState} from "vuex";

export default {
  name: "ModuleMes",
  components:{
    Navigation
  },
  computed:{
    ...mapState['userName','moduleCount'],
    userName:{
      get(){
        return this.$store.state.userName;
      },
      set(value){
        return this.$store.commit('setUserName', value);
      }
    },
    moduleCount:{
      get(){
        return this.$store.state.moduleCount;
      },
      set(value){
        return this.$store.commit('setModuleCount', value);
      }
    }
  },
  methods:{
    ...mapMutations(['moduleMes', 'delModule']),
    showModule() {
      this.moduleMes();
    },
    open1() {
      this.$prompt('请输入模块名', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(({ value }) => {
        this.$message({
          type: 'success',
          message: '已删除模块: ' + value
        });
        this.$store.state.moduleName = value;
        this.$store.commit('delModule');
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });
    },
  }
}
</script>

<style>
/*.el-card__body {*/
/*  background-color: lightgray;*/
/*}*/
</style>