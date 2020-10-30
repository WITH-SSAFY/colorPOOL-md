<template>
  <div>
    <v-dialog
      v-model="dialog"
      width="600"
      persistent
    >
      <!-- <div class="theme-wrap d-flex align-center justify-center" style="background-color: white">

      </div> -->
      <v-card :style="{'background-color' : selectedColor, 'color' : invertedTextColor}">
        <v-card-title>
          THEMEMEMEMEME
        </v-card-title>
        <div class="custom-theme-wrap d-flex align-center justify-center" :style="{'background-color' : 'rgb(255, 255, 255)'}">
          <div class="theme color1" :class="[isActive0? 'active': '']" @click="clickTheme1" :style="{'background-color' : selectedTheme[0]}"></div>
          <div class="theme color2" :class="[isActive1? 'active': '']" @click="clickTheme2" :style="{'background-color' : selectedTheme[1]}"></div>
          <div class="theme color3" :class="[isActive2? 'active': '']" @click="clickTheme3" :style="{'background-color' : selectedTheme[2]}"></div>
          <div class="theme color4" :class="[isActive3? 'active': '']" @click="clickTheme4" :style="{'background-color' : selectedTheme[3]}"></div>
          <div class="theme color5" :class="[isActive4? 'active': '']" @click="clickTheme5" :style="{'background-color' : selectedTheme[4]}"></div>
        </div>
        <div v-if="colorIndex == -1">
          커스텀할 색이 있다면 선택해보세요
        </div>
        <div v-if="colorIndex != -1" class="custom-color-range justify-center mb-2">
          <input class="color-slider r" type="range" min="0" max="255" step="1" :value="r">
          <input class="color-slider g" type="range" min="0" max="255" step="1" :value="g">
          <input class="color-slider b" type="range" min="0" max="255" step="1" :value="b">
        </div>
        <v-card-actions class="justify-end mr-2 pb-4">
          <v-btn @click="close">
            SUBMIT
          </v-btn>
          <v-btn @click="close">
            CLOSE
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
const landingStore = 'landingStore'
const colorStore = 'colorStore'

