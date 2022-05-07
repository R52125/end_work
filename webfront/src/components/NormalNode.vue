<template>
  <div class="text-center">
    <el-card class="box-card">
      <div class="mb-2">
        节点名称
      </div>
      <div class="mr-4 mb-2">
        <el-input
            placeholder=""
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
            placeholder=""
            v-model="nodeContent">
        </el-input>
      </div>

<!--      产生下一个节点的按钮按钮-->
    <CreateNextNode></CreateNextNode>
    </el-card>
  </div>
</template>

<script>
import {mapMutations, mapState} from 'vuex';
import CreateNextNode from "./CreateNextNode";
export default {
  name: "NormalNode",
  data: function () {
    return {
      visible:false,
    };
  },
  computed:{
    ...mapState[('nodeContent', 'nodeName', 'nodeType', 'normalCount')],
    normalCount:{
      get(){
        return this.$store.state.normalCount;
      },
      set(value){
        return this.$store.commit('setNormalCount', value);
      }
    },
    nodeContent: {
      get() {
        return this.$store.state.nodeContent;
      },
      set(value) {
        return this.$store.commit('setNodeContent', value);
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
  },
  components:{
    CreateNextNode,
  }
}
</script>

<style scoped>

.box-card {
  width: 480px;
  margin-left: auto;
  margin-right: auto;
}
</style>