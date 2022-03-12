<template>
  <div style="margin-left: 18px">
    <div class="text item mt-2" style="white-space: pre" v-html="'节点类型'"></div>
    <br>
    <el-radio-group v-model="radio">
      <div class="mb-2">
        <el-radio :label="3">普通话术</el-radio>
      </div>
      <div class="mb-2">
        <el-radio :label="6">任意回答话术</el-radio>
      </div>
      <div class="mb-2">
        <el-radio :label="9">结束语</el-radio>
      </div>
      </el-radio-group>
    <br>
    <div class="mb-2">
      节点名称
    </div>
    <div class="mr-4 mb-2">
      <el-input
          placeholder="请输入内容"
          v-model="input"
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
          v-model="textarea2">
      </el-input>
    </div>
  </div>
</template>

<script>
export default {
  name: "Node",
  data () {
    return {
      radio: 3,
      input: '',
      textarea2: ''
    };
  }
}
</script>

<style scoped>

</style>