<template>
  <div class="ml-3 mt-3">
<!--    <el-row class="text-center">-->
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
<!--    </el-row>-->

    <div class="text-center" v-for="(d, index) in normalCount" :key="index">
      <br>
<!--        <NormalNode></NormalNode>-->
      <div class="text-center">
        <el-card class="box-card" style="border-color: #B3C0D1">
<!--            nodeName-->
          <div class="mb-2">
            节点名称
          </div>
          <div class="mr-4 mb-2">
            <el-input
                placeholder=""
                v-model="d['name']"
                clearable>
            </el-input>
          </div>
<!--            nodeContent-->
          <div class="mb-2">
            话术内容
          </div>
          <div class="mr-4 mb-2">
            <el-input
                type="textarea"
                :autosize="{ minRows: 2, maxRows: 4}"
                placeholder=""
                v-model="d['content']">
            </el-input>
          </div>

<!--            linkbuttom-->
          <div>
            <el-row class="text-center">
<!--                肯定按钮-->
              <el-popover
                  placement="top"
                  width="200"
                  v-model="d['visible1']"
                  v-if="d['type']==1"
                  class="ml-3">
                <p class="text item mt-2" style="white-space: pre" v-html="'请输入下一个节点的名称'"></p>
                <el-radio-group>
                  <el-input v-model="d['sureNext']" placeholder="请输入内容"></el-input>
                </el-radio-group>
                <br>
<!--                  <el-button type="primary" size="mini" @click="addSureNext()">确定</el-button>-->
                <el-button slot="reference" class="shadow-lg" type="success">肯定</el-button>
              </el-popover>
<!--                否定按钮-->
              <el-popover
                  placement="top"
                  width="200"
                  v-model="d['visible2']"
                  v-if="d['type']==1"
                  class="ml-3">
                <p class="text item mt-2" style="white-space: pre" v-html="'请输入下一个节点的名称'"></p>
                <el-radio-group>
                  <el-input v-model="d['denyNext']" placeholder="请输入内容"></el-input>
                </el-radio-group>
                <br>
<!--                  <el-button type="primary" size="mini" @click="addDenyNext()">确定</el-button>-->
                <el-button slot="reference" class="shadow-lg" type="danger">否定</el-button>
              </el-popover>
<!--                未知按钮-->
              <el-popover
                  placement="top"
                  width="200"
                  v-model="d['visible3']"
                  v-if="d['type']==1"
                  class="ml-3">
                <p class="text item mt-2" style="white-space: pre" v-html="'请输入下一个节点的名称'"></p>
                <el-radio-group>
                  <el-input v-model="d['unknownNext']" placeholder="请输入内容"></el-input>
                </el-radio-group>
                <br>
<!--                  <el-button type="primary" size="mini" @click="addUnknownNext()">确定</el-button>-->
                <el-button slot="reference" class="shadow-lg" type="primary">未知</el-button>
              </el-popover>
<!--                <el-button class="ml-2 mr-2" type="warning" plain>否定</el-button>-->
<!--                <el-button class="ml-2 mr-2" type="warning" plain>未知</el-button>-->
            </el-row>
          </div>
        </el-card>
      </div>

    </div>
    <br>
    <el-row class="text-center">
      <el-button class="ml-3 mr-2" type="info" @click="push()">保存</el-button>
    </el-row>
  </div>
</template>

<script>
import {mapMutations, mapState} from "vuex";
export default {
  name: "Edit_area",
  data: function (){
    return{};
  },
  computed:{
    ...mapState(['nodeContent', 'nodeName', 'nodeType', 'normalCount']),
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
    nodeContent: {
      get(){
        return this.$store.state.nodeContent;
      },
      set(value){
        return this.$store.commit('setNodeContent', value);
      }
    },
    nodeName: {
      get() {
        return this.$store.state.nodeName;
      },
      set(value) {
        return this.$store.commit('setNodeName', value);
      }
    }
  },
  methods:{
    ...mapMutations(['sendNormalCount']),
    push(){
      this.sendNormalCount();
    }
  },
  components:{
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