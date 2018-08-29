<template>
<div>
<Header></Header>
<div class="wrap2">
  <div class="container">
    <div class='row'>
      <div class="col-main-left col-md-9">
          
      <!--  <ArticleList></ArticleList>-->
        <router-view></router-view>
      </div>
      <div class='col-main-right col-md-3'>
          <RightBar></RightBar>
      </div>
    
      </div>
    </div>
  </div> <!--end wrap2-->
  <Footer></Footer>
</div><!-- end all -->
</template>

<script>
import Header from './Header'
import Footer from './Footer'
import ArticleList from './ArticleList'
import RightBar from './RightBar'
export default {
  name: 'articlecontent',
  components:{
    Header,ArticleList,RightBar,Footer
  },
  data(){
    return{
       listArticles:[]
    }
  },
  created() {
  },
}
</script>

<style>

</style>