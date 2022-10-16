<template>
  <div class="body">
    <!--    <div class="search-box">-->
    <!--      <span class="search-word">Course Name: </span>-->
    <!--      <el-input class="search-bar" placeholder="Search course name" v-model="searchWord"></el-input>-->
    <!--      <el-button @click="searchCourse">Search</el-button>-->
    <!--    </div>-->
    <div class="content-box">
      <el-table class="course-table" border stripe :data="studentInfo">
        <el-table-column prop="id" label="ID" width="70"></el-table-column>
        <el-table-column prop="firstName" label="First Name" width="100"></el-table-column>
        <el-table-column prop="lastName" label="Last Name" width="100"></el-table-column>
        <el-table-column prop="email" label="E-mail" width=""></el-table-column>
        <el-table-column prop="isAdmin" label="is Admin" width="100"></el-table-column>
        <el-table-column width="120" label="Operations">
          <template #default="scope">
            <el-button v-if="scope.row.enabled && !scope.row.isAdmin" @click="disableStudent(scope.row.id)">disable</el-button>
            <el-button v-if="!scope.row.enabled && !scope.row.isAdmin" @click="enableStudent(scope.row.id)">enable</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "AdminStudentPage",
  data() {
    return {
      studentInfo: [],
    }
  },
  methods: {
    enableStudent(studentId) {
      this.refreshPage()
      axios.put('/api/students/enable/' + studentId).then(res => {
        console.log(res)
        alert('Modified Successfully!')
      }).catch(function (error) {
        console.log(error);
      });
      this.refreshPage()
    },
    disableStudent(studentId) {
      this.refreshPage()
      axios.put('/api/students/disable/' + studentId).then(res => {
        console.log(res)
        alert('Modified Successfully!')
      }).catch(function (error) {
        console.log(error);
      });
      this.refreshPage()
    },
    refreshPage() {
      axios.get('/api/students/').then(res => {
        this.studentInfo = res.data
        console.log(this.studentInfo)
      }).catch(function (error) {
        console.log(error);
      });
    },
  },
  mounted() {
    this.refreshPage()
  }
}
</script>

<style scoped>
.body {
  display: flex;
  flex-direction: column;
  height: 100%;
}
.search-box {
  width: 100%;
  height: 15%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: end;
  font-weight: bold;
  font-size: 40px;
}
.content-box {
  margin-top: 20px;
  width: 100%;
  height: 80%;
}
.course-table {
  width: 100%;
  height: 100%;
}
.post-box {
  display: flex;
  width: 100%;
  justify-content: start;
  margin-top: 10px;
}
.post-word {
  color: #56A3DA;
  font-weight: bold;
}
.input-div {
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-bottom: 10px;
}
.input-attributes {
  width: 30%;
}
.input-area {
  width: 60%;
}
.add-button {
  margin-top: 20px;
}
</style>
