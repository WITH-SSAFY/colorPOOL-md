<template>
  <div>
    <v-col class="example-area">
      <!-- 상단에 toggle 버튼-->
      <div class="toggle-button-group">
        <v-btn large text>toggle themes</v-btn>
        <v-btn large text>toggle palette</v-btn>
      </div>

      <!-- exapmle ui와 버튼 -->
      <div class="example-md">
        <div class="example-content"><example-content/></div>
        <!-- 다음 단계로 넘어가는 버튼-->
        <div class="button-group">
          <h1>Create Presentation</h1>
          <div type="button" class="button d-flex align-center" @click="goPickColor">with Team Color</div>
          <div type="button" class="button d-flex align-center" @click="goGetColor">with Main Keyword</div>
        </div>
      </div>

      <!-- 선택한 theme 팔레트에 표시-->
      <div class="example-palette">
        <div class="palette-content">
        <h1>Palettes in Markdown</h1>
          <div class="color-info d-flex align-center" @mouseover="overColor(0)" @mouseout="outColor(0)" @click="applyColor(0)">
            <div class="color" :style="{'background-color': color1 }"></div>
            <div class="desc">Background</div>
          </div>
          <div class="color-info d-flex align-center" @mouseover="overColor(1)" @mouseout="outColor(1)" @click="applyColor(1)">
            <div class="color" :style="{'background-color': color2 }"></div>
            <div class="desc">Headline</div>
          </div>
          <div class="color-info d-flex align-center" @mouseover="overColor(2)" @mouseout="outColor(2)" @click="applyColor(2)">
            <div class="color" :style="{'background-color': color3 }"></div>
            <div class="desc">Sub headline</div>
          </div>
          <div class="color-info d-flex align-center" @mouseover="overColor(3)" @mouseout="outColor(3)" @click="applyColor(3)">
            <div class="color" :style="{'background-color': color4 }"></div>
            <div class="desc">Emphasis</div>
          </div>
          <div class="color-info d-flex align-center" @mouseover="overColor(4)" @mouseout="outColor(4)" @click="applyColor(4)">
            <div class="color" :style="{'background-color': color5 }"></div>
            <div class="desc">Quote</div>
          </div>
        </div>
      </div>
    </v-col>
  </div>
</template>

<script>
import ExampleContent from './ExampleContent'
import { mapGetters, mapActions } from 'vuex'
const demoStore = 'demoStore'
const landingStore = 'landingStore'

export default {
  name: 'Example',
  components: {
    ExampleContent,
  },
  computed:{
    ...mapGetters(demoStore, {storeSelectedDemoTheme: 'GE_SELECTED_DEMO_THEME', storeFlagDemoTheme: 'GE_FLAG_DEMO_THEME'}),
  },
  data(){
    return {
      color1: null,
      color2: null,
      color3: null,
      color4: null,
      color5: null,
      selectedDemoTheme: null,
      flagDemoTheme: [],
      demoColor: [],
      demoFlag: [], 
      selectedDemo: ['', '', '', '', '']
    }
  },
  created(){
    this.selectedDemoTheme = this.storeSelectedDemoTheme;
    this.flagDemoTheme = this.storeFlagDemoTheme;
    this.setColors(this.selectedDemoTheme);
  },
  watch: {
    storeSelectedDemoTheme (val) {
      console.log(val);
      this.setColors(val);
    },
    storeFlagDemoTheme (val) {
      console.log(val);
      this.flagDemoTheme = val;
    },
  },
  methods: {
    ...mapActions(landingStore, ['AC_IS_PICK', 'AC_IS_GET']),
    ...mapActions(demoStore, ['AC_FLAG_DEMO_THEME']),
    setColors(color) {
      this.color1 = 'rgb(' + color.red1 + ', ' + color.green1 + ', ' + color.blue1 + ')';
      this.color2 = 'rgb(' + color.red2 + ', ' + color.green2 + ', ' + color.blue2 + ')';
      this.color3 = 'rgb(' + color.red3 + ', ' + color.green3 + ', ' + color.blue3 + ')';
      this.color4 = 'rgb(' + color.red4 + ', ' + color.green4 + ', ' + color.blue4 + ')';
      this.color5 = 'rgb(' + color.red5 + ', ' + color.green5 + ', ' + color.blue5 + ')';
    },
    //getColor와 PickColor 간의 이동을 위한 변수 변경
    goGetColor(){
      this.AC_IS_PICK(false);
      this.AC_IS_GET(true);
      this.$router.push({name: 'Select'});
    },
    goPickColor(){
      this.AC_IS_PICK(true);
      this.AC_IS_GET(false);
      this.$router.push({name: 'Select'});
    },
    
    //Color 위에 마우스 올렸을 때, 애니메이션을 위한 함수
    overColor(index){
      var targetColor = document.querySelectorAll('.color-info .color');
      targetColor[index].classList.add("animation-in");
      targetColor[index].classList.remove("animation-out");
    },
    outColor(index){
      var targetColor = document.querySelectorAll('.color-info .color');
      targetColor[index].classList.remove("animation-in");
      targetColor[index].classList.add("animation-out");
    },

    //적용할 색상을 선택 -> demoFlag 값 변경
    applyColor(index){
      this.flagDemoTheme[index] = !this.flagDemoTheme[index];
      this.AC_FLAG_DEMO_THEME(this.flagDemoTheme);
    }
  }
}
</script>

<style scoped>
.example-area.col{
  padding: 0;
}

.toggle-button-group{
  padding: 1% 5%;
  height: 8vh;
  width: 100%;
}

.toggle-button-group button{
  float: right;
}

.example-md{
  margin: 0;
  padding: 2%;
  height: 62vh;
  width: 100%;
  float: left;
}

.example-content{
  height: 100%; 
  width: 100%;
}

.button-group{
  float: left;
  padding-top: 20%;
  padding-right: 2%;
  height: 62vh;
  width: 30%;
  height: 100%;
  user-select: none;
}

.button-group h1{
  padding-left: 10%;
  margin-bottom: 3%;
}

.button-group button{
  margin-right: 5%; 
  margin-left: 5%; 
  margin-bottom: 7%; 
  display: block;
  font-size: 1.5rem;
}

.button{
  height: 68px;
  width: 95%;
  margin-left: 5%;
  margin-bottom: 5%;
  padding-left: 5%;
  background-color: #ee7771;
  font-size: 1.5rem;
  font-weight: bolder;
  color: #232a46;
}

.button:hover{
  background-color: #d16a64;
}

.example-content{
  float: left;
  width: 70%;
  height: 100%;
}

.example-palette{
  margin: 0;
  padding-left: 3%;
  user-select: none;
}

.palette-content{
  cursor:pointer;
  width: 100%;
  height: 100%;
}

.palette-content .color-info{
  border: 4px solid #707070;
  float: left;
  height: 90px;
  width: 30%;
  margin-right: 3%;
  margin-top: 10px;
  margin-bottom: 10px;
}

.palette-content .color-info .color{
  height: 50px;
  width: 50px;
  border-radius: 75px;
  border: 4px solid #707070;
  margin-left: 8%; 
  margin-right: 3%;
}

.animation-in{
  /* -webkit-filter: blur(0); */
  transform: scale(1.2);
  transition-duration: 0.3s;
}

.animation-out{
  /* -webkit-filter: blur(0); */
  transform: scale(1);
  transition-duration: 0.1s;
}

.palette-content .color-info .desc{
  font-size: 1.3rem;
}
</style>