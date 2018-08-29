<template>
<div>
<ArticleAdd ></ArticleAdd>
 <!--<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
    Open modal
  </button>-->
   <TakeAtContent v-bind:dataAtt='dataAtt' v-bind:idCont='idCont'></TakeAtContent>
<EditContent v-bind:dataContent='dataContent' v-bind:showEdit='showEdit'></EditContent>
 <!--<TakeAtContent v-bind:dataAtt='dataAtt'></TakeAtContent>-->
<div v-for='(arti,index) in listArticles' :key="index" class='list_articles'>
<div class="row">
  <div class="col-xs-6 col-sm-4 col-md-4">
    <div class="profile profile-sm">
    <button class="btn btn-default btn-lg"><i class="fa fa-circle-o-notch fa-spin"></i>{{arti.status}}</button>
      <div class="profile-cont">
        <div class="col-sm-6 col-md-3">
           <img :src="arti.avatar" alt="" class="img-rounded img-responsive" />
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
        
              <a class='btn-atdance'  data-toggle="modal" data-target="#exampleAtt"  @click='attClick(index,arti.id)'>Take attendance</a>
             
        </div>
    </div> <!-- end profile profile-sm -->
     
  </div> <!-- end col-md-4 -->
  <div class="col-xs-6 col-md-8 main-content">
    <div class="cont-articles">
      <div class="dropdown">
        <button type="button" class="btn dropdown-toggle" data-toggle="dropdown">
           ...
        </button>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="#showAddYN" >Add Event</a>
            <a class="dropdown-item"  data-toggle="modal" data-target="#exampleModal" @click='editContent(index,arti.id)'>Edit</a>
            <a class="dropdown-item" @click="deleteContent(index, arti.id)">Delete</a>
          </div>
        </div>
      </div> <!-- end cont-articles -->
    <div class="boxConfirm">
      <div class="boxConfirm--deleteContent boxDefault container-fluid-width">
      </div>
    </div>
    <h3>{{arti.contentName}}</h3>
    <span class="main__tittleLine"></span>
    
    <div id="contentPragraph" v-bind:class='{show:!arti.checkAll}' >
      <span  class='a111' v-html='arti.contentDescription'></span>
    </div>
    
   <button class="btn btn-default btn-viewa" v-on:click='arti.checkAll=!arti.checkAll' v-show='arti.checkAll'> ...View all</button>
    <button class="btn btn-default btn-viewa" v-on:click='arti.checkAll=!arti.checkAll' v-show='!arti.checkAll'> ..Hide</button>
    </div>
    
    </div>
    </div>
  <!--  <EditContent></EditContent>-->
  </div>
  
  </div>
</template>

