<template>
  <div class="slide-list-wrap">
    <draggable v-model="slides" draggable=".slide-wrap" :move="checkMove">
    <div class="slide-wrap" v-for="(slide, index) in slides" :key="index">
      <span class="slide-number" :style="{'color': selectedColor}">{{ index }}</span>
      <div class="slide-window" :class="'item' + index" :style="{'border-color': selectedColor}">
        <div v-html="slide"></div>
        <div class="slide-layer"></div>
      </div>
    </div>
    </draggable>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import draggable from 'vuedraggable'
const colorStore = 'colorStore'
const contentStore = 'contentStore'

require('../../assets/SlideEditStyle.css')

export default {
  name: 'ThemeSidebar',
  components: {
    draggable
  },
  computed:{
    ...mapGetters(colorStore, { storeSelectedColor: 'GE_SELECTED_COLOR'}),
    ...mapGetters(contentStore, { storeContents: 'GE_CONTENTS' }),
    ...mapGetters(contentStore, { storeChange: 'GE_CHANGE' }),
    slides: {
      get() {
        return this.storeContents;
      },
      set(val) {
        this.AC_CONTENTS_CHANGE(val);
      }
    }
  },
  data() {
    return {
      // slides: [],
      selectedColor: ''
    }
  },
  methods: {
    ...mapActions(contentStore, ['AC_CONTENTS_CHANGE', 'AC_CONTENTS']),
    checkMove(e){
      // console.log(e.draggedContext)
      // console.log(e.relatedContext)
      // const payload1 = {
      //   page: e.draggedContext.index,
      //   content: e.draggedContext.element 
      // };
      // const payload2 = {
      //   page: e.relatedContext.index,
      //   content: e.relatedContext.element 
      // };
      // this.AC_CONTENTS(payload1);
      // this.AC_CONTENTS(payload2);
      var color1 = document.querySelector('.editor__content.item' + e.draggedContext.index).style.backgroundColor;
      var color2 = document.querySelector('.editor__content.item' + e.relatedContext.index).style.backgroundColor;
      console.log(e);
      document.querySelector('.editor__content.item' + e.relatedContext.index + ' .ProseMirror').innerHTML = e.draggedContext.element;
      document.querySelector('.editor__content.item' + e.relatedContext.index).style.backgroundColor = color1;
      document.querySelector('.editor__content.item' + e.draggedContext.index + ' .ProseMirror').innerHTML = e.relatedContext.element;
      document.querySelector('.editor__content.item' + e.draggedContext.index).style.backgroundColor = color2;
      document.querySelector('.slide-window.item' + e.draggedContext.index + ' div').style.backgroundColor = color2;
      document.querySelector('.slide-window.item' + e.relatedContext.index + ' div').style.backgroundColor = color1;
      
    }
  },
  watch: {
    storeSelectedColor (val) {
      this.selectedColor = val;
    },
    storeChange : {
      handler() {
        this.slides = this.storeContents;
        this.slides.forEach((slide, index) => {
          document.querySelector('.slide-window.item' + index + ' div').innerHTML = slide;
          let color = document.querySelector('.slide-window.item' + index + ' div div').style.backgroundColor;
          document.querySelector('.slide-window.item' + index).style.backgroundColor = color;
          // document.querySelector('.slide-window .item' + index).innerHTML = slide;
        })
      }
    }
  },
  updated() {
    // this.slides = this.storeContents;
    // this.slides.forEach((slide, index) => {
    //   console.log(index, slide);
    // })
  },
  created() {
    this.selectedColor = this.storeSelectedColor;
    this.slides = this.storeContents;
  },
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
    /* display: inline;
    margin-left: 5%; */
    width: 100%;
    height: 100%;
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
    /* background-color: white; */
    /* background-image: blur(1.4); */
  }

</style>