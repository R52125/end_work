<template>
  <div>
    <Navigation></Navigation>
    <div style="position: absolute;
         left: 50%;
         top: 50%;
          transform: translate(-50%,-50%);">
      <el-card class="box-card text-center">
        <div slot="header" class="clearfix headertext">
          <span>新模板</span>
        </div>
        <!--      <div v-for="o in 4" :key="o" class="text item">-->
        <!--        {{'列表内容 ' + o }}-->
        <!--      </div>-->
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text" id="basic-addon2">模板名称</span>
          </div>
          <input type="text" class="form-control" placeholder="ID" aria-label="Username" aria-describedby="basic-addon1" v-model="moduleName">
        </div>
        <br>
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text" id="basic-addon1">描述</span>
          </div>
          <input type="text" class="form-control" placeholder="Description" aria-label="Username" aria-describedby="basic-addon1" v-model="description">
        </div>
        <br>
        <el-row>
          <el-button type="primary" @click="create">确认</el-button>
<!--          <el-button type="danger" class="ml-5" >重置</el-button>-->
        </el-row>
      </el-card>
    </div>
  </div>
</template>

<script>
import Navigation from "../components/Navigation";
import {mapState, mapMutations} from "vuex";
export default {
  name: "Create",
  components:{
    Navigation
  },
  computed:{
    ...mapState(['moduleName', 'description']),
    moduleName:{
      get(){
        return this.$store.state.moduleName;
      },
      set(value){
        return this.$store.commit('setModuleName', value);
      }
    },
    description:{
      get(){
        return this.$store.state.description;
      },
      set(value){
        return this.$store.commit('setDescription', value);
      }
    }
  },
  methods:{
    ...mapMutations(['createModule']),
    create(){
      this.createModule();
    }
  }
}
</script>

<style scoped>

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