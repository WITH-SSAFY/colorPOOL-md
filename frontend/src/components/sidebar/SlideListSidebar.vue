<template>
  <div class="slide-list-wrap">
    <div class="slide-wrap" v-for="(slide, index) in slides" :key="index">
      <span class="slide-number" :style="{'color': selectedColor}">{{ index }}</span>
      <div class="slide-window" :style="{'border-color': selectedColor}"></div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
const colorStore = 'colorStore'

export default {
  name: 'ThemeSidebar',
  computed:{
    ...mapGetters(colorStore, { storeSelectedColor: 'GE_SELECTED_COLOR'})
  },
  data() {
    return {
      slides: ['str', 'str'],
      selectedColor: ''
    }
  },
  watch: {
    storeSelectedColor (val) {
      this.selectedColor = val;
    }
  },
  created() {
    this.selectedColor = this.storeSelectedColor;
  }
}
</script>

<style scoped>
  html, body {
    height: 100%;
  }

  .slide-list-wrap{
    width: 100%;
    height: 100%;
    padding-top: 25px;
    border-right: 3px solid #707070;
    overflow: scroll;
  }

  .slide-list-wrap .slide-wrap {
    display: inline;
    margin-left: 5%;
  }

  .slide-list-wrap .slide-wrap .slide-num {
    text-align: left;
  }

  /* 
  슬라이드 창 비율 고정 [16:9]
  padding-top = 세로 / 가로 * 100
  */
  .slide-list-wrap .slide-wrap .slide-window {
    margin-left: 5%;
    margin-bottom: 2%;
    position: relative;
    width: 90%;
    border: 2px solid;
    background-color: white;
    cursor: pointer;
  }

  .slide-list-wrap .slide-wrap .slide-window:before {
    content: "";
    display: block;
    padding-top: 56.25%;
  }

</style>