<template>
  <div class="stack-wrap">
    <!-- 상단 페이지 인덱스-->
    <div class="page-index d-flex align-center" type="button" v-if="isPick" @click="goGetColor">with Main keywords</div>
    <div class="page-index d-flex align-center" type="button" v-if="isGet" @click="goPickColor">with Team Color</div>

    <!-- 팔레트 스택 -->
    

  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
const landingStore = 'landingStore'

export default {
  name: 'PaletteStack',
  computed: {
    ...mapGetters(landingStore, { storeIsGet: 'GE_IS_GET', storeIsPick: 'GE_IS_PICK'}),
  },
  data(){
    return{
      isGet: false,
      isPick: false,
      stack: [],
    }
  },
  watch: {
    storeIsGet(val){
      this.isGet = val;
    },
    storeIsPick(val){
      this.isPick = val;
    }
  },
  created(){
    this.isGet = this.storeIsGet;
    this.isPick = this.storeIsPick;
  },
  methods:{
    ...mapActions(landingStore, ['AC_IS_GET', 'AC_IS_PICK']),
    goGetColor(){
      this.AC_IS_GET(true);
      this.AC_IS_PICK(false);
    },
    goPickColor(){
      this.AC_IS_GET(false);
      this.AC_IS_PICK(true);
    }
  }
}
</script>

<style scoped>
.stack-wrap{
  height: 100%;
  width: 100%;
}

.page-index{
  height: 68px;
  width: 80%;
  margin-left: 10%;
  padding-left: 5%;
  background-color: #ee7771;
  font-size: 1.5rem;
  font-weight: bolder;
  color: #232a46;
}

</style>