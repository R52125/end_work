<template>
  <div class="ml-3 mt-3">
    <el-row class="text-center">

<!--      <el-popover-->
<!--          placement="top"-->
<!--          width="160"-->
<!--          v-model="visible">-->
<!--        <p class="text item mt-2" style="white-space: pre" v-html="'节点类型'"></p>-->
<!--        <br>-->
<!--        <el-radio-group v-model="nodeType">-->
<!--          <div class="mb-2">-->
<!--            <el-radio :label="1">普通话术</el-radio>-->
<!--          </div>-->
<!--          &lt;!&ndash;      <div class="mb-2">&ndash;&gt;-->
<!--          &lt;!&ndash;        <el-radio :label="6">任意回答话术</el-radio>&ndash;&gt;-->
<!--          &lt;!&ndash;      </div>&ndash;&gt;-->
<!--          <div class="mb-2">-->
<!--            <el-radio :label="2">结束语</el-radio>-->
<!--          </div>-->
<!--        </el-radio-group>-->
<!--        <el-button type="primary" size="mini" @click="add">确定</el-button>-->
<!--        <el-button slot="reference" class="shadow-lg">➕ 新增节点</el-button>-->
<!--      </el-popover>-->

<!--      <i class="el-icon-zoom-in btn shadow-lg" style="margin-left: 200px; background-color: white"></i>-->
<!--      <i class="el-icon-zoom-out btn shadow-lg" style="background-color: white"></i>-->
<!--      <i class="el-icon-refresh btn shadow-lg" style="background-color: white"></i>-->
    </el-row>

    <div class="text-center" v-for="(d, index) in normalCount" :key="index">
      <br>
        <NormalNode></NormalNode>
    </div>
<!--    <div v-for="(d, index) in normalCount" :key="'info'+index">-->
<!--      {{d["name"]}}-->
<!--      {{index}}-->
<!--    </div>-->
  </div>
</template>

<script>
import {mapMutations, mapState} from "vuex";
import NormalNode from "./NormalNode";
import draggable from "vuedraggable";
export default {
  name: "Edit_area",
  data: function (){
    return{
      visible: false,
    };
  },
  computed:{
    ...mapState(['nodeType', 'normalCount', 'normalIndex']),
    nodeType: {
      get() {
        return this.$store.state.nodeType;
      },
      set(value) {
        return this.$store.commit('setNodeType', value);
      },
    },
    normalCount: {
      get(){
        return this.$store.state.normalCount;
      },
      set(value){
        return this.$store.commit('setNormalCount', value);
      }
    },
    normalIndex: {
      get(){
        return this.$store.state.normalIndex;
      },
      set(value){
        return this.$store.commit('setNormalIndex', value);
      }
    }
  },
  methods:{
    ...mapMutations(['add_normal']),
    add(){
      this.add_normal();
    }
  },
  components:{
    NormalNode,
    draggable,
  }
}
</script>

<style scoped>

</style>