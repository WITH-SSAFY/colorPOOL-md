<template>
<div>
  <!-- 컬러 선택 전이라서 추천 배색이 아직 없는 경우는 더미 데이터를 보여줌-->
  <div class="theme-list-wrap" v-if="themeList.length==0">
    <div class="theme-wrap d-flex align-center justify-center" :style="{'background-color' : 'rgb('+color.color0.r+','+color.color0.g+','+color.color0.b+')'}"
          v-for="(color, index) in themes" :key="index" @click="selectTheme(color)" @mouseover="overTheme(index)" @mouseout="outTheme(index)">
      <div class="theme color1" :style="{'background-color' : 'rgb('+color.color1.r+', '+color.color1.g+', '+color.color1.b+')'}"></div>
      <div class="theme color2" :style="{'background-color' : 'rgb('+color.color2.r+', '+color.color2.g+', '+color.color2.b+')'}"></div>
      <div class="theme color3" :style="{'background-color' : 'rgb('+color.color3.r+', '+color.color3.g+', '+color.color3.b+')'}"></div>
      <div class="theme color4" :style="{'background-color' : 'rgb('+color.color4.r+', '+color.color4.g+', '+color.color4.b+')'}"></div>
      <div class="theme color5" :style="{'background-color' : 'rgb('+color.color5.r+', '+color.color5.g+', '+color.color5.b+')'}"></div>
    </div>
  </div>

  <!-- 컬러 선택해서 추천 배색을 불러온 경우 -->
  <div class="theme-list-wrap" v-if="themeList.length > 0">
      <div class="theme-wrap d-flex align-center justify-center" v-for="(theme, index) in themeList" :key="index"
            @click="selectTheme(theme)" @mouseover="overTheme(index)" @mouseout="outTheme(index)">
        <!-- 배경색 계산 안한 경우 -->
        <!-- <div class="theme color1" :style="{'background-color': 'rgb('+theme.red1+','+theme.green1+','+theme.blue1+')'}"></div>
        <div class="theme color2" :style="{'background-color': 'rgb('+theme.red2+','+theme.green2+','+theme.blue2+')'}"></div>
        <div class="theme color3" :style="{'background-color': 'rgb('+theme.red3+','+theme.green3+','+theme.blue3+')'}"></div>
        <div class="theme color4" :style="{'background-color': 'rgb('+theme.red4+','+theme.green4+','+theme.blue4+')'}"></div>
        <div class="theme color5" :style="{'background-color': 'rgb('+theme.red5+','+theme.green5+','+theme.blue5+')'}"></div> -->
        
        <!-- 배경색 계산 한 경우 -->
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
import exampleThemes from '../../assets/themeEx'
import { mapActions, mapGetters } from 'vuex'
const colorStore = 'colorStore'
const themeStore = 'themeStore'

