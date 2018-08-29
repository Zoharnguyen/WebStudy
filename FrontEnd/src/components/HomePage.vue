<template>
<div>
    <login14></login14>
<!--	<home></home>-->
	<router-view/>
</div>
</template>
<script>
import login14 from './login14'
import home from './home'
export default {
  name: 'homepage',
	components:{
		login14,home
	},

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


</style>