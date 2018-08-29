<template>
<div class='pending'>
  <ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#home">Content</a></li>
    <li><a data-toggle="tab" href="#menu1">Member</a></li>
  </ul>
<div class="tab-content">
  <div id="home" class="tab-pane fade in active">
    <div v-for='(arti,index) in listContentPen' :key="index" class='list_articles'>
      <div class="row">
        <div class="col-xs-6 col-sm-4 col-md-4">
          <div class="profile profile-sm">
  <!--  <button class="btn btn-default btn-lg"><i class="fa fa-circle-o-notch fa-spin"></i>{{arti.status}}</button>-->
            <div class="profile-cont">
              <div class="col-sm-6 col-md-3">
                 <img src="../assets/img/ca-user.png" alt="" class="img-rounded img-responsive" />
                          </div>
                  <div class="col-sm-6 col-md-9">
                    <h4>{{arti.userName}}</h4>
                    <small>
                    <cite title="San Francisco, USA">San Francisco, USA {{index}}<i class="fas fa-map-marker-alt">
                    </i></cite>
                    </small>
                    <p><span>Start:&nbsp</span>{{arti.startDate}}</p>
                    <p><span>End:&nbsp</span>{{arti.endDate}}</p>
                    <p><span>Tags:&nbsp</span>Default</p>
                    <p><span>Level:&nbsp</span>{{arti.level}}</p>
                    <div class="main__contentFL">
                      <i class="fas fa-heart"></i>
                      <span>100</span>
                                
                    </div>
                  </div> <!-- end col-md-9 -->
                  
                </div> <!--end profile-cont-->
                <div class='btn-att'>
                          <a class='btn-atdance' href="#">Take attendance</a>
                    </div>
                </div> <!-- end profile profile-sm -->
     
              </div> <!-- end col-md-4 -->
            <div class="col-xs-6 col-md-8 main-content">
             <!--< <div class="cont-articles">
                <div class="dropdown">
                  <button type="button" class="btn dropdown-toggle" data-toggle="dropdown">
                    ...
                  </button>
                    <div class="dropdown-menu">
                      <a class="dropdown-item" href="#showAddYN">Add Event</a>
                      <a class="dropdown-item"  data-toggle="modal" data-target="#exampleModal" @click='editContent(index,arti.id)'>Edit</a>
                      <a class="dropdown-item" @click="deleteContent(index, arti.id)">Delete</a>
                    </div>
                  </div>
                 </div><!--end cont-articles -->
                  
                 
          <div class="boxConfirm">
            <div class="boxConfirm--deleteContent boxDefault container-fluid-width">
            </div>
            </div>
            <h3>{{arti.contentName}}</h3>
            <span class="main__tittleLine"></span>
            
            <div id="contentPragraph" v-bind:class='{show:!arti.checkAll}' >
              <span  class='a111' v-html='arti.contentDescription'></span>
            </div>
    
        <button class="btn btn-default btn-viewa1" v-on:click='addContentPen(index,arti.id)'>Approve</button>
          <button class="btn btn-default btn-viewa2" v-on:click='deleteContentPen(index,arti.id)'>Cancel</button>
          <button class="btn btn-default btn-viewb" v-on:click='arti.checkAll=!arti.checkAll' v-show='arti.checkAll'> ...View all</button>
          <button class="btn btn-default btn-viewb" v-on:click='arti.checkAll=!arti.checkAll' v-show='!arti.checkAll'> ..Hide</button>
          </div>
    
    </div>
  </div>
  </div>
  <div id="menu1" class="tab-pane fade">
    <PendingMember></PendingMember>
  </div>
  </div> <!--end tab-content-->