export default {
  name: 'PaletteCustom',
  computed:{
    ...mapGetters( landingStore, {storeDialog: 'GE_DIALOG'}),
    ...mapGetters( colorStore, {storeSelectedTheme: 'GE_SELECTED_THEME', storeSelectedColor : 'GE_SELECTED_COLOR'})
  },
  data(){
    return{
      dialog: false,
      selectedTheme: [],
      selectedColor: '',
      invertedTextColor: 'black',
      colorIndex: -1,
      r: null,
      g: null,
      b: null,
      isActive0: false,
      isActive1: false,
      isActive2: false,
      isActive3: false,
      isActive4: false,
    }
  },
  watch: {
    storeDialog(val){
      let color = this.selectedTheme[0];
      color = color.replace("rgb(", "");
      color = color.replace(")", "");
      color = color.split(", ");
      this.r = color[0];
      this.g = color[1];
      this.b = color[2];
      console.log(color)
      this.dialog = val;
    },
    storeSelectedTheme(val){
      this.selectedTheme = val;
    },
    colorIndex(val) {
      // document.querySelector('.custom-theme-wrap').children[val].setAttribute('class', 'active');
      // 선택 색깔이 바뀜
      let color = this.selectedTheme[val];
      color = color.replace("rgb(", "");
      color = color.replace(")", "");
      color = color.split(", ");
      this.r = color[0];
      this.g = color[1];
      this.b = color[2];
      document.querySelector('.color-slider.r').style.background = 'linear-gradient(to right, rgb(0, ' + this.g + ', ' + this.b + '), rgb(255, ' + this.g + ', ' + this.b + '))'
      document.querySelector('.color-slider.g').style.background = 'linear-gradient(to right, rgb(' + this.r + ', 0, ' + this.b + '), rgb(' + this.r + ', 255, ' + this.b + '))'
      document.querySelector('.color-slider.b').style.background = 'linear-gradient(to right, rgb(' + this.r + ', ' + this.g + ', 0), rgb(' + this.r + ', ' + this.g + ', 255))'
    }
  },
  updated() {
    // this.selectedTheme[0] = this.storeSelectedTheme[0];
    // this.selectedTheme[1] = this.storeSelectedTheme[1];
    // this.selectedTheme[2] = this.storeSelectedTheme[2];
    // this.selectedTheme[3] = this.storeSelectedTheme[3];
    // this.selectedTheme[4] = this.storeSelectedTheme[4];
    this.selectedTheme[0] = this.storeSelectedTheme.selectedTheme.color1;
    this.selectedTheme[1] = this.storeSelectedTheme.selectedTheme.color2;
    this.selectedTheme[2] = this.storeSelectedTheme.selectedTheme.color3;
    this.selectedTheme[3] = this.storeSelectedTheme.selectedTheme.color4;
    this.selectedTheme[4] = this.storeSelectedTheme.selectedTheme.color5;
    // this.selectedTheme = this.storeSelectedTheme.selectedTheme;
    this.selectedColor = this.storeSelectedColor;
    this.setBackgroundColor(this.selectedColor);
    // r g b 값 받아오기
    let color = this.selectedTheme[0];
    color = color.replace("rgb(", "");
    color = color.replace(")", "");
    color = color.split(", ");
    this.r = color[0];
    this.g = color[1];
    this.b = color[2];
  },
  created(){
    this.dialog = this.storeDialog;
  },
  mounted() {
    // document.querySelector('.color-slider.r').style.background = 'linear-gradient(to right, rgb(0, ' + this.g + ', ' + this.b + '), rgb(255, ' + this.g + ', ' + this.b + '))'
  },
  beforeDestroy(){
    this.AC_DIALOG(false)
  },
  methods: {
    ...mapActions(landingStore, ['AC_DIALOG']),
    close(){
      this.AC_DIALOG(false);
    },
    setBackgroundColor(color) {
      let hex = color.replace('#', '');
      var value = hex.match( /[a-f\d]/gi ); 
      value = hex.match( /[a-f\d]{2}/gi ); 
      var r = parseInt( value[0], 16 ); 
      var g = parseInt( value[1], 16 ); 
      var b = parseInt( value[2], 16 ); 

      var num = r+g+b;
      if(num < 300) {
        this.invertedTextColor = 'white'
      } else {
        this.invertedTextColor = 'black'
      }
    },
    initIsActive() {
      this.isActive0 = false;
      this.isActive1 = false;
      this.isActive2 = false;
      this.isActive3 = false;
      this.isActive4 = false;
    },
    clickTheme1(){
      this.initIsActive();
      this.isActive0 = true;   
      this.colorIndex = 0;
    },
    clickTheme2(){
      this.initIsActive();
      this.isActive1 = true;   
      this.colorIndex = 1;
    },
    clickTheme3(){
      this.initIsActive();
      this.isActive2 = true;   
      this.colorIndex = 2;
    },
    clickTheme4(){
      this.initIsActive();
      this.isActive3 = true;   
      this.colorIndex = 3;
    },
    clickTheme5(){
      this.initIsActive();
      this.isActive4 = true;   
      this.colorIndex = 4;
    }
  }
}
</script>

<style scoped>
.custom-theme-wrap{
  margin-left: 25%;
  width: 50%;
  height: 105px;
  border: 4px solid #707070;
}

.theme {
  width: 65px;
  height: 65px;
  border-radius: 75px;
  border: 4px solid #707070;
  transition-duration: 300ms;
}

.theme.active {
  transform: scale(1.2);
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


/* ============================== color range 관련 CSS ============================== */
.range-value {
    padding: 20px;
    font-family: "Helvetica Neue", "Helvetica", "Arial", sans-serif;
    font-weight: 600;
    font-size: 20px;
    color: #595959;
    outline: none;
}

.color-slider {
    height: 20px;
    width: 80%;
    /* background-image: linear-gradient(to right, #FFEBEE, #B71C1C); */
    padding: 0 10px;
    margin: 0;
    -webkit-appearance: none;
    outline: none;
}
.color-slider::-webkit-slider-thumb {
    -webkit-appearance: none;
    background: transparent;
    height: 20px;
    width: 20px;
    border-radius: 30px;
    outline: none;
    box-shadow: inset 0 0 0 5px white, inset 0 0 0 6px rgba(0, 0, 0, .2), 0 0 0 1px rgba(0, 0, 0, .2);
}

/* =================================================================================== */
</style>