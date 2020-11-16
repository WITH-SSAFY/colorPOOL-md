<template>
<div>
  <!-- 컬러 선택 전이라서 추천 배색이 아직 없는 경우는 더미 데이터를 보여줌-->
  <div class="theme-list-wrap">
    <div class="theme-wrap d-flex align-center justify-center" :style="{'background-color' : color.background}"
          v-for="(color, index) in demoThemes" :key="index" @click="selectTheme(color)" @mouseover="overTheme(index)" @mouseout="outTheme(index)">
      <div class="theme color1" :style="{'background-color' : 'rgb('+color.red1+', '+color.green1+', '+color.blue1+')'}"></div>
      <div class="theme color2" :style="{'background-color' : 'rgb('+color.red2+', '+color.green2+', '+color.blue2+')'}"></div>
      <div class="theme color3" :style="{'background-color' : 'rgb('+color.red3+', '+color.green3+', '+color.blue3+')'}"></div>
      <div class="theme color4" :style="{'background-color' : 'rgb('+color.red4+', '+color.green4+', '+color.blue4+')'}"></div>
      <div class="theme color5" :style="{'background-color' : 'rgb('+color.red5+', '+color.green5+', '+color.blue5+')'}"></div>
    </div>
  </div>
</div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
const demoStore = 'demoStore'

export default {
  name: 'ThemeSidebar',
  data() {
    return {
      demoThemes: [],
      selectedDemoTheme: null,
      flagDemoTheme: []
    }
  },
  computed: {
    ...mapGetters(demoStore, {storeDemoThemes: 'GE_DEMO_THEMES', storeSelectedDemoTheme: 'GE_SELECTED_DEMO_THEME'}),
  },
  created(){
    this.demoThemes = this.storeDemoThemes;
    this.demoThemes.forEach(ele => {
      
      // 배경색 결정
      let r = parseInt((ele.red1 + ele.red2 + ele.red3 + ele.red4 + ele.red5) / 5)
      let g = parseInt((ele.green1 + ele.green2 + ele.green3 + ele.green4 + ele.green5) / 5)
      let b = parseInt((ele.blue1 + ele.blue2 + ele.blue3 + ele.blue4 + ele.blue5) / 5)
      const val = r + g + b
      if (val < 630 ) { // 밝은 배경
        r = r + 75
        g = g + 75
        b = b + 75
      } else { // 어두운 배경
        r = r - 175
        g = g - 175
        b = b - 175
      }
      ele.background = 'rgb(' + r + ', ' + g + ', ' + b + ')'
  
    })
    this.selectedDemoTheme = this.demoThemes[0];
    this.AC_SELECTED_DEMO_THEME(this.selectedDemoTheme);
  },
  watch: {
    storeSelectedDemoTheme (val) {
      this.selectedDemoTheme = val;
    }
  },
  methods:{
    ...mapActions(demoStore, ['AC_SELECTED_DEMO_THEME']),
    selectTheme(color) {
      this.AC_SELECTED_DEMO_THEME(color);
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
  position: fixed;
  width: 22.5%;
  height: 100vh;
  padding-top: 25px;
  /* border-right: 3px solid #707070; */
  overflow: scroll;
}

.animation-in{
  /* -webkit-filter: blur(0); */
  transform: scale(1.1);
  transition-duration: 0.3s;
}

.animation-out{
  /* -webkit-filter: blur(0); */
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