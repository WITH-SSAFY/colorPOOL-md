<template>
  <div>
    <v-col class="example-area">
      <!-- 상단 toggle 버튼-->
      <div class="toggle-button-group">
        <button @click="goPickColor">Color with Team-Color</button>
        <button @click="goGetColor">Color with Main-Keyword</button>
      </div>

      <!--컨셉 컨텐츠 -->
      <play-content/>

      <div class="indicator" :style="{'background-color': color1 }">
        <div class="arrow arrow-first"></div>
        <div class="arrow arrow-second"></div>
        <button class="getting-started" :style="{'background-color': color3 }" @click="goTutorial">Getting Started</button>
      </div>

      <!-- exapmle ui와 버튼 -->
      <div class="example-md" :style="{'background-color': color1 }">
        <div class="example-content"><example-content/></div>
        <!-- 다음 단계로 넘어가는 버튼-->
        <div class="button-group">
          <h1>Create Presentation</h1>
          <div type="button" class="button" @click="goPickColor">with Team Color</div>
          <div type="button" class="button" @click="goGetColor">with Main Keyword</div>
        </div>
      </div>

      <!-- 선택한 theme 팔레트에 표시-->
      <div class="example-palette">
        <div class="palette-content">
          <h1 style="margin: 0;">Palettes in Markdown</h1>
          <div class="color-info d-flex align-center" @mouseover="overColor(0)" @mouseout="outColor(0)" @click="[applyColor(0), activeColor(0)]">
            <div class="color" :style="{'background-color': color1 }"></div>
            <div class="desc">Background</div>
          </div>
          <div class="color-info d-flex align-center" @mouseover="overColor(1)" @mouseout="outColor(1)" @click="[applyColor(1), activeColor(1)]">
            <div class="color" :style="{'background-color': color2 }"></div>
            <div class="desc">Headline</div>
          </div>
          <div class="color-info d-flex align-center" @mouseover="overColor(2)" @mouseout="outColor(2)" @click="[applyColor(2), activeColor(2)]">
            <div class="color" :style="{'background-color': color3 }"></div>
            <div class="desc">Sub headline</div>
          </div>
          <div class="color-info d-flex align-center" @mouseover="overColor(3)" @mouseout="outColor(3)" @click="[applyColor(3), activeColor(3)]">
            <div class="color" :style="{'background-color': color4 }"></div>
            <div class="desc">Emphasis</div>
          </div>
          <div class="color-info d-flex align-center" @mouseover="overColor(4)" @mouseout="outColor(4)" @click="[applyColor(4), activeColor(4)]">
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
import PlayContent from './PlayContent'
import { mapGetters, mapActions } from 'vuex'
const demoStore = 'demoStore'
const landingStore = 'landingStore'