</div>
</div>
</template>
<script>
import axios from 'axios'
import PendingMember from './PendingMember'
export default {
  name: 'pending',
  components:{
    PendingMember
  },
  data(){
    return{
       listContentPen:[],
    }
  },
  methods:{
      deleteContentPen(index,id){
        var role=JSON.parse(localStorage.getItem("role"));
        if(role=='captain'){
        var idtoken=JSON.parse(localStorage.getItem("token"));
        this.listContentPen.splice(index,1);
        axios.post(`http://192.168.1.2:8082/deleteContentPendingItem/${id}\/${idtoken}\/`)
        .then(response=>{
          console.log(id);
          alert('xoa thanh cong')
          })
        .catch(e=>{console.log(e)});
        }else{
          alert('ban ko co quyen xoa')
        }

    },
     addContentPen(index,id){
      var role=JSON.parse(localStorage.getItem("role"));
        if(role=='captain'){
          
        var idtoken=JSON.parse(localStorage.getItem("token"));
      
          this.listContentPen.splice(index,1);
        axios.post(`http://192.168.1.2:8082/acessContentFromPendingItem/${id}\/${idtoken}\/`)
        .then(response=>{
          console.log(id);
          alert('add thanh cong');
          })
        .catch(e=>{console.log(e)});
        }else{
            alert('ban khong co quyen add');
        }

    }
  },
  created() {
    var idtoken=JSON.parse(localStorage.getItem("token"));
    axios.get(`http://192.168.1.2:8082/contentPendingItem/${idtoken}/`).then(response=>{
		//   this.listContents = data.body.data;
			 response.data.forEach(element => {
                element.checkAll = true;
                element.checkEdit = true;
                this.content = element;
                this.listContentPen.push(element);
            })
     
    });
  },
}
</script>
<style>
.btn-att .btn-atdance{
background: #ff5c00;
    color: #ffffff !important;
    display: inline-block;
}
.list_articles {
    margin-top: 30px;
    margin-bottom:10px;
    /* border-radius: 4px; */
}
.profile .img-rounded{
    width: 100%;
}
.profile.profile-sm {
    background: url(../assets/img/teaching.jpg);
}
.profile.profile-sm button{
position: absolute;
top: 14px;
left: -15px;
width: 110px;
height: 40px;
display: inline-block;
background: #dfe6ec;
padding: 5px 10px;
border: 1px solid #fff;
box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
color: #1d2124;
text-align: center;
border-radius: 2px
}
.profile .profile-cont {
    display: flex;
    padding-top: 30%;
    color: white;
  /*  background-color: #5a5a5880;*/
}
.profile .profile-cont h4{
  color:white;
}
.cont-articles {
  height:30px;
}
.cont-articles .dropdown{
  position:absolute;
  top:0;
  right:0;
}
.cont-articles .dropdown .btn{
  padding:0;
    background: transparent;
    font-size: 20px;

}
.cont-articles .dropdown-toggle::after{
  border:0;
}
.col-xs-6.col-md-8.main_content {
    border: gray solid 1px;
}
.main-content{
    border: 1px solid #34ace0;
    border-radius: 5px;
}
.main-content h3{
    color: #34ace0;
    position: relative;
    display: -webkit-box;
    -webkit-line-clamp: 1;
    overflow: hidden;
    -webkit-box-orient: vertical;
}
.main-content h3:hover{
  display:block;
}
.main__tittleLine {
    display: block;
    width: 100px;
    border-bottom: 1px solid #34ace0;
    margin-bottom: 10px;
    margin-left: -15px;
}
.main-content h3::before {
  /*  content: "";*/
    display: block;
    width: 100px;
    border-bottom: 2px solid #34ace0;
    position: absolute;
    bottom: -10px;
    left: 0;
    z-index: 1;
}
#contentPragraph{
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    text-align: justify;
    overflow:hidden;
    padding-top:15px;
    padding-left:16px;
    margin-bottom: 15px;
}
button.btn.btn-default.btn-viewa1 {
    position: absolute;
    bottom: 0;
  /*  right: 12px;*/
  left:70px;
    background: transparent;
    color:#34ace0;
}
button.btn.btn-default.btn-viewa2 {
    position: absolute;
    bottom: 0;
    right: 12px;
    background: transparent;
    color:#34ace0;
}
button.btn.btn-default.btn-viewb{
    background: transparent;
    color: #34ace0;
}
.btn-att {
    padding: 7px;
    text-align: center;
}
.btn-att .btn-atdance{
     background: transparent;
    color: #ffffff !important;
    display: inline-block;
    font-size: 15px;
    font-weight: 500;
    height: 50px;
    line-height: 0.8;
    padding: 18px 30px;
    border: white solid 1px;
}
.btn-att .btn-atdance a:hover{
      text-decoration: none;
}
.show{
  display:block !important;
}
.pending{
  margin-top:30px;
}
</style>
