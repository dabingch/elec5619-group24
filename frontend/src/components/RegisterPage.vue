<template>
  <div class="body">
    <div class="title-div">
      <div class="title-word">HD</div>
      <div class="title-word">Helper</div>
    </div>
    <div class="login-div">
      <div class="input-div">
        <div class="input-attributes">First Name: </div>
        <el-input class="input-area" v-model="firstName" placeholder="Please input your first name"></el-input>
      </div>
      <div class="input-div">
        <div class="input-attributes">Last Name: </div>
        <el-input class="input-area" v-model="lastName" placeholder="Please input your last name"></el-input>
      </div>
      <div class="input-div">
        <div class="input-attributes">Gender: </div>
        <el-select v-model="gender" class="gender-selector" placeholder="Select your gender" size="large">
          <el-option
              v-for="gender in genders"
              :key="gender"
              :label="gender"
              :value="gender"
          />
        </el-select>
      </div>
      <div class="input-div">
        <div class="input-attributes">E-mail: </div>
        <el-input class="input-area" type="email" v-model="email" placeholder="Please input your e-mail"></el-input>
      </div>
      <div class="input-div">
        <div class="input-attributes">Password: </div>
        <el-input class="input-area" type="password" show-password v-model="password" placeholder="Please input your password"></el-input>
      </div>
      <el-button class="login-button" type="primary" @click="signIn">SIGN IN</el-button>

    </div>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "LoginPage",
  data() {
    return {
      genders: ['male', 'female'],
      firstName: '',
      lastName: '',
      gender: '',
      email: '',
      password: '',
    }
  },
  methods: {
    signIn() {
      if (this.firstName && this.lastName && this.gender && this.email && this.password != '') {
       axios.post('/api/signup', {
          'firstName': this.firstName,
          'lastName': this.lastName,
          'gender': this.gender,
          'email': this.email,
          'password': this.password,
        }).then(res => {
         alert(res.data.message)
         if (res.data.status == 'Success') {
           this.$router.push({path:"/"})
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
  height: 502px;
  display: flex;
  flex-direction: column;
  align-items: center;
  border: solid 1px black ;
}
.input-div {
  display: flex;
  width: 90%;
  height: 12%;
  margin-top: 15px;
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
  display: flex;
  margin-left: 10px;
  height: 80%;
}
.gender-selector {
  width: 100%;
  margin-left: 10px;
}
.login-button {
  width: 150px;
  margin-top: 50px;
  font-size: 13px;
}
</style>
