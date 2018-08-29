<template>
<nav class="navbar navbar-expand-md navbar-fixed-top topnav" role="navigation">

	<div class="container topnav">
		<div class="navbar-header">
			<a class="navbar-brand topnav" href="#">
				<router-link to="/"><img src="../assets/img/logo1.png" alt="Logo"></router-link>
			</a>
		</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item dropdown" v-if='checkGSign'>
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" 
						role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Following Classes
					</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<router-link to="/article"><a class="dropdown-item">Php</a></router-link>
						<a class="dropdown-item" href="#">Java</a>
						<div class="dropdown-divider"></div>
						    <a class="dropdown-item" href="#">Something else here</a>
				    </div>
				</li>
				<li class="nav-item">
				<!--	<a class="nav-link" href="#about" v-on:click='show'>About</a>-->
					<a class="nav-link" href="#about">Help</a>
				</li >
				<li class="nav-item">
				<!--	<a class="nav-link" href="#about" v-on:click='show'>About</a>-->
					<a class="nav-link" href="#about">Contact</a>
				</li >
				<li class="nav-item">
				<!--	<a class="nav-link" href="#about" v-on:click='show'>About</a>-->
					<a class="nav-link" href="#about">FAQ</a>
				</li >		
					<GSignInButton @checkGSign='isCheckGSign'></GSignInButton>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
	
</nav>
</template>

<script>
import GSignInButton from './GSignInButton'
export default {
  name: 'login',
  data() {
    return {
    //  msg: 'Welcome to Your Vue.js App',
			checkGSign:JSON.parse(localStorage.getItem("user"))||false
		//isCheckG:false
    }
  },
		components:{
		GSignInButton
	},
	methods:{
		 isCheckGSign:function(val){
				this.checkGSign=val;
		},
		// show:function(){
		// 	console.log(this.ischeckG);
		// }
		 
	}
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


</style>
