import Vue from 'vue'
import Router from 'vue-router'
import GSignInButton from 'vue-google-signin-button'
import HomePage from '@/components/HomePage.vue'
import home from '@/components/home.vue'
import listContent from '@/components/listContent.vue'
import ArticleContent from '@/components/ArticleContent.vue'
import Pending from '@/components/Pending.vue'
import ArticleList from '@/components/ArticleList.vue'
import Member from '@/components/Member.vue'
//import MemberPending from '@/components/MemberPending.vue'
//import Pending from '@/components/Pending.vue'

Vue.use(GSignInButton)
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: HomePage,
      children:[
        {
          path:'',
          component:home
        },
        {
          path:'/:id',
          component:ArticleContent,
          children:[
                {
                path:'pending',
                component:Pending
                  // children:[
                  //   {
                  //     path:'',
                  //     component:
                  //   },
                  // ]
                },
                {
                  path:'member',
                  component:Member
                  },
                {
                  path:'',
                  component:ArticleList
                }
              ]
        }
      ]
    },
    {
      path: '/home',
      name: 'home',
      component: home
    },
    {
      path: '/listcontent',
      name: 'listCont',
      component: listContent
    },
    //  {
    //   path:'/article',
    //   name:'articleContent',
    //   component:ArticleContent,
    //   children:[
    //     {
    //     path:'pending',
    //     component:Pending
    //     },
    //     {
    //       path:'',
    //       component:ArticleList
    //       }
    //   ]
    // }
  ]
})
