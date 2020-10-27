<template>
  <div>
    <div id="container">
      <!-- Color Picker -->
      <div id="colorpicker">
        <div class="color-chip" v-for="(info, index) in materialColors" :key="index"
          :style="{'background-color': info.variations[5].hex}"
          @click="showColor(info.variations[5].hex, info.variations[5].name, info.variations)">
        </div>
      </div>
      <!-- 선택한 컬러 보여주는 원 -->
      <div id="example">
        <div id="bgcolorvalue">
          <div id="bgtitle">{{currentColorName}}</div>
          <div id="bghextitle">{{currentColor}}</div>
        </div>
        <div id="circle" :style="{'background-color': currentColor}" @click="selectColor()">
        </div>
      </div>
    </div>
    <!-- <div class="d-flex justify-center mb-2">
      <input id="color-range" class="color-slider" type="range" min="0" max="1" step="0.1">
      <div class="range-value" id="range-value"></div>   
    </div> -->
    <div class="d-flex justify-center">
      <div class="variations-wrap">
        <div class="variations" v-for="(color, index) in variations" :key="index" :style="{'background-color': color.hex}"
              @click="selectColor(color)">
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import materialColors from '../../assets/colorList'
import { mapActions } from 'vuex'
const colorStore = 'colorStore'
const themeStore = 'themeStore'

export default {
  name: 'PickColor',
  computed(){

  },
  data(){
    return{
      materialColors: materialColors,
      currentColor: '#F44336',
      currentColorName: 'Red Orange',
      variations: materialColors[0].variations,
      selectedColor: '', 
    } 
  },
  methods:{
    ...mapActions(colorStore, ['AC_SELECTED_COLOR']),
    ...mapActions(themeStore, ['AC_THEME_LIST']),
    selectColor(color){
      // alert('selected Color : '+this.currentColor)
      this.selectedColor = color.hex;
      this.currentColor = color.hex;
      this.currentColorName = color.name;
      this.AC_SELECTED_COLOR(color.hex);
      this.AC_THEME_LIST(color.id);
    },
    showColor(color, name, vari){
      // console.log(color+", "+name);
      this.currentColor = color;
      this.currentColorName = name;
      this.variations = vari;
    }
  }

}
</script>

<style scoped>
#container {
    user-select: none;
    display: flex;
    padding-top: 5px;
    justify-content: center;
    height: 100%;
}

#colorpicker {
  height: 50%;
  width: 45%;
  background-color: rgb(53, 53, 53);
  border-radius: 15px;
  margin: 20px;
  padding: 30px;
}

.color-chip{
  border-radius: 75px;
  float: left;
  height: 30px;
  width: 30px;
  margin: 8.3%;
  transition-duration: 300ms;
  cursor: pointer;
}

.color-chip:hover{
  transform: scale(1.5);
  transition-duration: 300ms;
}

#example {
    margin: 100px 40px 40px 40px;
}

#bgtitle {
    font-size: 14px;
}

#bghextitle {
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
    cursor: pointer;
}

.variations-wrap{
    height: 80px;
    width: 800px;
    border-radius: 15px;
    background-color: black;
}

.variations{
  overflow: hidden;
  display: inline-block;
  height: 100%;
  width: 10%;
  cursor: pointer;
}

.variations:hover{
  border: 4px solid rgba(0, 0, 0, 0.5);
}

/* slider 관련 css */
.range-value {
    padding: 20px;
    font-family: "Helvetica Neue", "Helvetica", "Arial", sans-serif;
    font-weight: 600;
    font-size: 20px;
    color: #595959;
}

.color-slider {
    height: 80px;
    width: 800px;
    border-radius: 75px;
    background-image: linear-gradient(to right, #FFEBEE, #B71C1C);
    padding: 0 10px;
    margin: 0;
    -webkit-appearance: none;
    outline: none;
}
.color-slider::-webkit-slider-thumb {
    -webkit-appearance: none;
    background: transparent;
    height: 50px;
    width: 50px;
    border-radius: 30px;
    box-shadow: inset 0 0 0 5px white, inset 0 0 0 6px rgba(0, 0, 0, .2), 0 0 0 1px rgba(0, 0, 0, .2);
}
.color-slider:focus {
    box-shadow: 0 0 0 2px #0cf, inset 0 0 0 5px white, inset 0 0 0 6px rgba(0, 0, 0, .2);
}
 

</style>