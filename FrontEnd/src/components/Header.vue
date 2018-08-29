<template>
   <!-- <login14></login14>-->
    <div class='wrap1'>
        <div class="container-fluid">
            <div class="row ">
            <!--   -->
                <div class="container">
                 <div class="header">
                 <!-- <div class="col-md-12"> -->
                        <div class="row justify-content-md-center row1">
                            <div class="col-2">
                                <button class="btn btn-default">
                                    <i class="fa fa-camera"></i>
                                    <span></span>
                                </button>
                            </div>
                        </div>
                        <div class="row align-items-end row2">
                            <div class="col-3 align-self-end">
                                <div class="user-info">
                                    <img src="../assets/img/java1.png" alt="" class="header_img">
                                </div>
                            </div> 
                            <div class="col-8">
                                <h3>Java Team</h3>
                                <div class="dropdown">
                                    <button type="button" class="btn dropdown-toggle joinClass" data-toggle="dropdown">
                                    Joined
                                     </button>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" href="#">Quit Class</a>
                                        
                                    </div>
                                </div> <!--end dropdown-->
                            </div>
                        </div> <!-- end row2 -->
                        <div class="header-menu">
                            <div class="row">
                                <div class="col-9">
                                    <nav class="navbar navbar-expand-lg navbar-light bg-light">
                                      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                                        <span class="navbar-toggler-icon"></span>
                                    </button>

                                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                                        <ul class="navbar-nav mr-auto">
                                          <li class="nav-item active">
                                            <router-link to='/article'><a class="nav-link">Content <span class="sr-only"></span></a></router-link>
                                        </li>
                                        <li class="nav-item">
                                            <router-link to='/article/member'><a class="nav-link" >Member</a></router-link>
                                        </li>
                                        <li class="nav-item ">
                                            <a class="nav-link" href="#">Attendance</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link " href="#">Calender</a>
                                        </li>
                                        <li class="nav-item">
                                           <router-link to='/article/pending'> <a class="nav-link " >Pending Items</a></router-link>
                                        </li> <li class="nav-item">
                                            <a class="nav-link " href="#">Setting</a>
                                        </li>
                                    </ul>
                                </div>
                            </nav>
                        </div>
                    </div>
                </div> <!-- end header-menu -->
                    </div> <!-- end container -->
                </div> <!-- end header -->
                <!-- </div> -->
         </div>
        </div>
    </div>
</template>

<script>
import login14 from './login14'
export default {
  name: 'article',
  components:{
      login14
  }
}
</script>

<style>
.wrap1{
    background:gray;
    background: url(../assets/img/bg-java.jpg);
    background-size: cover;
}
.header{
    height: 400px;
    width: 100%;
}
.header h3{
    color:white;
}
.header button.btn.dropdown-toggle.joinClass{
    background: transparent;
    color: white;
    border: white solid 1px;
    border-radius: 0;
}
.header .user-info img{
    background-size: cover;
    width: 100%;
}
.header .row.align-items-end{
    min-height: 300px;
}
/* -------header -menu------ */
.header-menu{
    margin-top:20px;
}
.header-menu .nav-item {
  padding: 0 10px;
}
.header-menu .navbar-light .navbar-nav .active > .nav-link{
    color: blue;
    border-bottom: blue solid 1px;
}
</style>