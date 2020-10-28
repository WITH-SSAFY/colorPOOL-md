<template>
<div>

  <!-- 컬러 선택해서 추천 배색을 불러온 경우 -->
  <div class="theme-list-wrap">
      <div class="theme-wrap d-flex align-center justify-center" v-for="(theme, index) in themeList" :key="index"
            @click="selectTheme(theme)" @mouseover="overTheme(index)" @mouseout="outTheme(index)">
        
        <div class="theme color1" :style="{'background-color': theme.color1 }"></div>
        <div class="theme color2" :style="{'background-color': theme.color2 }"></div>
        <div class="theme color3" :style="{'background-color': theme.color3 }"></div>
        <div class="theme color4" :style="{'background-color': theme.color4 }"></div>
        <div class="theme color5" :style="{'background-color': theme.color5 }"></div>
      </div>
  </div>
</div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
const colorStore2 = 'colorStore2'
const themeStore = 'themeStore'

export default {
  name: 'ThemeSidebar',
  computed: {
    ...mapGetters(themeStore, { storeThemeList: 'GE_THEME_LIST'})
  },
  data(){
    return{
      themeList: [], //추천 배색
    }
  },
  watch: {
    storeThemeList(val){
      this.themeList = [];
      //배경색 계산 필요없는 경우
      // this.themeList = val;

      //배경색 계산이 필요한 경우
      val.forEach((ele) => {
        // const r = (ele.red1 + ele.red2 + ele.red3 + ele.red4 + ele.red5) / 5;
        // const g = (ele.green1 + ele.green2 + ele.green3 + ele.green4 + ele.green5) / 5;
        // const b = (ele.blue1 + ele.blue2 + ele.blue3 + ele.blue4 + ele.blue5) / 5;

        const bg = {
          color1: 'rgb(' + ele.red1 + ', ' + ele.green1 + ', ' + ele.blue1 + ')',
          color2: 'rgb(' + ele.red2 + ', ' + ele.green2 + ', ' + ele.blue2 + ')',
          color3: 'rgb(' + ele.red3 + ', ' + ele.green3 + ', ' + ele.blue3 + ')',
          color4: 'rgb(' + ele.red4 + ', ' + ele.green4 + ', ' + ele.blue4 + ')',
          color5: 'rgb(' + ele.red5 + ', ' + ele.green5 + ', ' + ele.blue5 + ')',
          // color0: 'rgb(' + r + ', ' + g + ', ' + b + ')',
        }
        this.themeList.push(bg);
      })
    },
  },
  created(){
    
  },
  methods:{
    ...mapActions(colorStore2, ['AC_SELECTED_THEME']),
    
    //배색 하나 선택
    selectTheme(theme){
      var payload = {
        selectedTheme: theme
      }
      this.AC_SELECTED_THEME(payload);
    },

    //Theme 위에 마우스 올렸을 때, 애니메이션을 위한 함수
    overTheme(index){
      var targetTheme = document.getElementsByClassName('theme-wrap');
      targetTheme[index].classList.add("animation-in");
      targetTheme[index].classList.remove("animation-out");
    },
    outTheme(index){
      var targetTheme = document.getElementsByClassName('theme-wrap');
      targetTheme[index].classList.remove("animation-in");
      targetTheme[index].classList.add("animation-out");
    }
  }
}
</script>

<style scoped>

.theme-list-wrap{
  width: 100%;
  height: 100vh;
  padding-top: 25px;
  border-right: 3px solid #707070;
  overflow: scroll;
}

.animation-in{
  -webkit-filter: blur(0);
  transform: scale(1.1);
  transition-duration: 0.3s;
}

.animation-out{
  -webkit-filter: blur(0);
  transform: scale(1);
  transition-duration: 0.1s;
}

.theme-wrap{
  margin-left: 15%;
  margin-bottom: 25px;
  width: 70%;
  height: 95px;
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

@keyframes scaleBounce{
	from {transform:scale(1)}
	to {transform:scale(1.05)}
}
</style>