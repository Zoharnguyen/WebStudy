<template>
 <div class="row">
     <div class="add-content"> 
        <ul class="nav nav-tabs">
            <li class="nav-item">
                <a class="nav-link " data-toggle="tab" href="#home">Add Content</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#view">view all</a>
           </li>
        </ul>
        <div class="tab-content">
            <div id="home" class="tab-pane fade in active">
               <button class="" type="button" id='showAddYN' v-on:click='showForm' v-show='!showform'>You want to add a content? </button> 
                <form v-if='showform'>
                   <div class="flex">
                        <div class="flex-fill">
                            <div class="add-title fix-height">
                                <input class="addTittle form-control" name="" placeholder="Tittle" type="text"
                                :maxlength="max"
                                v-model.trim.lazy='textTitle'
                                @blur='addTitle'>
                                
                                <span class='help-block' v-show='isCheckTitle'>This field is required.</span>
                            </div>
                            <div class="addDate fix-height">
                            <!--    <label>StartDate</label>-->
                                   <DatePicker v-model="time1"  :not-before="new Date()" :not-after="endSelectDate()" 
                                   format="DD/MM/YYYY" lang="en" type='date' placeholder="Start Date" @change='checkStartDate'>
                                   </DatePicker> 
                                     <span class='help-block' v-show="checkSDateFlag">This field is required.</span>
                                <!--   <span class='help-block' v-show="time1 ==null ">This field is required.</span>-->
                                   
                            <!--   {{time1}} 
                                <span class='help-block' v-show='checkStartDate'>start date small end end date.</span>
                                {{time1}} -->
                               
                            </div>
                        </div>
                        <div class="flex-fill">
                            <div class="leveloption fix-height">
                            <select class="addLevel form-control" v-model='selectLevel' @blur='changeSelect'>
                                <option value=""  disable hidden="">Level</option>
                                <option value='begin' >Beginer</option>
                                <option value="inter">Intermediate</option>
                                <option value="expert">Expert</option>
                            </select>
                            
                            <span class='help-block' v-show='checkSelect'>This field is required.</span>
                        </div>
                            <div class="addDate fix-height">
                            <!--    <label>EndDate</label>-->
                                <DatePicker v-model="time2" class='aa' :not-before="startSelectDate()" 
                                  format="DD/MM/YYYY" placeholder='End Date' @change='checkEndDate' ref="input"></DatePicker>
                                   <span class='help-block' v-show="checkEndDateFlag">This field is required.</span>
                              <!--  <span class='help-block' v-show="time2==''">This field is required.</span>-->
                            <!--{{time2}} <span class='help-block' v-show='checkEndDate'>End date greate startdate </span>
                               <img class="ui-datepicker-trigger" src="../imgs/calendar.png" alt="Select date" title="Select date">-->
                               
                            </div>
                        </div>
                         </div>
                        <div class='ckeditor1'>
                             <vue-editor v-model="content" @blur='checkEditor'></vue-editor>
                              <span class='help-block' v-show="content==''">This field is required.</span>
                        </div>
                           <div class='add-action'>
                       <button type="button" class="btn btn-primary" v-if='showform' v-on:click='showForm'>
                        Cancel
                        </button>
                        <button type="button" class="btn btn-primary" @click='createContentDone'>
                        Done
                        </button>
                    </div>

                    </form>
                    </div> <!-- end id='home' -->
                    <div id="view" class="tab-pane fade">
                        <h3 style="padding-left:20px;">...</h3>
                    </div>
                </div> <!-- end tab-content -->
            </div> <!-- end add-content -->