export default {
  name: 'Example',
  components: {
    ExampleContent,
    PlayContent,
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
  mounted() {
    [...Array(5).keys()].forEach(index => this.activeColor(index))
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
    goGetColor () {
      this.AC_IS_PICK(false);
      this.AC_IS_GET(true);
      this.$router.push({name: 'Select'});
    },
    goPickColor () {
      this.AC_IS_PICK(true);
      this.AC_IS_GET(false);
      this.$router.push({name: 'Select'});
    },
    goTutorial () {
      document.querySelector('.example-md').scrollIntoView({behavior: "smooth"})
      document.querySelector('.color-info').classList.add("bounce-start")
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
    applyColor(index) {
      this.flagDemoTheme[index] = !this.flagDemoTheme[index];
      this.AC_FLAG_DEMO_THEME(this.flagDemoTheme);
    },
    activeColor(index) {
      var targetColor = document.querySelectorAll('.color-info .color');
      if (this.flagDemoTheme[index]) {
        targetColor[index].classList.add("active-color");
      } else {
        targetColor[index].classList.remove("active-color");
      }
    }
  }
}
</script>

<style scoped>
  .example-area.col{
    padding: 0;
    transition-duration: 400ms;
  }

  .toggle-button-group {
    display: flex;
    justify-content: flex-end;    
    padding: 0 5%;
    height: 8vh;
    width: 100%;
    background-color: #232a46;
  }

  .toggle-button-group button {
    font-size: 1.3rem;
    font-weight: bolder;
    padding: 0 2rem;
    color: #ee7771;
    display: inline;
  }

  .example-md {
    margin: 0;
    padding: 0 2%;
    padding-bottom: 2%;
    height: 67vh;
    width: 100%;
    overflow: scroll;
  }

  .example-md:after {
    display: block;
    content: "";
    clear: both;
  }

  .example-md .button-group {
    display: flex;
    justify-content: flex-end;
    flex-direction: column;
    width: 27.5%;
    height: 100%;
    padding-left: 2%;
    user-select: none;
  }

  .example-md .button-group .button {
    height: 70px;
    width: 95%;
    margin-bottom: 5%;
    padding: 5%;
    background-color: #ee7771;
    font-size: 1.5rem;
    font-weight: bolder;
    color: #232a46;
    box-shadow:inset 2px 2px 2px 0px rgba(255,255,255,.5),
    7px 7px 20px 0px rgba(0,0,0,.1),
    4px 4px 5px 0px rgba(0,0,0,.1);
  }

  .example-md .button-group .button:hover {
    background-color: #d16a64;
  }

  .example-md .example-content {
    float: left;
    width: 70%;
    height: 100%;
  }

  .example-palette {
    margin: 0;
    padding-top: 2%;
    padding-left: 3%;
    user-select: none;
  }

  .example-palette .palette-content {
    cursor: pointer;
    width: 90%;
    height: 90%;
  }

  .example-palette .palette-content:after {
    display: block;
    content: "";
    clear: both; 
  }

  .example-palette .palette-content .color-info {
    border: 4px solid #707070;
    float: left;
    height: 90px;
    width: 30%;
    margin-right: 3%;
    margin-top: 10px;
    margin-bottom: 10px;
  }

  .example-palette .palette-content .color-info .color {
    height: 50px;
    width: 50px;
    border-radius: 50%;
    border-bottom: 5px solid rgba(0, 0, 0, 0.1);
    box-shadow: 0 2px 1px rgba(0, 0, 0, 0.2);
    /* border: 4px solid #707070; */
    margin-left: 8%; 
    margin-right: 3%;
  }

  .example-palette .palette-content .color-info .color:hover {
    -webkit-transform: scale(1.2);
    transform: scale(1.2);
    border-bottom: 10px solid rgba(0, 0, 0, 0.15);
    box-shadow: 0 10px 10px rgba(0, 0, 0, 0.2);
  }

  .bounce-start {
    -webkit-animation: bounce 2s 1s ease-in-out;
        animation: bounce 2s 1s ease-in-out;
  }

  .animation-in {
    transform: scale(1.2);
    transition-duration: 0.15s;
  }

  .animation-out {
    transform: scale(1);
    transition-duration: 0.1s;
  }

  .active-color {
    -webkit-transform: scale(1.2) translateY(-5px);
    transform: scale(1.2) translateY(-5px);
    box-shadow: 0 10px 10px rgba(0, 0, 0, 0.2);
    border-bottom: 30px solid rgba(0, 0, 0, 0.15);
  }

  .palette-content .color-info .desc{
    font-size: 1.3rem;
  }

  /*
  =======================================================
  스크롤 유도 화살표
  =======================================================
  */
  .arrow {
    opacity: 0;
    position: absolute;
    right: 5%;
    top: 70%;
    -webkit-transform-origin: 50% 50%;
            transform-origin: 50% 50%;
    -webkit-transform: translate3d(-50%, -50%, 0);
            transform: translate3d(-50%, -50%, 0);
  }

  .arrow-first {
    -webkit-animation: arrow-movement 2s ease-in-out infinite;
            animation: arrow-movement 2s ease-in-out infinite;
  }

  .arrow-second {
    -webkit-animation: arrow-movement 2s 1s ease-in-out infinite;
            animation: arrow-movement 2s 1s ease-in-out infinite;
  }

  .arrow:before,
  .arrow:after {
    background: white;
    content: '';
    display: block;
    height: 3px;
    position: absolute;
    top: 0;
    left: 0;
    width: 30px;
  }

  .arrow:before {
    -webkit-transform: rotate(45deg) translateX(-23%);
            transform: rotate(45deg) translateX(-23%);
    -webkit-transform-origin: top left;
            transform-origin: top left;
  }

  .arrow:after {
    -webkit-transform: rotate(-45deg) translateX(23%);
            transform: rotate(-45deg) translateX(23%);
    -webkit-transform-origin: top right;
            transform-origin: top right;
  }

  @-webkit-keyframes arrow-movement {
    0% {
      opacity: 0;
      top: 65%;
    }
    70% {
      opacity: 1;
    }
    100% {
      opacity: 0;
    }
  }

  @keyframes arrow-movement {
    0% {
      opacity: 0;
      top: 65%;
    }
    70% {
      opacity: 1;
    }
    100% {
      opacity: 0;
    }
  }

  /*
  =======================================================
  Getting Started 버튼
  =======================================================
  */

  .indicator {
    padding-top: 3%;
    text-align: center;
  }

  .indicator button {
    position: relative;
    display: inline-block;
    cursor: pointer;
    outline: none;
    border: 0;
    vertical-align: middle;
    text-decoration: none;
    font-size: 1.5rem;
    font-family: inherit;
  }

  .indicator button.getting-started {
    font-weight: bolder;
    color: #382b22;
    text-transform: uppercase;
    padding: 1.25em 2em;
    /* background: #fff0f0; */
    border: 2px solid #b18597;
    border-radius: 0.75em;
    -webkit-transform-style: preserve-3d;
            transform-style: preserve-3d;
    -webkit-transition: background 150ms cubic-bezier(0, 0, 0.58, 1), -webkit-transform 150ms cubic-bezier(0, 0, 0.58, 1);
    transition: background 150ms cubic-bezier(0, 0, 0.58, 1), -webkit-transform 150ms cubic-bezier(0, 0, 0.58, 1);
    transition: transform 150ms cubic-bezier(0, 0, 0.58, 1), background 150ms cubic-bezier(0, 0, 0.58, 1);
    transition: transform 150ms cubic-bezier(0, 0, 0.58, 1), background 150ms cubic-bezier(0, 0, 0.58, 1), -webkit-transform 150ms cubic-bezier(0, 0, 0.58, 1);
    -webkit-animation: rubberBand 2s 1s ease-in-out;
        animation: rubberBand 2s 1s ease-in-out;
  }

  .indicator button.getting-started::before {
    position: absolute;
    content: '';
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(255, 227, 226, .5);
    border-radius: inherit;
    box-shadow: 0 0 0 2px #b18597, 0 0.625em 0 0 rgba(255, 227, 226, .5);
    -webkit-transform: translate3d(0, 0.75em, -1em);
            transform: translate3d(0, 0.75em, -1em);
    -webkit-transition: box-shadow 150ms cubic-bezier(0, 0, 0.58, 1), -webkit-transform 150ms cubic-bezier(0, 0, 0.58, 1);
    transition: box-shadow 150ms cubic-bezier(0, 0, 0.58, 1), -webkit-transform 150ms cubic-bezier(0, 0, 0.58, 1);
    transition: transform 150ms cubic-bezier(0, 0, 0.58, 1), box-shadow 150ms cubic-bezier(0, 0, 0.58, 1);
    transition: transform 150ms cubic-bezier(0, 0, 0.58, 1), box-shadow 150ms cubic-bezier(0, 0, 0.58, 1), -webkit-transform 150ms cubic-bezier(0, 0, 0.58, 1);
  }

  .indicator button.getting-started:hover {
    background: rgba(255, 227, 226, .5);
    -webkit-transform: translate(0, 0.25em);
            transform: translate(0, 0.25em);
  }

  .indicator button.getting-started:hover::before {
    box-shadow: 0 0 0 2px #b18597, 0 0.5em 0 0 rgba(255, 227, 226, .5);
    -webkit-transform: translate3d(0, 0.5em, -1em);
            transform: translate3d(0, 0.5em, -1em);
  }

  .indicator button.getting-started:active {
    background: rgba(255, 227, 226, .5);
    -webkit-transform: translate(0em, 0.75em);
            transform: translate(0em, 0.75em);
  }

  .indicator button.getting-started:active::before {
    box-shadow: 0 0 0 2px #b18597, 0 0 rgba(255, 227, 226, .5);
    -webkit-transform: translate3d(0, 0, -1em);
            transform: translate3d(0, 0, -1em);
  }

  /*
  =======================================================
  Bounce 애니메이션
  =======================================================
  */

  @keyframes bounce {
    from,
    20%,
    53%,
    to {
      animation-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);
      transform: translate3d(0, 0, 0);
    }

    40%,
    43% {
      animation-timing-function: cubic-bezier(0.755, 0.05, 0.855, 0.06);
      transform: translate3d(0, -30px, 0) scaleY(1.1);
    }

    70% {
      animation-timing-function: cubic-bezier(0.755, 0.05, 0.855, 0.06);
      transform: translate3d(0, -15px, 0) scaleY(1.05);
    }

    80% {
      transition-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);
      transform: translate3d(0, 0, 0) scaleY(0.95);
    }

    90% {
      transform: translate3d(0, -4px, 0) scaleY(1.02);
    }
  }

  @keyframes rubberBand {
    from {
      transform: scale3d(1, 1, 1);
    }

    30% {
      transform: scale3d(1.25, 0.75, 1);
    }

    40% {
      transform: scale3d(0.75, 1.25, 1);
    }

    50% {
      transform: scale3d(1.15, 0.85, 1);
    }

    65% {
      transform: scale3d(0.95, 1.05, 1);
    }

    75% {
      transform: scale3d(1.05, 0.95, 1);
    }

    to {
      transform: scale3d(1, 1, 1);
    }
  }


</style>