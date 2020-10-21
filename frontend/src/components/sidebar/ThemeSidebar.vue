<template>
  <div class="theme-list-wrap ">
    <div class="theme-wrap d-flex align-center justify-center" :style="{'background-color' : 'rgb('+color.color0.r+','+color.color0.g+','+color.color0.b+')'}"
          v-for="(color, index) in themes" :key="index" @click="selectTheme(color)">
      <div class="theme color1" :style="{'background-color' : 'rgb('+color.color1.r+','+color.color1.g+','+color.color1.b+')'}"></div>
      <div class="theme color2" :style="{'background-color' : 'rgb('+color.color2.r+','+color.color2.g+','+color.color2.b+')'}"></div>
      <div class="theme color3" :style="{'background-color' : 'rgb('+color.color3.r+','+color.color3.g+','+color.color3.b+')'}"></div>
      <div class="theme color4" :style="{'background-color' : 'rgb('+color.color4.r+','+color.color4.g+','+color.color4.b+')'}"></div>
      <div class="theme color5" :style="{'background-color' : 'rgb('+color.color5.r+','+color.color5.g+','+color.color5.b+')'}"></div>
    </div>
  </div>
</template>

<script>
import exampleThemes from '../../assets/themeEx'
export default {
  name: 'ThemeSidebar',
  created(){
    for(var i = 0; i < exampleThemes.length; i++){
      const curTheme = exampleThemes[i];
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
  data(){
    return{
      exampleThemes: exampleThemes,
      themes: [],
    }
  },
  methods:{
    selectTheme(theme){
      console.log('선택한 theme 정보 : ', theme);
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

.theme-wrap{
  margin-left: 15%;
  margin-bottom: 25px;
  width: 70%;
  height: 95px;
  border: 3px solid #707070;
}

.theme {
  width: 65px;
  height: 65px;
  border-radius: 75px;
  border: 2.5px solid #707070;
  /* float: left; */
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