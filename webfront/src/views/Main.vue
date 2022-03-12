
  <template>
    <div class="container-fluid">
      <b-jumbotron class="text-center">
        <template v-slot:header >话术模板管理</template>
<!--        <template v-slot:lead>-->
<!--          软工第10小组-->
<!--        </template>-->
        <hr class="my-4">
      </b-jumbotron>

    <b-tabs content-class="mt-3 ml-5 mr-5">
      <b-tab variant="primary" title="管理员" active>
        <b-card bg-variant="light">
          <div>
            <form>
              <div class="form-group">
                <b-form-input id="input-1" placeholder="请输入管理员账号"></b-form-input>
                <small id="adminNameHelpBlock" class="form-text text-muted">
                  用户名5位，只包含数字
                </small>
                <br>
                <b-form-input id="input-1" placeholder="请输入密码"></b-form-input>
                <small id="passwordHelpBlock" class="form-text text-muted">
                  密码长度为6-18位
                </small>
              </div>
            </form>
          </div>
          <b-button variant="primary" @click="login">管理员登录</b-button>
        </b-card>
      </b-tab>
    </b-tabs>

    </div>
  </template>

<script>

import {mapMutations} from "vuex";

export default {
  name: 'Main',
  data: function (){
    return{}
  },
  computed:{

  },
  methods:{
    ...mapMutations(['check_id']),
    login(){
      this.check_id()
    }
  }
}
</script>
