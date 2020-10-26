<template>
  <div>
    <div id="container">
        <div id="colorpicker">
          <div class="color-chip" v-for="(info, index) in materialColors" :key="index"
              :style="{'background-color': info.variations[5].hex}" @click="showColor(info.variations[5].hex, info.variations[5].name)">
          </div>
        </div>
        <div id="example">
            <div id="bgcolorvalue">
                <div id="bgtitle">{{currentColorName}}</div>
                <div id="bghextitle">{{currentColor}}</div>
            </div>
            <div id="circle" :style="{'background-color': currentColor}">
                <!-- <i id="icon" class="material-icons">
                </i> -->
            </div>
            <!-- <v-btn dark @click="selectColor()">선택하기</v-btn> -->
            <!-- <div id="shadcolorvalue">
                <div id="shadowtitle">Red 500</div>
                <div id="shadowhextitle">#FFGG78</div>
            </div> -->
        </div>
    </div>
  </div>
</template>

<script>
import materialColors from '../../assets/colorList'
import { mapActions } from 'vuex'
const colorStore = 'colorStore'

export default {
  name: 'PickColor',
  computed(){

  },
  data(){
    return{
      materialColors: materialColors,
      currentColor: '#F44336',
      currentColorName: 'Red Orange',
    } 
  },
  methods:{
    ...mapActions(colorStore, 'AC_SELECTED_COLOR'),
    selectColor(){
      alert('selected Color : '+this.currentColor)
    },
    showColor(color, name){
      console.log(color+", "+name);
      this.currentColor = color;
      this.currentColorName = name;
    }
  }

}
</script>

<style scoped>
html,
body {
    padding: 0;
    margin: 0;
    background: rgb(31, 31, 31);
    font-family: 'M PLUS Rounded 1c', sans-serif;
}

.disablescroll {
    overflow: hidden;
}

#container {
    user-select: none;
    display: flex;
    padding-top: 10px;
    justify-content: center;
    height: 100vh;
    min-width: 640px;
}

.copycontainer i {
    font-size: 30px;
    font-size: 30px;
    width: 100%;
    text-align: center;
}

#colorpicker {
  height: 55%;
  width: 45%;
  background-color: rgb(53, 53, 53);
  border-radius: 15px;
  margin: 40px;
  padding: 40px;
}

.color-chip{
  border-radius: 75px;
  float: left;
  height: 30px;
  width: 30px;
  margin: 8.3%;
  transition-duration: 300ms;
}

.color-chip:hover{
  transform: scale(1.5);
  transition-duration: 300ms;
}

#example {
    margin: 100px 40px 40px 40px;
}

#colorpicker .colorp {
    width: 16.666%;
    height: 35px;
    display: flex;
    align-items: center;
    justify-content: center;
}

#shadcolorvalue {
    color: #717171;
    font-size: 18px;
    text-align: center;
    margin-top: 50px;
    position: relative;
}

#copytoclip {
    width: 100%;
    text-align: center;
    color: rgb(228, 228, 228);
}

#bgtitle,
#shadowtitle {
    font-size: 14px;
}

#bghextitle,
#shadowhextitle {
    font-size: 18px;
}

#bgcolorvalue {
    color: #717171;
    font-size: 18px;
    text-align: center;
    margin-bottom: 50px;
    position: relative;
}

#bgcolorvalue span {
    font-size: 25px;
}

#bgcolorvalue::after {
    content: '';
    position: absolute;
    z-index: 0;
    border: 2px solid #717171;
    height: 128px;
    width: 50px;
    border-right: none;
    top: 29px;
    left: -20px;
}

#shadcolorvalue::after {
    content: '';
    position: absolute;
    border: 2px solid #717171;
    height: 121px;
    width: 47px;
    border-left: none;
    top: -92px;
    right: -14px;
}

#colorpicker .colorp .lilcircle {
    height: 30px;
    width: 30px;
    transform: scale(.7);
    background-color: #008fee;
    border-radius: 50%;
    cursor: pointer;
    transition: all .5s ease;
}

#colorpicker .colorp .lilcircle:hover,
#colorpicker .colorp .lilcircle.active {
    transform: scale(1);
    transition: all .5s ease;
}

#colorpicker #example {
    height: 400px;
    width: 300px;
    background-color: rgb(53, 53, 53);
    border-radius: 5px;
    margin: 10px;
    display: flex;
    align-items: center;
    justify-content: center;
}

#circle {
    width: 180px;
    z-index: 1;
    position: relative;
    height: 180px;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    overflow: hidden;
}

#circle i {
    font-size: 100px;
    color: white;
}

#icon {
    cursor: pointer;
}
</style>