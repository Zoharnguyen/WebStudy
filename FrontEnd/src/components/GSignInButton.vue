<template>
    <div>
				<li class="nav-item" v-if='!checkGSign'>
  <g-signin-button
    :params="googleSignInParams"
    @success="onSignInSuccess"
    @error="onSignInError" >
    Sign in
  </g-signin-button>
  </li>
	<li class="nav-item dropdown" v-if='checkGSign' trigger="click">
        <a class="nav-link dropdown-toggle nav-user" data-toggle="dropdown" href="#"
        role="button" aria-haspopup="true" aria-expanded="false">
        <img v-bind:src="user.Paa" alt="" />
        {{user.ig}}
        </a>
          <div class="dropdown-menu">
              <a class="dropdown-item" href="#">View Profile</a>
              <a class="dropdown-item" href="/"  @click="signOut1">Logout </a>
          </div>
	</li >
   <div class="alert alert-danger " id='success' style="display:none" >
    <button v-on:click="myFunction" class='btn-alert'>&times;</button>
  		<strong>Email không được phép truy cập hệ thống</strong> 
	</div>
  </div>
 
</template>

<script>
import axios from 'axios';
export default {
    name:'gSingButton',
  data() {
    return {
    user:JSON.parse(localStorage.getItem("user")),
      /**
       * The Auth2 parameters, as seen on
       * https://developers.google.com/identity/sign-in/web/reference#gapiauth2initparams.
       * As the very least, a valid client_id must present.
       * @type {Object}
       */
      googleSignInParams: {
        client_id: '42949777000-2u8ulfkd7qcb047va966lk9iojm7omi3.apps.googleusercontent.com',
      },
       checkGSign:false,
       checkM:false
    };
    
  },
  methods: {
    onSignInSuccess(googleUser) {
      // `googleUser` is the GoogleUser object that represents the just-signed-in user.
      // See https://developers.google.com/identity/sign-in/web/reference#users
      const profile = googleUser.getBasicProfile()
       var id_token = googleUser.getAuthResponse().id_token;
      const HostedDomain = googleUser.getHostedDomain()
      this.user = profile;
    //  var abc='https://www.googleapis.com/oauth2/v3/tokeninfo?id_token='+id_token;
   //   console.log('abc',abc);
  
    if(HostedDomain=='ntq-solution.com.vn'){

       localStorage.setItem('user',JSON.stringify(profile));
    //   var user1=JSON.parse(localStorage.getItem('user'));
    // console.log(user1);
      // console.log(profile.getId());
      // console.log(profile.getImageUrl());
      // console.log(profile.getGivenName());
     // console.log('dntc',this.checkM);
      this.$emit("checkGSign", true);
      this.checkGSign=true;
     this.checkM=false;
       var email=profile.getEmail();
    //  var email='wada@gmail.com';
    //  var id='1';
      var userName=profile.getName();
      var familyName=profile.getFamilyName();
      var givenName=profile.getGivenName();
       var avatar=profile.getImageUrl();
    // var string=avatar.split("/").join("@");
     //  console.log(string); 
      
     // avatar='bbbbbb';
        console.log(avatar);
        axios.post(
        `http://192.168.1.2:8082/login/`,{
        //  params:{
            id:'1',
            email:profile.getEmail(),
            userName:profile.getName(),
            familyName:profile.getFamilyName(),
            givenName:profile.getGivenName(),
            avatar:profile.getImageUrl()
          //  profile.getEmail(),
          //  profile.getName()
        // }
       })
      .then(response =>{
          axios.get('http://192.168.1.2:8082/token/')
            .then(res=>{console.log(res.data);
              localStorage.setItem('token',JSON.stringify(res.data));
            })
            .catch(e => {
            console.log('token err',e)
            });
        
      })
      .catch(e => {
        console.log('post',e.response)
      });
        setTimeout(function(){
          var idtoken=JSON.parse(localStorage.getItem("token"));
        //  console.log(id_token);
          axios.get(`http://192.168.1.2:8082/role/${idtoken}\/`)
            .then(resp=>{
              console.log(resp.data);
              localStorage.setItem('role',JSON.stringify(resp.data));
            })
            .catch(e => {
            console.log('role err',e)
            });
        },4000);
          
      //  axios.get('http://192.168.1.2:8082/token/')
      // .then(res=>{console.log(res.data);
      //   localStorage.setItem('token',JSON.stringify(res.data));
      // })
      // .catch(e => {
      //   console.log('get',e)
      // });
       
      // console.log(res.data);
      
  
    }
    else{
      this.checkM=true;
    //  alert('khong phai email cty');
    //  console.log('sau sai',this.checkM);
      if(this.checkM==true){
        document.getElementById('success').classList.add("aaa");
    //    this.checkM=false;
      }
    }
  //  console.log('hosted',HostedDomain);
  //  console.log('user_info',user_info);
  //this.user_info.user_name=userinfo.ig;

      

    },
    onSignInError (error) {
      // `error` contains any error occurred.
      console.log('OH NOES', error);
      // document.getElementById('success').classList.add("aaa");
      
    },
    signOut1() {
      this.checkGSign=false; 
      this.$emit("checkGSign", false);
    this.checkSuccess=false;
      localStorage.removeItem('user');
      localStorage.removeItem('token');
      localStorage.removeItem('role');

     var auth2=gapi.auth2.getAuthInstance();
      auth2.signOut();
    // console.log('click s-out');
    },
  //    checkMsg(){
  //     if(this.checkM==true){
  //        document.getElementById('success').classList.add("aaa");
  //     }else{
  //       console.log('ko chay');
  //     }
  //  }
  myFunction(){
     document.getElementById('success').classList.remove("aaa");
  }


  },
 
  mounted() {
    this.checkGSign = JSON.parse(localStorage.getItem("user"));
    // hoi ???????
  }
  // computed() {
  //   this.checkGSign = JSON.parse(localStorage.getItem("user"));  
  // }

}
</script>

<style>
.g-signin-button {
  /* This is where you control how the button looks. Be creative! */
  display: inline-block;
  padding: 4px 8px;
  border-radius: 3px;
  background-color: #3c82f7;
  color: #fff;
  box-shadow: 0 3px 0 #0f69ff;
  cursor:pointer;
}
a.nav-link.dropdown-toggle.nav-user img {
    width: 30px;
    border-radius: 50%;
}
.aaa{
	position: absolute;
	right: 0;
    top: 100%;
    display:block !important;
}
.btn-alert{
    background: transparent;
    border: none;
    cursor: pointer;
}
</style>
