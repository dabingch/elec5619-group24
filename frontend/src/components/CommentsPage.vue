<template>
  <div class="body">
<!--    <div class="search-box">-->
<!--      <span class="search-word">Course Name: </span>-->
<!--      <el-input class="search-bar" placeholder="Search course name" v-model="searchWord"></el-input>-->
<!--      <el-button @click="searchCourse">Search</el-button>-->
<!--    </div>-->
    <div class="search-box">
      {{courseName}}
    </div>
    <div class="content-box">
      <el-table class="course-table" border stripe :data="commentsInfo">
        <el-table-column prop="courseComment" label="Course Comment" ></el-table-column>
        <el-table-column prop="lecturerComment" label="Lecturer Comment"></el-table-column>
        <el-table-column prop="tutorComment" label="Tutor Comment"></el-table-column>
        <el-table-column width="140" label="Rating">
          <template #default="scope">
            <el-rate disabled v-model="scope.row.rating"></el-rate>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="post-box" v-if="courseId!=-1">
      <el-button class="post-word" text @click="dialogFormVisible = true">
        Post Your New Comment Here
      </el-button>
      <el-dialog v-model="dialogFormVisible" title="Add new comment">
        <div class="input-div">
          <div class="input-attributes">Course Comment: </div>
          <el-input
              autosize class="input-area"
              v-model="courseComment"
              placeholder="Please input your comment"
              type="textarea">
          </el-input>
        </div>
        <div class="input-div">
          <div class="input-attributes">Lecturer Comment: </div>
          <el-input
              autosize class="input-area"
              v-model="lecturerComment"
              placeholder="Please input your comment"
              type="textarea">
          </el-input>
        </div>
        <div class="input-div">
          <div class="input-attributes">Tutor Comment: </div>
          <el-input
              autosize class="input-area"
              v-model="tutorComment"
              placeholder="Please input your comment"
              type="textarea">
          </el-input>
        </div>
        <el-rate v-model="rating"></el-rate>
        <el-button class="add-button" type="primary" @click="addComment">Submit</el-button>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "CommentsPage",
  data() {
    return {
      commentsInfo: [],
      searchWord: '',
      dialogFormVisible: false,
      courseComment: '',
      lecturerComment: '',
      tutorComment: '',
      rating: 0,
    }
  },
  props: {
    courseId: Number,
    studentId: Number,
    courseName: String,
  },
  methods: {
    // searchCourse() {
    //   if (this.searchWord != '') {
    //     axios.get('/api/courses/search?keyword=' + this.searchWord).then(res => {
    //       this.commentsInfo = res.data
    //       console.log(res)
    //     }).catch(function (error) {
    //       console.log(error);
    //     });
    //   } else {
    //     axios.get('/api/courses/enabled').then(res => {
    //       this.commentsInfo = res.data
    //       console.log(this.commentsInfo)
    //     }).catch(function (error) {
    //       console.log(error);
    //     });
    //   }
    // },
    addComment() {
      this.refreshPage()
      if (this.courseComment && this.lecturerComment && this.tutorComment == '') {
        alert("Please all the information that required.")
      } else {
        axios.post('/api/courses/' + this.courseId + '/students/' + this.studentId + '/comments', {
          courseComment: this.courseComment,
          tutorComment: this.tutorComment,
          lecturerComment: this.lecturerComment,
          rating: this.rating
        }).then(res => {
          console.log(res)
          alert("Submit successfully!")
        }).catch(function (error) {
          console.log(error);
        });
        this.courseComment = ''
        this.lecturerComment = ''
        this.tutorComment = ''
        this.rating = 0
        this.dialogFormVisible = false
      }
      this.refreshPage()
    },
    refreshPage() {
      if (this.courseId == -1) {
        axios.get('/api/comments').then(res => {
          this.commentsInfo = res.data
          console.log(this.commentsInfo)
        }).catch(function (error) {
          console.log(error);
        });
      } else {
        axios.get('/api/courses/' + this.courseId + '/comments').then(res => {
          this.commentsInfo = res.data
          console.log(this.commentsInfo)
        }).catch(function (error) {
          console.log(error);
        });
      }
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
</style>
