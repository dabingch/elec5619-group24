<template>
  <div class="body">
    <div class="title-div">
      <div class="title-word">HD</div>
      <div class="title-word">Helper</div>
    </div>
    <div class="login-div">
      <div class="input-div">
        <div class="input-attributes">E-mail: </div>
        <el-input class="input-area" v-model="email" placeholder="Please input your e-mail"></el-input>
      </div>
      <div class="input-div">
        <div class="input-attributes">Password: </div>
        <el-input type="password" class="input-area" v-model="password" placeholder="Please input your password"></el-input>
      </div>
      <el-button class="login-button" type="primary" @click="signIn">SIGN IN</el-button>
      <router-link to="/registerPage" class="create-account">
        Create Account
      </router-link>
    </div>

  </div>
</template>

<script>
// import axios from "axios";

import axios from "axios";

export default {
  name: "LoginPage",
  data() {
    return {
      email: '',
      password: '',
    }
  },
  methods: {
    signIn() {
      if (this.email && this.password != '') {
        axios.post('/api/signin', {
          'email': this.email,
          'password': this.password,
        }).then(res => {
          console.log(res)
          if (res.data.status == 'Success') {
            if (res.data.student.isAdmin) {
              this.$router.push({
                path: '/adminHomePage',
                query: {
                  id: res.data.student.id,
                }
              })
            } else {
              this.$router.push({
                path: '/homePage',
                query: {
                  id: res.data.student.id,
                }
              })
            }
          }
        }).catch(function (error) {
          console.log(error);
          alert(error.response.data)
        });

      } else {
        alert('Please input all the information that required!')
      }
    }
  }
}

</script>

<style scoped>
.body {
  padding-top: 100px;
  display: flex;
  flex-direction: row;
  justify-content: center;
}
.title-div {
  display: flex;
  width: 300px;
  flex-direction: column;
  justify-content: flex-start;
}
.title-word {
  display: flex;
  font-size: 72px;
  font-weight: bold;
  font-family: Arial;
  margin-top: 10px;
}
.login-div {
  width: 538px;
  height: 299px;
  display: flex;
  flex-direction: column;
  align-items: center;
  border: solid 1px black ;
}
.input-div {
  display: flex;
  width: 90%;
  height: 15%;
  margin-top: 30px;
  align-items: center;
}
.input-attributes {
  display: flex;
  width: 40%;
  align-items: center;
  justify-content: end;
  font-size: 24px;
  font-weight: bold;
  font-family: Arial;
}
.input-area {
  margin-left: 10px;
  display: flex;
  height: 80%;
}
.login-button {
  width: 150px;
  margin-top: 40px;
  font-size: 13px;
}
.create-account {
  margin-top: 20px;
  color: #1C85CE;
  font-size: 13px;
}
</style>
