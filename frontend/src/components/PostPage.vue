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
      <el-table class="course-table" border stripe :data="postsInfo">
        <el-table-column prop="title" label="Title" width="200"></el-table-column>
        <el-table-column prop="tutorial" label="Tutorial" width="100"></el-table-column>
        <el-table-column prop="comment" label="Comment" width=""></el-table-column>
        <el-table-column prop="course.courseName" label="Course Name" width="150"></el-table-column>
        <el-table-column width="150" label="Operations">
          <template #default="scope">
            <el-button v-if="scope.row.student.id != studentId" @click="sendApplication(scope.row.id)">Send request</el-button>
            <el-button v-if="scope.row.student.id == studentId" @click="openEdit(scope.row)">Edit request</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-dialog v-model="editDialogFormVisible" title="Edit post">
        <div class="input-div">
          <div class="input-attributes">Title: </div>
          <el-input
              autosize class="input-area"
              v-model="editTitle"
              placeholder="Please input your comment">
          </el-input>
        </div>
        <div class="input-div">
          <div class="input-attributes">Tutorial: </div>
          <el-input
              autosize class="input-area"
              v-model="editTutorial"
              placeholder="Please input your comment">
          </el-input>
        </div>
        <div class="input-div">
          <div class="input-attributes">Comment: </div>
          <el-input
              autosize class="input-area"
              v-model="editComment"
              placeholder="Please input your comment"
              type="textarea">
          </el-input>
        </div>
        <el-button class="add-button" type="primary" @click="updatePost">Submit</el-button>
      </el-dialog>
    </div>
    <div class="post-box" v-if="courseId!=-1">
      <el-button class="post-word" text @click="dialogFormVisible = true">
        Post Your New Group Here
      </el-button>
      <el-dialog v-model="dialogFormVisible" title="Add new post">
        <div class="input-div">
          <div class="input-attributes">Title: </div>
          <el-input
              autosize class="input-area"
              v-model="title"
              placeholder="Please input your comment">
          </el-input>
        </div>
        <div class="input-div">
          <div class="input-attributes">Tutorial: </div>
          <el-input
              autosize class="input-area"
              v-model="tutorial"
              placeholder="Please input your comment">
          </el-input>
        </div>
        <div class="input-div">
          <div class="input-attributes">Comment: </div>
          <el-input
              autosize class="input-area"
              v-model="comment"
              placeholder="Please input your comment"
              type="textarea">
          </el-input>
        </div>
        <el-button class="add-button" type="primary" @click="addPost">Submit</el-button>
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
      postsInfo: [],
      searchWord: '',
      dialogFormVisible: false,
      tutorial: '',
      title: '',
      comment: '',
      editPostId: -1,
      editDialogFormVisible: false,
      editTitle: '',
      editTutorial: '',
      editComment: '',
    }
  },
  props: {
    courseId: Number,
    studentId: Number,
    courseName: String,
  },
  methods: {
    addPost() {
      this.refreshPage()
      if (this.tutorial && this.title && this.comment == '') {
        alert("Please all the information that required.")
      } else {
        axios.post('/api/students/' + this.studentId + '/courses/' + this.courseId + '/posts', {
          tutorial: this.tutorial,
          comment: this.comment,
          title: this.title,
        }).then(res => {
          console.log(res)
          alert("Submit successfully!")
        }).catch(function (error) {
          console.log(error);
        });
        this.tutorial = ''
        this.title = ''
        this.comment = ''
        this.dialogFormVisible = false
      }
      this.refreshPage()
    },
    sendApplication(postId) {
      axios.get('/api/students/' + this.studentId + '/posts/' + postId + '/apply').then(res => {
        console.log(res)
        alert("Send successfully!")
      }).catch(function (error) {
        console.log(error);
      });
    },
    openEdit(post) {
      this.editPostId = post.id
      this.editTitle = post.title
      this.editTutorial = post.tutorial
      this.editComment = post.comment
      this.editDialogFormVisible = true
    },
    updatePost() {
      this.refreshPage()
      axios.put('/api/posts/' + this.editPostId, {
        title: this.editTitle,
        tutorial: this.editTutorial,
        comment: this.editComment
      }).then(res => {
        console.log(res)
        alert("Update successfully!")
        this.editDialogFormVisible = false
      }).catch(function (error) {
        console.log(error);
      });
      this.refreshPage()
    },
    refreshPage() {
      if (this.courseId == -1) {
        axios.get('/api/posts').then(res => {
          this.postsInfo = res.data
          console.log(this.postsInfo)
        }).catch(function (error) {
          console.log(error);
        });
      } else {
        axios.get('/api/courses/' + this.courseId + '/posts').then(res => {
          this.postsInfo = res.data
          console.log(this.postsInfo)
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