export default {
  name: 'ThemeSidebar',
  computed: {
    ...mapGetters(colorStore, { storeDemoFlag : 'GE_DEMO_FLAG'}),
    ...mapGetters(themeStore, { storeThemeList: 'GE_THEME_LIST'})
  },
  data(){
    return{
      exampleThemes: exampleThemes,
      themes: [], //demo 배색
      themeList: [], //추천 배색
      demoFlag: [],
    }
  },
  watch: {
    storeDemoFlag(val){
      this.demoFlag = val;
    },
    storeThemeList(val){
      //배경색 계산 필요없는 경우
      // this.themeList = val;

      //배경색 계산이 필요한 경우
      for(var j = 0; j < val.length; j++){
        const curTheme = val[j];
        const r = (curTheme.red1+curTheme.red2+curTheme.red3+curTheme.red4+curTheme.red5)/5;
        const g = (curTheme.green1+curTheme.green2+curTheme.green3+curTheme.green4+curTheme.green5)/5;
        const b = (curTheme.blue1+curTheme.blue2+curTheme.blue3+curTheme.blue4+curTheme.blue5)/5;
        const bg = { color1: 'rgb('+curTheme.red1+', '+curTheme.green1+', '+curTheme.blue1+')',
                    color2: 'rgb('+curTheme.red2+', '+curTheme.green2+', '+curTheme.blue2+')',
                    color3: 'rgb('+curTheme.red3+', '+curTheme.green3+', '+curTheme.blue3+')',
                    color4: 'rgb('+curTheme.red4+', '+curTheme.green4+', '+curTheme.blue4+')',
                    color5: 'rgb('+curTheme.red5+', '+curTheme.green5+', '+curTheme.blue5+')',
                    color0: 'rgb('+r+', '+g+', '+b+')' //배경색
                    };
        this.themeList.push(bg);
      }
    }
  },
  created(){
    this.demoFlag = this.storeDemoFlag;
    //초기 색상 저장
    var payload = {
      selectedTheme : exampleThemes[0]
    }
    this.AC_SELECTED_THEME(payload)

    payload = [
      'rgb('+exampleThemes[0].color1.r+', '+exampleThemes[0].color1.g+', '+exampleThemes[0].color1.b+')',
      'rgb('+exampleThemes[0].color2.r+', '+exampleThemes[0].color2.g+', '+exampleThemes[0].color2.b+')',
      'rgb('+exampleThemes[0].color3.r+', '+exampleThemes[0].color3.g+', '+exampleThemes[0].color3.b+')',
      'rgb('+exampleThemes[0].color4.r+', '+exampleThemes[0].color4.g+', '+exampleThemes[0].color4.b+')',
      'rgb('+exampleThemes[0].color5.r+', '+exampleThemes[0].color5.g+', '+exampleThemes[0].color5.b+')',
    ]
    this.AC_DEMO_COLOR({theme: payload, flag: this.demoFlag});

    //rgb 중간값 계산
    for(var j = 0; j < exampleThemes.length; j++){
      const curTheme = exampleThemes[j];
      const r = (curTheme.color1.r+curTheme.color2.r+curTheme.color3.r+curTheme.color4.r+curTheme.color5.r)/5;
      const g = (curTheme.color1.g+curTheme.color2.g+curTheme.color3.g+curTheme.color4.g+curTheme.color5.g)/5;
      const b = (curTheme.color1.b+curTheme.color2.b+curTheme.color3.b+curTheme.color4.b+curTheme.color5.b)/5;
      const bg = { color1: curTheme.color1,
                  color2: curTheme.color2,
                  color3: curTheme.color3,
                  color4: curTheme.color4,
                  color5: curTheme.color5,
                  color0: { r, g, b}};
      this.themes.push(bg);
    }
    this.themeList = this.storeThemeList;
  },
  methods:{
    ...mapActions(colorStore, ['AC_SELECTED_THEME', 'AC_DEMO_COLOR', 'AC_DEMO_FLAG', 'AC_SELECTED_DEMO']),
    
    //배색 하나 선택
    selectTheme(theme){
      var payload = {
        selectedTheme: theme
      }
      this.AC_SELECTED_THEME(payload);

      if(this.themeList.length > 0){
        console.log('theme 왜 undifined??', theme);
        payload = [
          theme.color1, theme.color2, theme.color3, theme.color4, theme.color5
        ]
        this.AC_DEMO_COLOR({theme: payload, flag: [true, true, true, true, true]}); 
      } else {
        payload = [
          'rgb('+theme.color1.r+', '+theme.color1.g+', '+theme.color1.b+')',
          'rgb('+theme.color2.r+', '+theme.color2.g+', '+theme.color2.b+')',
          'rgb('+theme.color3.r+', '+theme.color3.g+', '+theme.color3.b+')',
          'rgb('+theme.color4.r+', '+theme.color4.g+', '+theme.color4.b+')',
          'rgb('+theme.color5.r+', '+theme.color5.g+', '+theme.color5.b+')',
        ]
        this.AC_DEMO_COLOR({theme: payload, flag: [true, true, true, true, true]}); 
      }
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