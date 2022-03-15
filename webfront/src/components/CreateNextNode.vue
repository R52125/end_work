<template>
<div>

  <el-popover
      placement="top"
      width="160"
      v-model="visible1"
      class="ml-3">
    <p class="text item mt-2" style="white-space: pre" v-html="'节点类型'"></p>
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
    <el-button type="primary" size="mini" @click="add">确定</el-button>
    <el-button slot="reference" class="shadow-lg" type="success">肯定</el-button>
  </el-popover>

  <el-popover
      placement="top"
      width="160"
      v-model="visible2"
      class="ml-3">
    <p class="text item mt-2" style="white-space: pre" v-html="'节点类型'"></p>
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
    <el-button type="primary" size="mini" @click="add">确定</el-button>
    <el-button slot="reference" class="shadow-lg" type="danger">否定</el-button>
  </el-popover>

  <el-popover
      placement="top"
      width="160"
      v-model="visible3"
      class="ml-3">
    <p class="text item mt-2" style="white-space: pre" v-html="'节点类型'"></p>
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
    <el-button type="primary" size="mini" @click="add">确定</el-button>
    <el-button slot="reference" class="shadow-lg" type="primary">未知</el-button>
  </el-popover>
</div>
</template>

<script>
import {mapMutations, mapState} from "vuex";

export default {
  name: "CreateNextNode",
  data: function(){
    return {
      visible1:false,
      visible2:false,
      visible3:false,
    }
  },
  computed:{
    ...mapState(['nodeType']),
    nodeType:{
      get(){
        return this.$store.state.nodeType;
      },
      set(value){
        return this.$store.commit('setNodeType', value);
      }
    }
  },
  methods:{
    ...mapMutations(['add_normal']),
    add(){
      this.add_normal()
    }
  }
}
</script>

<style scoped>

</style>