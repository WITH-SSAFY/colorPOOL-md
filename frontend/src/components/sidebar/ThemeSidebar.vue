<template>
  <div class="theme-list-wrap">
    <div class="theme-wrap d-flex align-center justify-center" :style="{'background-color' : 'rgb('+color.color0.r+','+color.color0.g+','+color.color0.b+')'}"
          v-for="(color, index) in themes" :key="index" @click="selectTheme(color)" @mouseover="overTheme(index)" @mouseout="outTheme(index)">
      <div class="theme color1" :style="{'background-color' : 'rgb('+color.color1.r+', '+color.color1.g+', '+color.color1.b+')'}"></div>
      <div class="theme color2" :style="{'background-color' : 'rgb('+color.color2.r+', '+color.color2.g+', '+color.color2.b+')'}"></div>
      <div class="theme color3" :style="{'background-color' : 'rgb('+color.color3.r+', '+color.color3.g+', '+color.color3.b+')'}"></div>
      <div class="theme color4" :style="{'background-color' : 'rgb('+color.color4.r+', '+color.color4.g+', '+color.color4.b+')'}"></div>
      <div class="theme color5" :style="{'background-color' : 'rgb('+color.color5.r+', '+color.color5.g+', '+color.color5.b+')'}"></div>
    </div>
  </div>
</template>

<script>
import exampleThemes from '../../assets/themeEx'
import { mapActions, mapGetters } from 'vuex'
const colorStore = 'colorStore'

export default {
  name: 'ThemeSidebar',
  computed: {
    ...mapGetters(colorStore, { storeDemoFlag : 'GE_DEMO_FLAG'})
  },
  data(){
    return{
      exampleThemes: exampleThemes,
      themes: [],
      demoFlag: [],
    }
  },
  watch: {
    storeDemoFlag(val){
      this.demoFlag = val;
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

    // var payload2 = payload;
    // for(var i = 0; i < 5; i++){
    //   if(!this.demoFlag[i]){
    //     payload2[i] = ''; 
    //   }
    // }
    // this.AC_SELECTED_DEMO(payload2);
    // this.AC_SELECTED_DEMO({theme : payload, flag : this.demoFlag});

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
  },
  methods:{
    ...mapActions(colorStore, ['AC_SELECTED_THEME', 'AC_DEMO_COLOR', 'AC_DEMO_FLAG', 'AC_SELECTED_DEMO']),
    
    //배색 하나 선택
    selectTheme(theme){
      var payload = {
        selectedTheme: theme
      }
      this.AC_SELECTED_THEME(payload);
      
      payload = [
        'rgb('+theme.color1.r+', '+theme.color1.g+', '+theme.color1.b+')',
        'rgb('+theme.color2.r+', '+theme.color2.g+', '+theme.color2.b+')',
        'rgb('+theme.color3.r+', '+theme.color3.g+', '+theme.color3.b+')',
        'rgb('+theme.color4.r+', '+theme.color4.g+', '+theme.color4.b+')',
        'rgb('+theme.color5.r+', '+theme.color5.g+', '+theme.color5.b+')',
      ]
      // this.AC_DEMO_COLOR(payload);
      console.log('배색 선택 시 payload : ', payload)
      this.AC_DEMO_COLOR({theme: payload, flag: this.demoFlag}); 

      // var payload2 = payload;
      // for(var i = 0; i < 5; i++){
      //   if(!this.demoFlag[i]){
      //     payload2[i] = ''; 
      //   }
      // }
      // this.AC_SELECTED_DEMO(payload2);
      // this.AC_SELECTED_DEMO({theme : payload, flag : this.demoFlag});
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