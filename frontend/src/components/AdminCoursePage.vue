<template>
  <div class="body">
    <!--    <div class="search-box">-->
    <!--      <span class="search-word">Course Name: </span>-->
    <!--      <el-input class="search-bar" placeholder="Search course name" v-model="searchWord"></el-input>-->
    <!--      <el-button @click="searchCourse">Search</el-button>-->
    <!--    </div>-->
    <div class="content-box">
      <el-table class="course-table" border stripe :data="courseInfo" :key="tableKey">
        <el-table-column prop="id" label="ID" width="70"></el-table-column>
        <el-table-column prop="courseName" label="Course Name" width=""></el-table-column>
        <el-table-column prop="lecturer.firstName" label="Lecturer" width="150"></el-table-column>
        <el-table-column prop="tutor.firstName" label="Tutor" width="150"></el-table-column>
        <el-table-column prop="enabled" label="is Enabled" width="100"></el-table-column>
        <el-table-column width="120" label="Operations">
          <template #default="scope">
            <el-button v-if="scope.row.enabled" @click="disableCourse(scope.row.id)">disable</el-button>
            <el-button v-if="!scope.row.enabled" @click="enableCourse(scope.row.id)">enable</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "AdminCoursePage",
  data() {
    return {
      courseInfo: [],
    }
  },
  methods: {
    disableCourse(courseId) {
      this.refreshPage()
      axios.put('/api/courses/disabled/' + courseId).then(res => {
        console.log(res)
        alert('Modified Successfully!')
      }).catch(function (error) {
        console.log(error);
      });
      this.refreshPage()

    },
    enableCourse(courseId) {
      this.refreshPage()
      axios.put('/api/courses/enabled/' + courseId).then(res => {
        console.log(res)
        alert('Modified Successfully!')
      }).catch(function (error) {
        console.log(error);
      });
      this.refreshPage()
    },
    refreshPage() {
      axios.get('/api/courses').then(res => {
      this.courseInfo = res.data
      console.log(this.courseInfo)
    }).catch(function (error) {
      console.log(error);
    });
    }
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
