<template>
  <div class="body">
    <div class="header">
      <div class="title-div">
        <span>HD</span>
        <span>Helper</span>
      </div>
      <div class="welcome-div">
        <span class="welcome-word">Hi, {{ studentName }} </span>
        <router-link style="width: 100px" replace to="/" >
          <el-link class="welcome-word">Sign out</el-link>
        </router-link>
      </div>
    </div>
    <div class="selector-div">
      <el-menu class="switch-div" :default-active=pageNum @select="switchPage">
        <el-menu-item class="menu-item" index="1">
          <span class="menu-item-word">User Info</span>
        </el-menu-item>
        <el-menu-item class="menu-item" index="2">
          <span class="menu-item-word">Course</span>
        </el-menu-item>
        <el-menu-item class="menu-item" index="3">
          <span class="menu-item-word">Post</span>
        </el-menu-item>
        <el-menu-item class="menu-item" index="4">
          <span class="menu-item-word">Comments</span>
        </el-menu-item>
      </el-menu>
      <div class="content-div">
        <welcome-page v-if="pageNum==0"></welcome-page>
        <UserInfoPage v-if="pageNum==1" :student="student" @firstName="updateName"></UserInfoPage>
        <CoursePage v-if="pageNum==2" @comments="switchComments" @posts="switchPosts"></CoursePage>
        <PostPage v-if="pageNum==3" :course-id="postCourseId" :course-name="postCourseName" :student-id="student.id"></PostPage>
        <CommentsPage v-if="pageNum==4" :course-id="commentCourseId" :course-name="commentCourseName" :student-id="student.id"></CommentsPage>
      </div>
    </div>
  </div>
</template>

<script>
import UserInfoPage from "@/components/UserInfoPage";
import CoursePage from "@/components/CoursePage";
import PostPage from "@/components/PostPage";
import CommentsPage from "@/components/CommentsPage";
import WelcomePage from "@/components/WelcomePage";
import axios from "axios";

export default {
  name: "HomePage",
  components: {
    WelcomePage,
    UserInfoPage,
    PostPage,
    CommentsPage,
    CoursePage
  },
  data() {
    return {
      student: {},
      pageNum: "0",
      commentCourseId: -1,
      commentCourseName: '',
      postCourseId: -1,
      postCourseName: '',
      studentName: ''
    }
  },
  methods: {
    switchPage(key) {
      this.pageNum = key
    },
    switchComments(courseId) {
      this.commentCourseId = courseId.id
      this.commentCourseName = courseId.courseName
      this.pageNum = '4'
    },
    switchPosts(courseId) {
      this.postCourseId = courseId.id
      this.postCourseName = courseId.courseName
      this.pageNum = '3'
    },
    updateName(firstName) {
      this.studentName = firstName
    }
  },
  mounted() {
    this.student = axios.get('/api/students/' + this.$route.query.id,).then(res => {
      this.student = res.data
      this.studentName = this.student.firstName
    }).catch(function (error) {
      console.log(error)
    })
  }
}
</script>

<style scoped>
.body {
  display: flex;
  flex-direction: column;
  padding: 20px;
}
.header {
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: start;
  justify-content: space-between;
}
.title-div {
  display: flex;
  flex-direction: column;
  border: 1px solid black;
  width: 260px;
  font-size: 72px;
  font-weight: bold;
  font-family: Arial;
  margin-left: 50px;
}
.welcome-div {
  display: flex;
  flex-direction: column;
  align-items: end;
  margin-right: 60px;
}
.welcome-word {
  font-size: 24px;
}
.selector-div {
  display: flex;
  width: 100%;
  height: 500px;
  margin-top: 20px;
  flex-direction: row;
  align-items: center;
}
.switch-div {
  width: 200px;
  height: 400px;
  margin-left: 80px;
  border-top: 1px  solid black;
  border-left: 1px  solid black;
  border-right: 1px  solid black;
}
.menu-item {
  display: flex;
  width: 100%;
  height: 25%;
  justify-content: center;
  border-bottom: 1px  solid black;
}
.menu-item {
  display: flex;
  width: 100%;
  height: 25%;
  justify-content: center;
  border-bottom: 1px  solid black;
}
.menu-item-word {
  font-size: 24px;
  font-weight: bold;
}
.content-div {
  width: 70%;
  height: 90%;
  margin-left: 30px;
}
</style>
