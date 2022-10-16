<template>
  <div class="body">
    <div class="search-box">
      <span class="search-word">Course Name: </span>
      <el-input class="search-bar" placeholder="Search course name" v-model="searchWord"></el-input>
      <el-button @click="searchCourse">Search</el-button>
    </div>
    <div class="content-box">
      <el-table class="course-table" border stripe :data="courseInfo">
        <el-table-column prop="courseName" label="Course Name" ></el-table-column>
        <el-table-column prop="lecturer.firstName" label="Lecturer"></el-table-column>
        <el-table-column prop="tutor.firstName" label="Tutor"></el-table-column>
        <el-table-column label="Operations" width="180">
          <template #default="scope">
            <el-button text class="operation-word" @click="commentCLick(scope.$index, scope.row)">
              Rate
            </el-button>
            <el-button text class="operation-word" @click="postClick(scope.$index, scope.row)">
              Group
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="post-box">
      <el-button class="post-word" text @click="dialogFormVisible = true">
        Post Your New Course Here
      </el-button>
      <el-dialog v-model="dialogFormVisible" title="Add new course">
        <div class="input-div">
          <div class="input-attributes">Course Name: </div>
          <el-input class="input-area" v-model="newCourseName"></el-input>
        </div>
        <div class="input-div">
          <div class="input-attributes">Lecturer First Name: </div>
          <el-input class="input-area" v-model="newLecturerFirstName"></el-input>
        </div>
        <div class="input-div">
          <div class="input-attributes">Lecturer Last Name: </div>
          <el-input class="input-area" v-model="newLecturerLastName"></el-input>
        </div>
        <div class="input-div">
          <div class="input-attributes">Tutor First Name: </div>
          <el-input class="input-area" v-model="newTutorFirstName"></el-input>
        </div>
        <div class="input-div">
          <div class="input-attributes">Tutor Last Name: </div>
          <el-input class="input-area" v-model="newTutorLastName"></el-input>
        </div>
        <el-button class="add-button" type="primary" @click="applyCourse">ADD</el-button>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "CoursePage",
  data() {
    return {
      courseInfo: [],
      searchWord: '',
      dialogFormVisible: false,
      newCourseName: '',
      newLecturerFirstName: '',
      newLecturerLastName: '',
      newTutorFirstName: '',
      newTutorLastName: '',
      isLecturer: false,
      isTutor: false,
      lectureId: -1,
      tutorId: -1,
    }
  },
  methods: {
    searchCourse() {
      if (this.searchWord != '') {
        axios.get('/api/courses/search?keyword=' + this.searchWord).then(res => {
          this.courseInfo = res.data
          console.log(res)
        }).catch(function (error) {
          console.log(error);
        });
      } else {
        axios.get('/api/courses/enabled').then(res => {
          this.courseInfo = res.data
          console.log(this.courseInfo)
        }).catch(function (error) {
          console.log(error);
        });
      }
    },
    applyCourse() {
      if (this.newCourseName && this.newLecturerFirstName &&
          this.newTutorFirstName && this.newLecturerLastName && this.newTutorLastName != '') {
        axios.get('/api/lecturers?firstName=' + this.newLecturerFirstName + '&lastName=' + this.newLecturerLastName)
            .then(res => {
              console.log(res)
              this.isLecturer = true
              this.lectureId = res.data[0].id
              axios.get('/api/tutors?firstName=' + this.newTutorFirstName + '&lastName=' + this.newTutorLastName)
                  .then(res => {
                    console.log(res)
                    this.isTutor = true
                    this.tutorId = res.data[0].id
                    if (this.isLecturer && this.isTutor) {
                      axios.post('/api/courses/lecturer/' + this.lectureId + '/tutor/' + this.tutorId, {
                        'courseName': this.newCourseName
                      }).then(res => {
                        console.log(res)
                        alert("Add successfully!")
                      }).catch(function (error) {
                        alert('The course is already added')
                        console.log(error);
                      });
                      this.newCourseName = ''
                      this.newLecturerFirstName = ''
                      this.newLecturerLastName = ''
                      this.newTutorFirstName = ''
                      this.newTutorLastName = ''
                      this.dialogFormVisible = false
                      this.isLecturer = false
                      this.isTutor = false
                    } else {
                      alert("Tutor or lecturer is not exist")
                    }
                  }).catch(function (error) {
                console.log(error);
              });
        }).catch(function (error) {
          console.log(error);
        });
      } else {
        alert("Please input all the information required.")
      }
      this.refreshPage()
    },
    commentCLick(index, courseId) {
      this.$emit('comments', courseId)
    },
    postClick(index, courseId) {
      this.$emit('posts', courseId)
    },
    refreshPage() {
      axios.get('/api/courses/enabled').then(res => {
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
}
.search-word {
  width: 20%;
  font-weight: bold;
}
.search-bar {
  width: 35%;
  margin-right: 10px;
}
.content-box {
  margin-top: 20px;
  width: 100%;
  height: 80%;
  background-color: red;
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
.operation-word {
  color: #3389FF;
  text-decoration: underline;
}
</style>
