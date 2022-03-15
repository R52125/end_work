<template>
  <div style="margin-left: 18px">
    <div class="text item mt-2" style="white-space: pre" v-html="'节点类型'"></div>
    <br>
    <el-radio-group v-model="nodeType">
      <div class="mb-2">
        <el-radio :label="1">普通话术</el-radio>
      </div>
<!--      <div class="mb-2">-->
<!--        <el-radio :label="6">任意回答话术</el-radio>-->
<!--      </div>-->
      <div class="mb-2">
        <el-radio :label="2">结束语</el-radio>
      </div>
    </el-radio-group>
    <br>
    <div class="mb-2">
      节点名称
    </div>
    <div class="mr-4 mb-2">
      <el-input
          placeholder="请输入内容"
          v-model="nodeName"
          clearable>
      </el-input>
    </div>
    <div class="mb-2">
      话术内容
    </div>
    <div class="mr-4 mb-2">
      <el-input
          type="textarea"
          :autosize="{ minRows: 2, maxRows: 4}"
          placeholder="请输入内容"
          v-model="nodeContent">
      </el-input>
    </div>
    <br>
    <el-row class="text-center">
      <el-button class="ml-2 mr-2" type="success" @click="add" plain>新增节点</el-button>
<!--      <el-button class="ml-2 mr-2" type="warning" @click="add" plain>取消</el-button>-->
    </el-row>
    <div v-for="(d, index) in normalCount" :key="index">
      {{d["name"]}}
      {{index}}
    </div>
  </div>
</template>

<script>
import {mapMutations, mapState} from "vuex";

export default {
  name: "Node",
  data: function() {
    return {};
  },
  computed:{
    ...mapState(['nodeType', 'nodeName', 'nodeContent', 'normalCount']),
    nodeType:{
      get(){
        return this.$store.state.nodeType;
      },
      set(value){
        return this.$store.commit('setNodeType', value);
      }
    },
    nodeName:{
      get(){
        return this.$store.state.nodeName;
      },
      set(value){
        return this.$store.commit('setNodeName', value);
      }
    },
    nodeContent:{
      get(){
        return this.$store.state.nodeContent;
      },
      set(value){
        return this.$store.commit('setNodeContent', value);
      }
    },
    normalCount:{
      get(){
        return this.$store.state.normalCount;
      },
      set(value){
        return this.$store.commit('setNormalCount', value);
      }
    }
  },
  methods:{
    ...mapMutations(['add_normal']),
    add(){
      this.add_normal();
    }
  }
}
</script>

<style scoped>

</style>