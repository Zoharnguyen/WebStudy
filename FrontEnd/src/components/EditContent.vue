<template>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" v-show='showEdit'>
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Edit Content</h5>
       <!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>-->
      </div>
      <div class="modal-body">
        <div class="edit-content"> 
        <form>
 		<div class="flex">
 			<div class="flex-fill">
 				<div class="add-title fix-height">
 					<input class="addTittle form-control" name="" placeholder="Tittle" type="text"
 					:maxlength="max"
 					v-model='dataContent.contentName'
 					@blur='addTitle'>

 					<span class='help-block' v-show='isCheckTitle'>This field is required.</span>
 				</div>
 				<div class="addDate fix-height">
 					<!--    <label>StartDate</label>-->
 					<DatePicker v-model="dataContent.startDate"  :not-before="new Date()" :not-after="endSelectDate()" 
 					format="DD/MM/YYYY" lang="en" type='date' placeholder="Start Date" @change='checkStartDate'>
 				</DatePicker> 
 			<!--	<span class='help-block' v-show="checkSDateFlag">This field is required.</span>-->
 			</div>
 		</div>
 		<div class="flex-fill">
 			<div class="leveloption fix-height">
 				<select class="addLevel form-control" v-model='dataContent.level' @blur='changeSelect'>
 					<option value=""  disable hidden="">Level</option>
 					<option value='begin' >Beginer</option>
 					<option value="inter">Intermediate</option>
 					<option value="expert">Expert</option>
 				</select>

 				<span class='help-block' v-show='checkSelect'>This field is required.</span>
 			</div>
 			<div class="addDate fix-height">
 				<!--    <label>EndDate</label>-->
 				<DatePicker v-model="dataContent.endDate" class='aa' :not-before="startSelectDate()" 
 				format="DD/MM/YYYY" placeholder='End Date' @change='checkEndDate' ref="input"></DatePicker>
 		        <span class='help-block' v-show="checkEndDateFlag">This field is required.</span>

 			</div>
 		</div>
 	</div>
 	<div class='ckeditor1'>
 		<vue-editor v-model="dataContent.contentDescription" @blur='checkEditor'></vue-editor>
 	<span class='help-block' v-show="this.dataContent.contentDescription==''">This field is required.</span>
 	</div>

</form>
      </div>
      </div>

      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal" @click='cancelSaveContent'>Close</button>
        <button type="button" class="btn btn-primary" @click='saveContentDone' >Save changes</button>
      </div>
    </div>
  </div>
</div>
</template>
<script>

import DatePicker from 'vue2-datepicker'
import { VueEditor } from "vue2-editor";
import axios from 'axios';
export default {
  name: 'EditContent',
  components:{
    DatePicker,VueEditor
  },
//   mounted(){
//       this.dataGet=JSON.parse(localStorage.getItem('dataEdit'));
//       console.log('data',this.dataGet);
//       console.log('dataname',this.dataGet.contentName);
//   },
  props:['dataContent','showEdit'],
  data(){
    
    return{
      
          isCheck:false,
         // dataGet:[],
         // content:null,

            textTitle:'',
          max:64,
         
        //  selectLevel:'',
          time1:'',
          time2:'',

        isCheckTitle:false,
        checkSelect:false,
        checkSDateFlag:false,
        checkEndDateFlag:false,
        checkEditorFlag:false
         
    };
  },
   created(){
//       alert('whyyyyy');
//   },
 // created () {

  //      console.log('datacontent la',this.dataContent);

  },
  methods:{
      addTitle(){
          if(this.dataContent.contentName==''){
                this.isCheckTitle=true;
          
          }else{
              this.isCheckTitle=false;
          }
      },
      changeSelect(){
          if(this.dataContent.level===''){
              this.checkSelect=true;
          }else{
              this.checkSelect=false;
          }
      },
      
      endSelectDate(){
        return this.dataContent.endDate;
      },
      startSelectDate(){
        //  console.log(this.time1);      
          return this.startDate;
      },
       checkStartDate(){
        if(this.dataContent.startDate==''||this.dataContent.startDate==null){
            this.checkSDateFlag=true;
        }
        if(this.dataContent.startDate!=''&&this.dataContent.startDate!=null){
            this.checkSDateFlag=false;
        }
      
      },
      checkEndDate(){
     if(this.dataContent.endDate==''||this.dataContent.endDate==null){
         this.checkEndDateFlag=true;
     }
     if(this.dataContent.endDate!=''&&this.dataContent.endDate!=null){
         this.checkEndDateFlag=false;
     }
    // console.log('co le nao',this.time1);
      },
      checkEditor(){
          if(!this.dataContent.contentDescription){
              this.dataContent.contentDescription='';
          }
      },
      cancelSaveContent(){
            localStorage.removeItem('dataEdit');

            this.dataContent=[];
            // this.textTitle='';
            // this.selectLevel='';
            // this.time1='';
            // this.time2='';
            // this.content=null;
      },
     
        saveContentDone(){
            if(this.dataContent.contentName==''){
                this.isCheckTitle=true;
            }
            if(this.dataContent.level==''){
                 this.checkSelect=true;
            }
            if(this.dataContent.startDate==''){
                this.checkSDateFlag=true;
            }
            if(this.dataContent.endDate==''){
                this.checkEndDateFlag=true;
            }
            if(!this.dataContent.contentDescription){
              this.data.contentDescription='';
            }
            console.log('checkSDate',this.checkSDateFlag);
            console.log('checkEDate',this.checkEndDateFlag);
            var moment=require('moment');
           
            if(this.isCheckTitle==false&&this.checkSelect==false&&this.checkSDateFlag==false
            &&this.checkEndDateFlag==false&&this.dataContent.contentDescription!=''){
                 console.log('aaaa');
                var localStore=JSON.parse(localStorage.getItem("user"));
            //    console.log(localStore.ig);
                var tokenID=JSON.parse(localStorage.getItem('token'));
            //    var dataGet=JSON.parse(localStorage.getItem('dataEdit'));

                axios.post(`http://192.168.1.2:8082/editContent/${tokenID}\/`,{
                    id:this.dataContent.id,
                    userName:localStore.ig,
                    avatar:localStore.Paa,
                    level:this.dataContent.level,
                    startDate:moment(this.dataContent.startDate).format("MM/DD/YYYY"),
                    endDate:moment(this.dataContent.endDate).format("MM/DD/YYYY"),
                    status:'learning',
                    contentName:this.dataContent.contentName,
                    contentDescription:this.dataContent.contentDescription,
                })
                .then(response=>{console.log('postcontent success')})
                 .catch(e=>{console.log('errorcontent',e.response)});
            }
        }
  },

}
</script>
<style>
@media (min-width: 576px){
.modal-dialog {
    max-width: 795px;
    margin: 1.75rem auto;
  }
}
</style>
