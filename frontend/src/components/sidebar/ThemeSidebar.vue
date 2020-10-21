<template>
  <div class="theme-list-wrap ">
    <div class="theme-wrap d-flex align-center justify-center" :style="{'background-color' : 'rgb('+color.color0.r+','+color.color0.g+','+color.color0.b+')'}" v-for="color in this.themes" :key="color.color0">
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
      const r = (exampleThemes[i].color1.r+exampleThemes[i].color2.r+exampleThemes[i].color3.r+exampleThemes[i].color4.r+exampleThemes[i].color5.r)/5;
      const g = (exampleThemes[i].color1.g+exampleThemes[i].color2.g+exampleThemes[i].color3.g+exampleThemes[i].color4.g+exampleThemes[i].color5.g)/5;
      const b = (exampleThemes[i].color1.b+exampleThemes[i].color2.b+exampleThemes[i].color3.b+exampleThemes[i].color4.b+exampleThemes[i].color5.b)/5;
      const bg = { color1: exampleThemes[i].color1,
                  color2: exampleThemes[i].color2,
                  color3: exampleThemes[i].color3,
                  color4: exampleThemes[i].color4,
                  color5: exampleThemes[i].color5,
                  color0: { r, g, b}};
      console.log('for문 내부',this.themes);
      this.themes.push(bg);
    }
    console.log('themes : ',this.themes)
  },
  data(){
    return{
      exampleThemes: exampleThemes,
      themes: [],
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