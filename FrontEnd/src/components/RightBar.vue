<template>
<div style="padding-top:30px;">   
    <div class="alert alert-warning alert-dismissible" id='alert' v-show='showAlert'>
    <button type="button" class="close" data-dismiss="alert" v-on:click='myFunction'>&times;</button>
    <strong></strong>{{messAlert}}
  </div>
     <div class="rightbar-member">
        <form  class='d-flex'>
            <div class="form-group">
                <input type="text" class="form-control" v-model='searchEmail' 
                @input='onChange' 

                >
                
            </div>
            <button  class="btn addMember" @click.prevent='addMember'>
                <i class="fas fa-user-plus"></i>
            </button>
           
        </form>
   
            <div class='searchEmail' v-show='isOpen'>
                <div class='searchShow' v-for='(item,index) in filterEmail' v-bind:value="item">
                    <a @click='onClick(item)'>{{item}}</a>
                </div>
            </div>
    </div>
    <div class="main__classInformations">
        <p class="inforTittle">Class's Informations</p>
        <span class="inforLine"></span>
        <p class="times">
            <i class="fas fa-clock"></i>
            <span>17-06-1994</span>
            <span> - </span>
            <span>now</span>
        </p>
        <p class="captain">
            <i class="fas fa-star"></i>
            <span>Captain :</span>
            <span>Captain American</span>
        </p>
        <p class="mems">
            <i class="fas fa-users"></i>
            <span>Members :</span>
            <span>100</span>
        </p>
        <p class="events">
            <i class="fas fa-calendar-alt"></i>
            <span>Events :</span>
            <span>Unlimit</span>
        </p>
    </div>
        <div class="main__classLinks">
            <p class="linkTittle">Important Links</p>
            <span class="linkLine"></span>
            <a class="times" href="#">
                <i class="fab fa-skype"></i>
                <span>Skype</span>
            </a>
            <a class="mentor" href="#">
                <i class="fab fa-facebook-messenger"></i>
                <span>Messenger</span>
            </a>
            <a class="mentor" href="#">
                <i class="fab fa-google-plus-g"></i>
                <span>Google +</span>
            </a>
        </div>
    </div>