</div><!-- row-->
</template>
<script>
import DatePicker from 'vue2-datepicker'
import { VueEditor } from "vue2-editor";
import axios from 'axios';
export default {
  name: 'articleadd',
  components:{
      DatePicker,
      VueEditor

  },
  data(){
      return{
          showform:false,
          isCheck:false,
          content:null,
          textTitle:'',
          max:64,
         
          selectLevel:'',
          time1:'',
          time2:'',

           isCheckTitle:false,
          checkSelect:false,
          checkSDateFlag:false,
          checkEndDateFlag:false,
          checkEditorFlag:false
         
          
    }
  },
  methods:{
      showForm:function(){
          if(this.showform==false){
              this.showform=true;
          }else{
              this.showform=false;
          }
      },
      addTitle(){
          if(this.textTitle==''){
                this.isCheckTitle=true;
           //      console.log(this.$refs.input);
          }else{
              this.isCheckTitle=false;
          }
      },
      changeSelect(){
          if(this.selectLevel===''){
              this.checkSelect=true;
          }else{
              this.checkSelect=false;
          }
      },
       isValidDate(date){
        if (date === null || date === undefined) {
        return false
        }
      },
      endSelectDate(){
        return this.time2;
      },
      startSelectDate(){
        //  console.log(this.time1);      
          return this.time1;
      },
       checkStartDate(){
        if(this.time1==''||this.time1==null){
            this.checkSDateFlag=true;
        }
        if(this.time1!=''&&this.time1!=null){
            this.checkSDateFlag=false;
        }
      
      },
      checkEndDate(){
     if(this.time2==''||this.time2==null){
         this.checkEndDateFlag=true;
     }
     if(this.time2!=''&&this.time2!=null){
         this.checkEndDateFlag=false;
     }
    // console.log('co le nao',this.time1);
      },
      checkEditor(){
          if(!this.content){
              this.content='';
          }
      },
     
        createContentDone(){
            if(this.textTitle==''){
                this.isCheckTitle=true;
            }
            if(this.selectLevel==''){
                 this.checkSelect=true;
            }
            if(this.time1==''){
                this.checkSDateFlag=true;
            }
            if(this.time2==''){
                this.checkEndDateFlag=true;
            }
            if(!this.content){
              this.content='';
            }
            console.log('checkSDate',this.checkSDateFlag);
            console.log('checkEDate',this.checkEndDateFlag);
            var moment=require('moment');
           
            if(this.isCheckTitle==false&&this.checkSelect==false&&this.checkSDateFlag==false
            &&this.checkEndDateFlag==false&&this.content!=''){
                 console.log('aaaa addarticle');
                var localStore=JSON.parse(localStorage.getItem("user"));
                console.log(localStore.ig);
                var tokenID=JSON.parse(localStorage.getItem('token'));
            //    console.log(tokenID);
           //     console.log('ngaybd',moment(this.time1).format("MM/DD/YYYY"));

                //const myDate = moment(str, 'YYYY-MM-DD').toDate();
                
                console.log('des',this.content);
                console.log('ngaykt',moment(this.time2,"MM/DD/YYYY"));
                // var avatar=localStore.Paa;
                // var string=avatar.split("/").join("_");


                // var titlepost=this.textTitle;
                // var selectLevelpost=this.selectLevel;
                // var startD=this.time1;
                // var endD=this.time2;
                // var cont=this.content;
                // var status='learning';
                // axios.post(`http://192.168.1.39:8080/creatContent/${localStore.ig}\/${string}\/${selectLevelpost}\/${startD}\/${endD}\/${status}\/${cont}\/${tokenID}\/`)
                // .then(response=>{console.log('post content success')})
                // .catch(e=>{console.log('postcontent',e.response)});
                axios.post(`http://192.168.1.2:8082/creatContent/${tokenID}\/`,{
                    userName:localStore.ig,
                    avatar:localStore.Paa,
                    level:this.selectLevel,
                //    startDate:moment(this.time1).,
                    startDate:moment(this.time1).format("MM/DD/YYYY"),
                //loi ko list data do sort sai ngay
                    endDate:moment(this.time2).format("MM/DD/YYYY"),
                    status:'learning',
                    contentName:this.textTitle,
                    contentDescription:this.content,
                })
                .then(response=>{
                    console.log('postcontent success')
                    this.showform=false;
                      if(role=='captain'){
                            alert('them thanh cong');
                        }else{
                             alert('chuyen sang pending');
                     }
                })
                 .catch(e=>{console.log('errorcontent',e.response)});
                   var role=JSON.parse(localStorage.getItem("role"));
            // if(role=='captain'){
            //     alert('them thanh cong');
            // }else{
            //     alert('chuyen sang pending');
            // }
        } //end if axios
           
    }



      
  }
}
</script>

<style>
.wrap2 .add-content {
    margin-top: 30px;
}
.add-content{
    width: 100%;
}
.flex {
    display: flex;
}
.flex-fill {
    width: 47%;
    padding: 25px;
}
.flex-fill .add-title {
    padding-bottom: 24px;
   
}
.fix-height{
 height: 90px;
}
.addDate label{
    display:block !important;
}
.addDate .mx-datepicker{
    width:330px;
}
.leveloption {
    padding-bottom: 25px;
}
.add-content{
    margin-bottom: 30px;
background: #fff;
border: 1px solid #34ace0;
border-radius: 5px;
}
.add-content a.nav-link.active{
    border-top-left-radius: 0;
    border-left: none;
    border-top: none;
    color: #fff;
}
.add-content a.nav-link.active {
    background-color: #34ace0;
}

#home {
    padding: 5px 15px;
    padding-bottom:20px;
}
.add-action {
    padding-top: 20px;
}
#showAddYN {
    background: transparent;
    width: 100%;
    border: 1px solid #Dddddd;
    display: block;
    width: 100%;
    border-radius: 5px;
    text-align: left;
    padding: 10px;
    color: #2d3436;
}

.quillWrapper .ql-toolbar .ql-clean{
    display:none !important;
}
</style>
