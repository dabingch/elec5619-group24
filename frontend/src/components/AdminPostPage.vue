<template>
  <div class="body">
    <!--    <div class="search-box">-->
    <!--      <span class="search-word">Course Name: </span>-->
    <!--      <el-input class="search-bar" placeholder="Search course name" v-model="searchWord"></el-input>-->
    <!--      <el-button @click="searchCourse">Search</el-button>-->
    <!--    </div>-->
    <div class="content-box">
      <el-table class="course-table" border stripe :data="postInfo">
        <el-table-column prop="id" label="ID" width="70"></el-table-column>
        <el-table-column prop="title" label="Title" width="100"></el-table-column>
        <el-table-column prop="tutorial" label="Tutorial" width="100"></el-table-column>
        <el-table-column prop="comment" label="Comment" width=""></el-table-column>
        <el-table-column prop="course.courseName" label="Course Name" width="120"></el-table-column>
        <el-table-column width="200" label="Operations">
          <template #default="scope">
            <el-button v-if="!scope.row.disabled" @click="disablePost(scope.row.id)">disable</el-button>
            <el-button v-if="scope.row.disabled" @click="enablePost(scope.row.id)">enable</el-button>
            <el-button @click="deletePost(scope.row.id)">delete</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "AdminPostPage",
  data() {
    return {
      postInfo: [],
    }
  },
  methods: {
    enablePost(postId) {
      this.refreshPage()
      axios.put('/api/posts/enable/' + postId).then(res => {
        console.log(res)
        alert('Modified Successfully!')
      }).catch(function (error) {
        console.log(error);
      });
      this.refreshPage()
    },
    disablePost(postId) {
      this.refreshPage()
      axios.put('/api/posts/disable/' + postId).then(res => {
        console.log(res)
        alert('Modified Successfully!')
      }).catch(function (error) {
        console.log(error);
      });
      this.refreshPage()
    },
    deletePost(postId) {
      this.refreshPage()
      axios.delete('/api/posts/' + postId).then(res => {
        console.log(res)
        alert('Modified Successfully!')
      }).catch(function (error) {
        console.log(error);
      });
      this.refreshPage()
    },
    refreshPage() {
      axios.get('/api/posts').then(res => {
        this.postInfo = res.data
        console.log(this.postInfo)
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
