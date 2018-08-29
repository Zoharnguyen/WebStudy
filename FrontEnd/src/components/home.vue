<template>
<div>
<div class="intro-header">
        <div class="container">
            <div class="row ">
                <div class="col-lg-12 ">
                    <div class="intro-message">
                        <h3>Welcome</h3>
                        <h1>Learning Social Network</h1>
                        <hr class="intro-divider">
                    <!--    <ul class="list-inline intro-social-buttons">
                            <li>
                                <button type="button" class="btn btn-outline-primary">Primary</button>
                            </li>
                        </ul>-->
                    </div>
                </div>
           </div>
        </div>
    </div>
    <!-- header -->
    <div class="wrap2">
        <div class="container">
            <div class="row">
                <div class="header-product">
                    <h2>Khoas hoc</h2>
                    <hr class="small">
                </div>
                <div class="col-md-4 column productbox">
                    <img src="../assets/img/im1.jpg" class="img-responsive">
                    <div class="producttitle">Product 2</div>
                    <div class="productabout">
                        <div class="pull-right">
                           <p>Curious which components explicitly require jQuery, our JS, and Popper.js? Click the show components link below. If you’re at all unsure about the general page structure, keep reading for an example page template.</p>
                        </div>
                    </div>
                </div> 

                <div class="col-md-4 column productbox">
                    <img src="../assets/img/ima1.png" class="img-responsive">
                    <div class="producttitle">Product 2</div>
                    <div class="productabout">
                        <div class="pull-right">
                           <p>Curious which components explicitly require jQuery, our JS, and Popper.js? Click the show components link below. If you’re at all unsure about the general page structure, keep reading for an example page template.</p>
                        </div>
                    </div>
                </div> 
                <div class="col-md-4 column productbox">
                    <img src="../assets/img/im2.png" class="img-responsive">
                    <div class="producttitle">Product 2</div>
                    <div class="productabout">
                        <div class="pull-right">
                           <p>Curious which components explicitly require jQuery, our JS, and Popper.js? Click the show components link below. If you’re at all unsure about the general page structure, keep reading for an example page template.</p>
                        </div>
                    </div>
                </div> 
            </div>
        </div>
    </div>
    <footer>
        <div class="container">
            <div class="row justify-content-between">
                <div class="col-md-4 copyright1">
                        <p >Copyright NTQ 2018.All Rights Reserved</p>
                </div>
                <div class='col-md-4 copyright2'>
                    <ul class='d-inline-flex'>
                        <li class="flex-fill">Home</li>
                        <li class="flex-fill">About</li>
                        <li>Contact</li>
                    </ul>
                </div>
            </div>
        </div>
    </footer>
</div> 
</template>

<script>
export default {
  name: 'home'
}
</script>
<style scoped>
.intro-header {
    padding-top: 50px; 
    padding-bottom: 50px;
    text-align: center;
    color: #f8f8f8;
    background: url(../assets/img/bg1.jpg) no-repeat center center;
    background-size: cover;
}
.intro-message > h1 {
    margin: 0;
    text-shadow: 2px 2px 3px rgba(0,0,0,0.6);
    font-size: 46px;
}
.intro-message {
    position: relative;
    padding-top: 10%;
    padding-bottom: 20%;
}

.intro-divider {
    width: 400px;
    border-top: 1px solid #f8f8f8;
    border-bottom: 1px solid rgba(0,0,0,0.2);
}

.intro-message > h3 {
    text-shadow: 2px 2px 3px rgba(0,0,0,0.6);
}

@media(max-width:767px) {
    .intro-message {
        padding-bottom: 15%;
    }

    .intro-message > h1 {
        font-size: 30px;
    }

    ul.intro-social-buttons > li {
        display: block;
        margin-bottom: 20px;
        padding: 0;
    }

    ul.intro-social-buttons > li:last-child {
        margin-bottom: 0;
    }

    .intro-divider {
        width: 100%;
    }
}
footer {
    padding: 30px 0;
    background-color: #f8f8f8;
}

p.copyright {
    margin: 15px 0 0;
} 
.wrap1{
    background: gray;
}
.header{
    height: 400px;
    width: 100%;
}
.header .user-info img{
    background-size: cover;
    width: 100%;
}
.header .row.align-items-end{
    min-height: 260px;
}

.header-product{
    width: 100%;
}
</style>