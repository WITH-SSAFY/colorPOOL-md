<template>
  <div>
    <!-- 프로그레스 바 -->
    <div class="progress">
      <div class="circle" :class="[page == 1 ? 'active' : '', page > 1 ? 'done': '']" @click="goPickColor">
        <span class="label">1</span><br>
        <span class="title">{{ landText }}</span>
      </div>
      <span class="bar"></span>
      <div class="circle" :class="[page == 2 ? 'active' : '', page > 2 ? 'done': '']" @click="goEditing">
        <span class="label">2</span><br>
        <span class="title" style="margin-left: -50px;">Create Markdown</span>
      </div>
      <span class="bar"></span>
      <div class="circle" :class="[page == 3 ? 'active' : '', page > 3 ? 'done': '']" @click="goColoring">
        <span class="label">3</span><br>
        <span class="title" style="margin-left: -15px;">Coloring</span>
      </div>
      <span class="bar"></span>
      <div class="circle" :class="[page == 4 ? 'active' : '', page > 4 ? 'done': '']" @click="goFinal">
        <span class="label">4</span><br>
        <span class="title" style="margin-left: -5px;">Final</span>
      </div>
    </div>
    
    <!-- editPage : 최종 선택한 배색-->
    <div v-if="page == 2 || page == 3" class="theme-wrap" style="background-color: white; margin-top: 2%;">
      <div class="theme color1" :style="{'background-color' : storeFinalTheme[0]}"></div>
      <div class="theme color2" :style="{'background-color' : storeFinalTheme[1]}"></div>
      <div class="theme color3" :style="{'background-color' : storeFinalTheme[2]}"></div>
      <div class="theme color4" :style="{'background-color' : storeFinalTheme[3]}"></div>
      <div class="theme color5" :style="{'background-color' : storeFinalTheme[4]}"></div>
    </div>

  </div>

</template>

<script>
import { mapGetters, mapActions } from 'vuex'
const landingStore = 'landingStore'
const customStore = 'customStore'
export default {
  props: {
    page: {
      default: void 0
    }
  },
  computed: {
    ...mapGetters(landingStore, {storeIsPick: 'GE_IS_PICK', storeIsGet: 'GE_IS_GET'}),
    ...mapGetters(customStore, {storeFinalTheme: 'GE_FINAL_THEME'})
  },
  data() {
    return {
      // page: 0,
      landText : '',
    }
  },
  created() {
    console.log(this.page);
    if(this.page == 1) {
      if(this.storeIsPick) { 
        this.landText = 'Pick Your Color'
      }
      if(this.storeIsGet) {
        this.landText = 'Get Your Color'
      }
    } else {
      this.landText = 'Select Your Color'
    }
  },
  methods: {
    ...mapActions(landingStore, ['AC_IS_PICK', 'AC_IS_GET']),
    goPickColor () {
      this.AC_IS_PICK(true);
      this.AC_IS_GET(false);
      this.$router.push({name: 'Select'});
    },
    goEditing () {
      this.$router.push({name: 'Edit'});
    },
    goColoring () {
      this.$router.push({name: 'Edit'});
    },
    goFinal () {
      this.$router.push({name: 'Result'});
    },
  },
  watch : {
    storeIsPick(val) {
      if(val) this.landText = 'Pick Your Color'
    },
    storeIsGet(val) {
      if(val) this.landText = 'Get Your Color'
    }
  }
}
</script>

<style scoped>
*, *:after, *:before {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  white-space: nowrap;
}

/* Form Progress */
.progress {
  position : absolute;
  top : 6%;
  width: 50%;
  /* width: 1000px; */
  /* margin: 20px auto; */
  text-align: center;
  word-wrap: normal;
}

.progress .circle,
.progress .bar {
  display: inline-block;
  background: #fff;
  width: 40px; height: 40px;
  border-radius: 40px;
  border: 2px solid #d5d5da;
  cursor: pointer;
}
.progress .bar {
  position: relative;
  width: 150px;
  height: 10px;
  top: -33px;
  margin-left: 0px;
  margin-right: 0px;
  border-left: none;
  border-right: none;
  border-radius: 0;
}
.progress .circle .label {
  display: inline-block;
  width: 32px;
  height: 32px;
  line-height: 32px;
  border-radius: 32px;
  margin-top: 2px;
  color: #b5b5ba;
  font-size: 1.3rem;
  font-weight: bold;
}

.progress .circle:nth-child(2) .title {
  margin-left: -100px;
}

.progress .circle:nth-child(1) .title {
  margin-left: -50px;
}
.progress .circle .title {
  color: #b5b5ba;
  font-size: 10px;
  line-height: 30px;
  /* margin-left: -15px; */
}


/* Done / Active */
.progress .bar.done,
.progress .circle.done {
  background: #eee;
}
.progress .bar.active {
  background: linear-gradient(to right, #EEE 40%, #FFF 60%);
}
.progress .circle.done .label {
  color: #FFF;
  background: #e0e0e0;
  box-shadow: inset 0 0 2px rgba(0,0,0,.2);
  font-size: 1.3rem;
  font-weight: bold;
}
.progress .circle.done .title {
  color: #707070;
}
.progress .circle.active .label {
  color: #FFF;
  background: #ee7771;
  box-shadow: inset 0 0 2px rgba(0,0,0,.2);
  font-size: 1.3rem;
  font-weight: bold;
}
.progress .circle.active .title {
  color: #ee7771;
}

/* 최종선택배색 */
.theme-wrap {
  position: absolute;
  right: 5%;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 17%;
  height: 105px;
  border: 3.5px solid #707070;
  cursor:pointer;
}

.theme-wrap .theme {
  width: 65px;
  height: 65px;
  border-radius: 75px;
  border: 3.5px solid #707070;
  transition-duration: 300ms;
}

.theme-wrap .theme.color1{
  z-index: 50;
}

.theme-wrap .theme.color2{
  margin-left: -30px;
  z-index: 51;
}

.theme-wrap .theme.color3{
  margin-left: -30px;
  z-index: 52;
}

.theme-wrap .theme.color4{
  margin-left: -30px;
  z-index: 53;
}

.theme-wrap .theme.color5{
  margin-left: -30px;
  z-index: 54;
}


</style>