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
          <v-btn block large elevation="0" color="#EE7771" tile height="68px" @click="goPickColor">With Team Color</v-btn>
          <v-btn block large elevation="0" color="#EE7771" tile height="68px" @click="goGetColor">with Main Keyword</v-btn>
        </div>
      </div>

      <!-- 선택한 theme 팔레트에 표시-->
      <div class="example-palette">
        <div class="palette-content">
        <h1>Palettes in Markdown</h1>
          <div class="color-info d-flex align-center" @mouseover="overColor(0)" @mouseout="outColor(0)" @click="applyColor(color1, 0)">
            <div class="color" :style="{'background-color': color1 }"></div>
            <div class="desc">Background</div>
          </div>
          <div class="color-info d-flex align-center" @mouseover="overColor(1)" @mouseout="outColor(1)" @click="applyColor(color2, 1)">
            <div class="color" :style="{'background-color': color2 }"></div>
            <div class="desc">Headline</div>
          </div>
          <div class="color-info d-flex align-center" @mouseover="overColor(2)" @mouseout="outColor(2)" @click="applyColor(color3, 2)">
            <div class="color" :style="{'background-color': color3 }"></div>
            <div class="desc">Sub headline</div>
          </div>
          <div class="color-info d-flex align-center" @mouseover="overColor(3)" @mouseout="outColor(3)" @click="applyColor(color4, 3)">
            <div class="color" :style="{'background-color': color4 }"></div>
            <div class="desc">Emphasis</div>
          </div>
          <div class="color-info d-flex align-center" @mouseover="overColor(4)" @mouseout="outColor(4)" @click="applyColor(color5, 4)">
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
const colorStore = 'colorStore'
const landingStore = 'landingStore'

export default {
  name: 'Example',
  components: {
    ExampleContent,
  },
  computed:{
    ...mapGetters(colorStore, {storeSelectedTheme: 'GE_SELECTED_THEME',
                                storeDemoColor: 'GE_DEMO_COLOR',
                                storeDemoFlag: 'GE_DEMO_FLAG'}),
  },
  data(){
    return {
      color1: null,
      color2: null,
      color3: null,
      color4: null,
      color5: null,
      // selectedTheme: [],
      demoColor: [],
      demoFlag: [], 
      selectedDemo: ['', '', '', '', '']
    }
  },
  created(){
    // this.selectedTheme = this.storeSelectedTheme;
    this.demoColor = this.storeDemoColor;
    // console.log('created - demoColor', this.demoColor);
    this.color1 = this.demoColor[0];
    this.color2 = this.demoColor[1];
    this.color3 = this.demoColor[2];
    this.color4 = this.demoColor[3];
    this.color5 = this.demoColor[4];

    this.demoFlag = this.storeDemoFlag;
  },
  watch: {
    // storeSelectedTheme(val){
    //   this.selectedTheme = val;
    // },
    storeDemoColor(val){
      this.demoColor = val;
      console.log('example.vue - watch - demoColor', this.demoColor);
      this.color1 = val[0];
      this.color2 = val[1];
      this.color3 = val[2];
      this.color4 = val[3];
      this.color5 = val[4];
    },
    storeDemoFlag(val){
      // console.log('example.vue - watch - storeDemoFlag');
      this.demoFlag = val;
    }
  },
  methods: {
    ...mapActions(landingStore, ['AC_IS_PICK', 'AC_IS_GET']),
    ...mapActions(colorStore, ['AC_DEMO_COLOR', 'AC_DEMO_FLAG', 'AC_SELECTED_DEMO']),

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
    applyColor(color, index){
      // console.log('applyColor', color);
      color
      this.demoFlag[index] = !this.demoFlag[index];
      // console.log('바꿀때 demoFlag 배열', this.demoFlag);
      // this.AC_DEMO_FLAG(this.demoFlag);

      // console.log('demoFlag!!', this.demoFlag);
      // for(var i = 0; i < 5; i++){
      //   if(this.demoFlag[i]){
      //     this.selectedDemo[i] = this.demoColor[i];
      //   } else {
      //     this.selectedDemo[i] = '';
      //   }
      // } 
      
      // var theme = this.demoColor;
      // for(var i = 0; i < 5; i++){
      //   if(!this.demoFlag[i]){
      //     theme[i] = '';
      //   }
      // }
      this.AC_SELECTED_DEMO({theme : this.demoColor, flag: this.demoFlag });
      // this.AC_SELECTED_DEMO({theme: this.demoColor, flag: this.demoFlag});
      // if(index == 0){
      //   if(this.demoColor.backColor === ''){
      //     const backColor = 'rgb('+this.color1.r+', '+this.color1.g+', '+this.color1.b+')';
      //     this.demoColor.backColor = backColor;
      //     this.AC_DEMO_COLOR(this.demoColor);
      //   } else {
      //     this.demoColor.backColor = '';
      //     this.AC_DEMO_COLOR(this.demoColor);
      //   }
      // } else if(index == 1){
      //   if(this.demoColor.headColor === ''){
      //     const headColor = 'rgb('+this.color2.r+', '+this.color2.g+', '+this.color2.b+')';
      //     this.demoColor.headColor = headColor;
      //     this.AC_DEMO_COLOR(this.demoColor);
      //   } else {
      //     this.demoColor.headColor = '';
      //     this.AC_DEMO_COLOR(this.demoColor);
      //   }
      // } else if(index == 2){
      //   if(this.demoColor.subColor === ''){
      //     const subColor = 'rgb('+this.color3.r+', '+this.color3.g+', '+this.color3.b+')';
      //     this.demoColor.subColor = subColor;
      //     this.AC_DEMO_COLOR(this.demoColor);
      //   } else {
      //     this.demoColor.subColor = '';
      //     this.AC_DEMO_COLOR(this.demoColor);
      //   }
      // } else if(index == 3){
      //   if(this.demoColor.emphaColor === ''){
      //     const emphaColor = 'rgb('+this.color4.r+', '+this.color4.g+', '+this.color4.b+')';
      //     this.demoColor.emphaColor = emphaColor;
      //     this.AC_DEMO_COLOR(this.demoColor);
      //   } else {
      //     this.demoColor.emphaColor = '';
      //     this.AC_DEMO_COLOR(this.demoColor);
      //   }
      // } else if(index == 4){
      //   if(this.demoColor.quoteColor === ''){
      //   const quoteColor = 'rgb('+this.color5.r+', '+this.color5.g+', '+this.color5.b+')';
      //   this.demoColor.quoteColor = quoteColor;
      //   this.AC_DEMO_COLOR(this.demoColor);
      //   } else {
      //     this.demoColor.quoteColor = '';
      //     this.AC_DEMO_COLOR(this.demoColor);
      //   }
      // }
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
  /* background-color: cadetblue; */
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
  /* background-color: darkslateblue; */
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

.example-content{
  float: left;
  width: 70%;
  height: 100%;
  /* background-color: darksalmon; */
}

.example-palette{
  margin: 0;
  /* padding-top: 62vh; */
  padding-left: 3%;
  user-select: none;
}

.palette-content{
  cursor:pointer;
  width: 100%;
  height: 100%;
  /* background-color: cornflowerblue; */
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
  -webkit-filter: blur(0);
  transform: scale(1.2);
  transition-duration: 0.3s;
}

.animation-out{
  -webkit-filter: blur(0);
  transform: scale(1);
  transition-duration: 0.1s;
}

.palette-content .color-info .desc{
  font-size: 1.3rem;
}
</style>