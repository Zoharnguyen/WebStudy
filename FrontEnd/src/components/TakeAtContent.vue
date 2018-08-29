<template>
<div class="modal fade" id="exampleAtt" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
        <div class='header-take'>
            <div class='row justify-content-center'>
           
           <img src='../assets/img/atten.jpg' class='img-responsive img-header'>
           </div>
           <h3>Take Atendance</h3>
            <div class="col-md-12" v-for='(member,index) in dataAtt'>
               <div class="row member-imformation ">
                    <div class=" col-md-3 col-lg-3">
                        
                            <div class='member-img col-md-8'  >
                                <img :src="member.avatar" alt="">
                            
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="member-info">
                            <h5 class='memberName'>{{member.userName}}</h5>
                            <p class='padd'>Gmail: <span style='font-weight:300;' v-model='member.email'>{{member.email}}</span></p>
                            <input type="checkbox" src="img/ btn_name.gif"  class="abc"  v-model='member.status'>
                           <!-- <input type="checkbox" src="img/ btn_name.gif"  class="abc" v-if='member.status==0' v-model='!member.status'>-->
                        </div>
                    <!-- end member-info -->
                    </div>
                    <div class="col-md-3 col-lg-3">

                        <div class='note'>
                            <input type="text" v-model='member.note' >
                        </div>
                    </div>
                    </div>
            <!-- end member-imformation -->
                </div>
                <button @click='donePostTake(idCont)'>Submit</button>
                <button data-dismiss="modal" >Cancel</button>
             </div>
        </div>
    </div>
</div>
</template>

<script>
import axios from 'axios'
export default {
    name: 'takeatt',
    components:{},
    props:['dataAtt','idCont'],
    data(){
        return {
            listMemberAtt:[],
        //    dataAtt:this.dataAtt,
        //    idCont:this.dataAtt.id,

        }
    },
    methods:{
        donePostTake(id_content){
            console.log(this.dataAtt);
            this.dataAtt.forEach(element => {
            email:element.email;
            status:element.status;
            note:element.note;
            element.idContent=this.idCont;
            this.listMemberAtt.push(element);

        
          });
        var idtoken=JSON.parse(localStorage.getItem("token"));
        console.log('a',this.listMemberAtt);
        axios.post(`http://192.168.1.2:8082/insertListAttendance/${idtoken}\/`,this.listMemberAtt)
        .then(response=>{console.log('suceess pending')})
        .catch(e=>{console.log(e)});
        }
    },
      created() {
    //   //  update
    //   // nen watch
    //   this.listMemberAtt=[];
    // //     alert('dsfsadfasdf');
       
      }
}
</script>

 <style>
 .modal-content{
     width:112%;
 }
.modal-content .header .img-header{
    height:300px;
}
 .modal-content .row.member-imformation {
    border-bottom: gray solid 1px;
    padding: 15px 1px;
}
</style>
