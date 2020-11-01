<template>
  <div class="stack-wrap">
    <!-- 상단 페이지 인덱스-->
    <div class="page-index d-flex align-center" type="button" v-if="isPick" @click="goGetColor">with Main keywords</div>
    <div class="page-index d-flex align-center" type="button" v-if="isGet" @click="goPickColor">with Team Color</div>

    <!-- 팔레트 스택 -->
    <div class="palette-wrap">
      <div v-for="(theme, index) in stack" :key="index" @click="showDialog(theme)">
        <div class="palette d-flex align-center justify-center" :style="{'background-color' : theme.background}">
          <div class="theme-wrap d-flex align-center justify-center" style="background-color: white;">
            <div class="theme color1" :style="{'background-color' : theme.color1}"></div>
            <div class="theme color2" :style="{'background-color' : theme.color2}"></div>
            <div class="theme color3" :style="{'background-color' : theme.color3}"></div>
            <div class="theme color4" :style="{'background-color' : theme.color4}"></div>
            <div class="theme color5" :style="{'background-color' : theme.color5}"></div>
          </div>
        </div>
        <!-- 말풍선 아래 부분 -->
        <div class="palette-point" :style="{'border-top-color': theme.background}"></div>
      </div>
    </div>

    <!-- palette custom 페이지 -->
    <palette-custom/>
  </div>
</template>

<script>
import PaletteCustom from '../select-palette/PaletteCustom'
import { mapGetters, mapActions } from 'vuex'
const landingStore = 'landingStore'
const colorStore = 'colorStore'
const customStore = 'customStore'

export default {
  name: 'PaletteStack',
  components:{
    PaletteCustom
  },
  computed: {
    ...mapGetters(landingStore, { storeIsGet: 'GE_IS_GET', storeIsPick: 'GE_IS_PICK'}),
    ...mapGetters(colorStore, { storeSelectedColor: 'GE_SELECTED_COLOR', storeSelectedTheme: 'GE_SELECTED_THEME'})
  },
  data(){
    return{
      isGet: false,
      isPick: false,
      stack: [],
      selectedColor: '',
      dialog: false,
    }
  },
  watch: {
    storeIsGet(val){
      this.isGet = val;
    },
    storeIsPick(val){
      this.isPick = val;
    },
    storeSelectedTheme(val){
      val.selectedTheme.background = this.selectedColor;
      this.stack.unshift(val.selectedTheme);
    },
    storeSelectedColor(val){
      this.selectedColor = val;
    }
  },
  created(){
    this.isGet = this.storeIsGet;
    this.isPick = this.storeIsPick;
    if(!this.isPick&&!this.isGet){
      this.isPick = localStorage.getItem('isPick');
      this.isGet = localStorage.getItem('isGet');
    }
    this.selectedColor = this.storeSelectedColor;
  },
  beforeDestroy(){
    //이 페이지를 벗어날 때, isGet과 isPick을 초기화해줌
    this.AC_IS_GET(false);
    this.AC_IS_PICK(false);
  },
  methods:{
    ...mapActions(landingStore, ['AC_IS_GET', 'AC_IS_PICK', 'AC_DIALOG']),
    ...mapActions(colorStore, ['AC_SELECTED_COLOR', 'AC_SELECTED_THEME']),
    ...mapActions(customStore, ['AC_CUSTOM_THEME']),
    goGetColor(){
      this.AC_IS_GET(true);
      this.AC_IS_PICK(false);
    },
    goPickColor(){
      this.AC_IS_GET(false);
      this.AC_IS_PICK(true);
    },
    showDialog(theme){
      console.log('showDialog', theme);
      this.AC_CUSTOM_THEME(theme);
      // this.AC_SELECTED_COLOR(theme.background);
      // this.AC_SELECTED_THEME([theme.color1, theme.color2, theme.color3, theme.color4, theme.color5]);
      this.AC_DIALOG(true);
    },
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

.page-index:hover{
  background-color: #d16a64;
}

.palette-wrap{
  position: absolute;
  bottom: 0;
  margin-top: 10px;
  margin-bottom: 10px;
  width: 24%;
  max-height: 85vh;
  overflow: scroll;
}

.palette{
  padding-top: 25px;
  height: 170px;
  margin: 0% 10%;
  border-radius: 10px;
  cursor: pointer;
  /* background-color: skyblue; */
}

.palette-point{
  width: 20px;
  margin-left: 15%;
  margin-bottom: 5px; 
  border-left: 15px solid transparent;
  border-right: 15px solid transparent;
  border-top: 10px solid;
  /* border-top-color: skyblue; */
}

.theme-wrap{
  width: 90%;
  height: 105px;
  border: 4px solid #707070;
  cursor:pointer;
}

.theme {
  width: 65px;
  height: 65px;
  border-radius: 75px;
  border: 4px solid #707070;
}

.theme.color1{
  z-index: 50;
}

.theme.color2{
  margin-left: -30px;
  z-index: 51;
}

.theme.color3{
  margin-left: -30px;
  z-index: 52;
}

.theme.color4{
  margin-left: -30px;
  z-index: 53;
}

.theme.color5{
  margin-left: -30px;
  z-index: 54;
}
</style>