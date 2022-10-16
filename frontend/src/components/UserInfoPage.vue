<template>
  <div class="body">
    <div>
      <div class="table-div">
        <div class="input-div">
          <div class="input-attributes">First Name: </div>
          <el-input class="input-area" v-model="firstName"></el-input>
        </div>
        <div class="input-div">
          <div class="input-attributes">Last Name: </div>
          <el-input class="input-area" v-model="lastName"></el-input>
        </div>
        <div class="input-div">
          <div class="input-attributes">Gender: </div>
          <el-input class="input-area" v-model="gender"></el-input>
        </div>
        <div class="input-div">
          <div class="input-attributes">E-mail: </div>
          <el-input class="input-area" disabled v-model="email"></el-input>
        </div>
        <div class="input-div">
          <div class="input-attributes">Password: </div>
          <el-input class="input-area" placeholder="Only input when you want to change password." v-model="password"></el-input>
        </div>
        <el-button class="update-bt" @click="updateUserInfo">
          Update
        </el-button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "UserInfoPage",
  props: {
    student: {},
  },
  data() {
    return {
      id: "",
      firstName: "",
      lastName: "",
      gender: "",
      email: "",
      password: ""
    }
  },
  methods: {
    updateUserInfo() {
      if (this.firstName && this.lastName && this.gender && this.email != '') {
        axios.put('/api/students/' + this.id, {
          'firstName': this.firstName,
          'lastName': this.lastName,
          'gender': this.gender,
          'email': this.email,
          'password': this.password,
          "enabled": true,
          "isAdmin": false
        })
        this.$emit('firstName', this.firstName)
        alert("Update Successfully!")
        if (this.password != "") {
          this.$router.push({
            path: '/',
          })
        }
      } else {
        alert('Please input all the information that required!')
      }
    }
  },
  mounted() {
    this.id = this.student.id
    this.firstName = this.student.firstName
    this.lastName = this.student.lastName
    this.gender = this.student.gender
    this.email = this.student.email
  }
}
</script>

<style scoped>
.body {
  display: flex;
  width: 100%;
  height: 100%;
  border: 1px solid black;
  flex-direction: column;
  align-items: center;
}
.table-div {
  width: 500px;
  margin-top: 40px;
}
.input-div {
  display: flex;
  flex-direction: row;
  align-items: center;
  font-weight: bold;
  margin-bottom: 20px;
}
.input-attributes {
  width: 40%;
}
.update-bt {
  margin-top: 20px;
}
</style>
