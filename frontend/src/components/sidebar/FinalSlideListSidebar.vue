<template>
  <div class="slide-list-wrap">
    <div class="slide-wrap" v-for="(slide, index) in slides" :key="index">
      <span class="slide-number" :style="{'color': selectedColor}">{{ index }}</span>
      <div class="slide-window" :class="'item' + index" :style="{'border-color': selectedColor}">
        <div v-html="slide"></div>
        <div class="slide-layer" @click="goSlide(index)"></div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
const colorStore = 'colorStore'
const pdfStore = 'pdfStore'

require('../../assets/SlideEditStyle.css')

export default {
  name: 'FinalSlideListSidebar',
  components: {
    
  },
  data() {
    return {
      selectedColor: '',
      slides: [],
    }
  },
  computed: {
    ...mapGetters(colorStore, { storeSelectedColor: 'GE_SELECTED_COLOR'}),
    ...mapGetters(pdfStore, {storeContents : 'GE_CONTENTS'})
  },
  mounted() {
    this.totalContent = this.storeContents;
    var list = this.totalContent.split('</div>');
    list.pop();
    list.forEach((str) => {
      this.slides.push(str + '</div>')
    });
  },
  updated() {
    document.querySelectorAll('.slide-window').forEach(node => {
      console.log(node);
      let color = node.firstChild.firstChild.style.backgroundColor;
      node.style.backgroundColor = color;
    })
  },
  methods: {
    goSlide(index) {
      const elem = document.querySelector('.demo-page:nth-child(' + (index + 1) + ')');
      document.querySelector('.demo-section').scrollTo({
        top: elem.offsetTop - (elem.clientHeight / 1.2),
        behavior: 'smooth'
      })
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
    width: 100%;
  }

  .slide-list-wrap .slide-wrap .slide-num {
    text-align: left;
  }

  .slide-list-wrap .slide-wrap .slide-window {
    margin-left: 5%;
    margin-bottom: 2%;
    position: relative;
    width: 90%;
    border: 2px solid;
    background-color: white;
    cursor: pointer;
    overflow: hidden;
    
  }


  .slide-list-wrap .slide-wrap .slide-window:after {
    content: "";
    display: block;
    padding-top: 56.25%;
  }

  .slide-window div {
    position: absolute;
    padding: 5% 10%;
    top: 0;
    right: 0;
    left: 0;
    bottom: 0;
    word-wrap: break-word;
    z-index: 95;
  }


  .slide-window div div p img {
    max-height: 100px;
  }

  .slide-window .slide-layer {
    width: 100%;
    height: 100%;
    z-index: 96;
    user-select: none;
  }
</style>