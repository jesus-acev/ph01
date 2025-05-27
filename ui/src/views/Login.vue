<template>
<div>
    <Logincomponent/>
</div>
</template>


<script>
export default{
components:{
    Logincomponent:() => import("@/components/LoginCom")
},
}
</script>