<template>
  <div class="slide-list-wrap">
    <div class="slide-wrap" v-for="(slide, index) in slides" :key="index">
      <span class="slide-number d-flex align-center justify-center">{{ index }}</span>
      <div class="slide-window d-flex align-center justify-center"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ThemeSidebar',
  data() {
    return {
      slides: ['str', 'str'],
    }
  }
}
</script>

<style scoped>
  .slide-list-wrap{
    width: 100%;
    height: 100vh;
    padding-top: 25px;
    border-right: 3px solid #707070;
    overflow: scroll;
  }

  .slide-list-wrap .slide-wrap {
    /* display: inline; */
    text-align: left;
    margin-left: 0;
  }

  .slide-list-wrap .slide-wrap .slide-num {
    display: inline;
    text-align: left;
  }

  /* 
  슬라이드 창 비율 고정 [16:9]
  padding-top = 세로 / 가로 * 100
  */
  .slide-list-wrap .slide-wrap .slide-window {
    margin-left: 5%;
    margin-bottom: 25px;
    position: relative;
    width: 90%;
    border: 2px solid #707070;
    background-color: white;
    cursor: pointer;
  }

  .slide-list-wrap .slide-wrap .slide-window:before {
    content: "";
    display: block;
    padding-top: 56.25%;
  }

</style>