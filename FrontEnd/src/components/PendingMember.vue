<template>
<div class="member-content">
   <div class='row' >
       <!--  <div  >-->
            <div class="col-md-6" v-for='(member,index) in listMemberPen'>  
                <div class="row member-imformation">
                    <div class=" col-md-3 col-lg-3">
                        <div class='member-img'>
                            <img :src="member.avatar" alt="">
                        </div>
                    </div>
                    <div class=" col-md-9 col-lg-9">
                        <div class="member-info">
                            <h5 class='memberName'>{{member.userName}}</h5>
                            <span class='memberDate'>JoinedDate :{{member.joinedDate}}</span>
                            <p class='padd'>Gmail: <span style='font-weight:300;'>{{member.email}}</span></p>
                            <p></p>
                        </div> <!-- end member-info -->
                    </div>
                    <div class="dropdown">
                        <button type="button" class="btn dropdown-toggle" data-toggle="dropdown">
                        ...
                        </button>
                        <div class="dropdown-menu">
                           
                            <a class="dropdown-item"  data-toggle="modal" data-target="#exampleModal" @click='addMemberPend(index,member.email)'>Add Member</a>
                            <a class="dropdown-item" @click="deleteMember(index,member.email)">Delete</a>
                        </div>
                    </div>
                </div> <!-- end member-imformation -->
            </div>
    <!--    </div>-->
    </div>
</div>
</template>
<script>
import axios from 'axios'
export default {
  name: 'pendingMember',
  data(){
      return{
          listMemberPen:[],
      }
  },
  methods:{
     deleteMember(index,email){
        var idtoken=JSON.parse(localStorage.getItem("token"));
    //    console.log();
        var role=JSON.parse(localStorage.getItem("role"));
          if(role=='captain'){
        this.listMemberPen.splice(index,1);

        axios.post(`http://192.168.1.2:8082/deleteMemberPendingItem/${email}\/${idtoken}\/`)
        .then(response=>{
            console.log(email);
            alert('xoa thanh cong')
        })
        .catch(e=>{console.log(e)});
          }else{
              alert('ban khong co quyen delete')
          }

     },
     addMemberPend(index,email){
          var idtoken=JSON.parse(localStorage.getItem("token"));
           var role=JSON.parse(localStorage.getItem("role"));
        // console.log('deletemeber setcap:',role);
         if(role=='captain'){
           this.listMemberPen.splice(index,1);
            axios.post(`http://192.168.1.2:8082/acessMemberFromPendingItem/${email}\/${idtoken}\/`)
        .then(response=>{console.log(email); alert('add sucess')})
        .catch(e=>{console.log(e)});
         }
         else{
             alert('ban ko quyen add')
         }
     }
 },
created () {
       var idtoken=JSON.parse(localStorage.getItem("token"));
    axios.get(`http://192.168.1.2:8082/memberPendingItem/${idtoken}\/`).then(response=>{
		//   this.listContents = data.body.data;
			 this.listMemberPen = response.data;
        //     console.log(response.data);
         console.log('member pen',this.listMemberPen);
                
            })
      //  console.log(response.data);
		//	element.checkAll=true;
			// this.listArticles=response.data;
     // console.log(idtoken);
     //  console.log(this.listArticles);

    },
  
}
</script>
<style>
.member-content{
    margin-top:30px;
}
.member-imformation .listmeber{
    padding:5px 5px;
}
.member-imformation .member-info{
    font-size:14px;
}
.member-imformation .member-info p{
    margin-bottom:0;
}
.member-imformation h5{
    margin-bottom:0;
}
.member-imformation p.padd{
    margin-top:5px;
}
.member-imformation .member-img{
    border:gray solid 1px;
    width: 98px;
    min-height: 98px;
    padding:3px;
    box-shadow: 0px 0px 5px 0px #a9a9a9;
}
.row.member-imformation{
    padding:8px 0px;
    position: relative;
}
.member-imformation .member-img img{
    width:100%;
}
.member-imformation .dropdown{
    position:absolute;
    right:0;

}
.member-imformation .dropdown-toggle:after{
    display:none;
}
.member-imformation button.btn.dropdown-toggle{
    background: transparent;
}
.member-imformation  .btn.focus,.btn:focus{
    box-shadow:none;
}
</style>
