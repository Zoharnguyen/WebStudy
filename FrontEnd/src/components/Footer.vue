<template>
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
                        <li class="flex-fill">Contact</li>
                    </ul>
                </div>
            </div>
        </div>
    </footer>
</template>
<script>
export default {
  name: 'home'
}
</script>
<style>
    
footer {
    padding: 30px 0;
    background-color: #f8f8f8;
}
p.copyright {
    margin: 15px 0 0;
} 
.col-md-4.copyright1 {
    display: flex;
    align-items: center;
}
</style>