</div>
</template>
<script>
import axios from 'axios'
export default {
  name: 'rightbar',
  data(){
      return{
          searchEmail:'',
          listMember:[],
          listEmailUser:[],
          isOpen:false,
          showAlert:false,
          messAlert:'',
          userInClass:0,
          userOutClass:0

      }
  },
  methods:{
      onChange(){
          if(this.searchEmail==''){
              this.isOpen=false;
          }else{
               this.isOpen=true;
          }
         
      },
      onClick(item){
          this.searchEmail=item
      },
      addMember(){
        //  console.log(this.listMember);
        //  console.log(this.listEmailUser[0]);
        console.log('this.searchEmail',this.searchEmail);
        for(var i=0;i<this.listEmailUser.length;i++){
            if(this.searchEmail === this.listEmailUser[i]){
                  console.log('co trong list');
                       this.userOutClass++;
                
                for(var j = 0;j<this.listMember.length;j++){
                    if(this.searchEmail !== this.listMember[j].email){
                        this.userInClass = 0;
                        console.log('add trung member')
                    }

                    if(this.searchEmail == this.listMember[j].email){
                         this.userInClass++;
                         console.log('trung member')
                         break;
                       
                    }

                }
                if(this.userInClass==0){
                    var emailp=this.searchEmail;
                    console.log('aaa');
                    var idtoken=JSON.parse(localStorage.getItem("token"));
                    axios.post(`http://192.168.1.2:8082/addMember/${emailp}\/${idtoken}\/`)
                    .then(response =>{console.log('addmmebr success')})
                    .catch(e => {
                        console.log('addmeber err',e.response)
                    });
                    this.searchEmail='';
                    
                }
                break;
            }
            if(this.searchEmail !== this.listEmailUser[i]){
            this.userOutClass = 0;
            } 


        }
            if(this.userOutClass != 0){
                if (this.userInClass >= 1) {
                    this.messAlert='Thành viên đã là member';
                    this.showAlert=true;
           
                }
                if(this.userInClass == 0) {
                     var role=JSON.parse(localStorage.getItem("role"));
                    if(role=='captain'){
                        this.messAlert='Add member success';
                        this.showAlert=true;
                    }else{
                        this.messAlert='Chuyển sang pending ';
                        this.showAlert=true;
                    }
                }


          
            }
            if(this.userOutClass == 0){
               this.messAlert='No result';
                    this.showAlert=true;
            
            }

        
      },
    myFunction(){
      document.getElementById('alert').classList.remove("block");
            this.showAlert=false;
     },
       handleClickOutside(evt) {
        if (!this.$el.contains(evt.target)) {
          this.isOpen = false;
        //  this.arrowCounter = -1;
        }
      }
  },
  created(){
       var idtoken=JSON.parse(localStorage.getItem("token"));
    axios.get(`http://192.168.1.2:8082/memberClass/${idtoken}\/`).then(response=>{
		//   this.listContents = data.body.data;
			 this.listMember = response.data;
             //console.log('listEmail',response.data);
         //console.log(this.listMember);
                
            });
     axios.get(`http://192.168.1.2:8082/getListEmailUser/${idtoken}\/`).then(response=>{
		//   this.listContents = data.body.data;
			 this.listEmailUser = response.data;
        //     console.log('listUser',response.data);
        //     console.log('itemUser',this.listEmailUser[0]);
         //console.log(this.listMember);
                
            })
            
  },
  computed:{
       filterEmail: function() {
           var postArr=this.listEmailUser,
            searchString = this.searchEmail;
                    //    console.log('postAr',postArr);
                    //    console.log(searchString);
            if(!searchString){
                return searchString;
            }
            searchString = searchString.trim().toLowerCase();
            postArr = postArr.filter( function(item) {
                if(item.toLowerCase().indexOf(searchString) !== -1) {
                    return item;
                }
            })
        return postArr;
        }
  },
    mounted() {
      document.addEventListener('click', this.handleClickOutside);
    },
    destroyed() {
      document.removeEventListener('click', this.handleClickOutside);
    }

}
</script>

<style>
.rightbar-member{
    border: 1px solid #34ace0;
    padding: 10px;
    background: #fff;
    border-radius: 5px;
    margin-bottom: 30px;
     position: relative;
}
.d-flex{
    display:flex;

}
.rightbar-member .form-group{
    margin-bottom:0;
}
.searchEmail{
    position: absolute;
    font-size: 14px;
}
.searchEmail .searchShow {
    background: whitesmoke;
    margin-bottom: 4px;
}
.rightbar-member button.addMember {
    display: block;
    background: transparent;
    border: none;
    color: #34ace0;
    font-size: 18px;
    padding-right: 0;
}
.main__classInformations {
    padding: 10px;
    background: #fff;
    border: 1px solid #34ace0;
    border-radius: 5px;
    margin-bottom: 30px;
}
.main__classInformations p.inforTittle {
    font-weight: bold;
    margin-bottom: 15px;
    color: #34ace0;
}
.main__classInformations span.inforLine {
    display: block;
    width: 100px;
    border-bottom: 1px solid #34ace0;
    margin-left: -10px;
    margin-bottom: 10px;
}
.main__classLinks {
    padding: 10px;
    background: #fff;
    border: 1px solid #34ace0;
    border-radius: 5px;
    margin-bottom: 30px;
}
.main__classLinks p.linkTittle {
    font-weight: bold;
    margin-bottom: 15px;
    color: #34ace0;
}
.main__classLinks span.linkLine {
    display: block;
    width: 100px;
    border-bottom: 1px solid #34ace0;
    margin-left: -10px;
    margin-bottom: 10px;
}
.main__classLinks a {
    margin-bottom: 10px;
    display: block;
    color: #000;
}
.searchEmail{
    position:reala
}
.block{
    .aaa
	position: absolute;
	right: 0;
    top: 100%;
    display:block !important;
}
</style>
