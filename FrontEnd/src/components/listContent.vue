<template>
<div>
<input type="text" v-model="postBody" @change="postPost()"/>

  <div v-for='post in posts' >
    <p>{{post.email}}</p>
     <p>{{post.id}}</p>
  </div>
 </div>
</template>
<script>
import axios from 'axios'
export default {
  name: 'listCont',
  data(){
      return{
        //   posts:[
        //       {userID:2001,title:'abc@123.com'},{userID:2002,title:'aada@asd.com'}
        //   ],
        postBody: '',
        posts:[]
      };
  },
//   addP(){
//      axios.post(`http://jsonplaceholder.typicode.com/posts`,this.posts)
//     .then(response =>{console.log(response)})
//     .catch(e=>{
//           console.log(e)
//       });
//   },
  methods:{
    postPost() {
      axios.post(`https://cors.io/?http://f9dc367b.ngrok.io/`,{
        email: this.postBody,
        id:1
      })
      .then(response =>{ console.log(response)})
      .catch(e => {
        console.log('post',e.response)
      })
    }
  },
   created(){
      axios.get(`http://5b5fc5168e9f160014b88e3f.mockapi.io/login`)
      .then(response=>{
        this.posts=response.data
      })
      .catch(e=>{
        console.log('get',e)
      })
    }
}
</script>

<style>
</style>