<script>
import axios from 'axios'
import ArticleAdd from './ArticleAdd'
import EditContent from './EditContent'
import TakeAtContent from './TakeAtContent'
export default {
  name: 'articlelist',
  components:{
    ArticleAdd,EditContent,TakeAtContent,
  },
  data(){
    return{
      listArticles:[],
      showContent:false,
      dataContent:[],
      listUser:[],
      showEdit:false,
      dataAtt:[],
      showTake:false,
      idCont:'',
     // idtoken:JSON.parse(localStorage.getItem("token")),
    };
  },
  methods:{
    deleteContent(index,id){
      var idtoken=JSON.parse(localStorage.getItem("token"));
      var role=JSON.parse(localStorage.getItem("role"));
      var userLogin=JSON.parse(localStorage.getItem("user"));
        // axios.get(`http://192.168.1.2:8082/getIdEmailUser\/${idtoken}\/`)
        // .then(response=>{
        //   this.listUser=response.data;
        //   console.log('rse',this.listUser);
        // })       why su dung o day  user laij null ???
        // .catch(e=>{console.log(e)});
      var emailContent;
       //   console.log('user',this.listUser);
          console.log('userxxx',this.listArticles[index].id);
          for(var i=0;i<this.listUser.length;i++){
            if(this.listUser[i].id==this.listArticles[index].id){
              emailContent=this.listUser[i].email;
            //  emailContent=this.listArticles[index].email;
            //  console.log('aaa',emailContent);
            }
          }
        // console.log(response.data);
      if(role=='captain' || emailContent==userLogin.U3){
        this.listArticles.splice(index,1);
        axios.get(`http://192.168.1.2:8082/deleteContent/${id}\/${idtoken}\/`)
        .then(response=>{console.log(id)})
        .catch(e=>{console.log(e)});
        alert('xoa thanh cong');
      }else{
        alert('ban ko co quyen');
      }

    },
    editContent(index,id){

      var idtoken=JSON.parse(localStorage.getItem("token"));
      var role=JSON.parse(localStorage.getItem("role"));
      var userLogin=JSON.parse(localStorage.getItem("user"));
      
      var emailContent;
          console.log('userxxx',this.listArticles[index].id);
          for(var i=0;i<this.listUser.length;i++){
            if(this.listUser[i].id==this.listArticles[index].id){
              emailContent=this.listUser[i].email;
            
            }
          }
    
      if(role=='captain' || emailContent==userLogin.U3){
        // alert('edit');
        this.dataContent=this.listArticles[index];
     //   console.log(this.dataContent)
        this.showEdit=true;
      
       
      }
      else{
         this.showEdit=false;
        alert('ban ko co quyen');
        this.dataContent=[];
       
      }
    //  console.log('dataedit',this.listArticles);
      // localStorage.setItem('dataEdit',JSON.stringify(data));
      // if(!data){
      //   alert('datako tontai');
      // }
   // console.log('data-prop',this.data);
    },
    attClick(index,id){
        console.log('attclick id',this.listArticles[index].id)
        this.idCont = this.listArticles[index].id;
          console.log('idCont',this.idCont);
          var id=this.idCont;
           var idtoken = JSON.parse(localStorage.getItem("token"));
           axios.get(`http://192.168.1.2:8082/getListAttendance/${id}\/${idtoken}\/`).then(response => {
             
                   this.dataAtt = response.data;
    // //     //         console.log(response.data);
    // //     //         console.log('lsitmemeber att', this.listMemberAtt);
           console.log('daataATt', this.dataAtt);
               })
              .catch(e => {
                  console.log('getdata articlelist', e.response)
               });
          //      this.idCont = this.listArticles[index].id;
      //  this.dataAtt=this.listArticles[index];
      //  this.idCont=this.listArticles[index].id;

       // this.showEdit=true;
      // console.log(this.showTake);
      // console.log('data ben list khi attClick',this.dataAtt);
      //  console.log('id cont',this.idCont);
    //  }
    //  else{
    //    alert('ban ko co quyen');
      //  this.dataAtt=[];
      //  this.showEdit=false;
   //   }
    }


  },
  created() {
    this.showTake=false;
    var idtoken=JSON.parse(localStorage.getItem("token"));
    axios.get(`http://192.168.1.2:8082/listContent/${idtoken}/`).then(response=>{
		  // this.listContents = response.body.data;
			 response.data.forEach(element => {
                element.checkAll = true;
                element.checkEdit = true;
                this.content = element;
                this.listArticles.push(element);
            });
      //  console.log(response.data);
		//	element.checkAll=true;
			// this.listArticles=response.data;
     // console.log(idtoken);
     // console.log(this.listArticles);
     
     
    })
      axios.get(`http://192.168.1.2:8082/getIdEmailUser\/${idtoken}\/`)
        .then(response=>{
          this.listUser=response.data;
        //  console.log('rse',this.listUser);
        });

      

  },
  mounted() {
     // this.dataGet = JSON.parse(localStorage.getItem('dataEdit'));
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
    width: 50px;
    height:50px;
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
button.btn.btn-default.btn-viewa {
    position: absolute;
    bottom: 0;
    right: 12px;
    background: transparent;
    color:#34ace0;
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
</style>
