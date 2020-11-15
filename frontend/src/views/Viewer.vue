<template>
  <div class="viewer-wrap">
    <v-carousel class="viewer-carousel" height="100%"  hide-delimiters>
      <v-carousel-item style="width: 100%; height: 100%;" class="viewer-carousel-item" v-for="(slide, index) in slides" :key="index">
        <div v-html="slide"></div>
      </v-carousel-item>
    </v-carousel>
  </div>
</template>

<script>
import axios from '../api/axiosCommon'
require('../assets/ViewEditStyle.css')

export default {
  name: 'Viewer',
  props: {
    id: {
      deafult: void 0
    }
  },
  data() {
    return {
      slides: [],
      original : '',
      handler: null,
    }
  },
  created() {
    console.log(this.id)
    axios.get('contents/' + this.id)
    .then((res) => {
      console.log(res);
      this.original = res.data.data;
      this.updateSlides();
    })
    .catch(err => {
      console.log(err)
    })
  },
  mounted () {
    this.handler = window.addEventListener('keydown', (e) => {
      if(e.code == 'ArrowLeft' || e.code == 'ArrowUp') {
        document.querySelector('.v-window__prev button').click();
      }
      if(e.code == 'ArrowRight' || e.code == 'ArrowDown') {
        document.querySelector('.v-window__next button').click();
      }
    })
    document.querySelector('.v-window__prev').style.display = 'None'
    document.querySelector('.v-window__next').style.display = 'None'
  },
  beforeDestroy() {
    window.removeEventListener('keydown', this.handler);
  },
  methods: {
    updateSlides () {
      var list = this.original.split('</div>');
      list.pop();
      list.forEach(str => {
        str = str.replace("<div style='", "<div style='padding: 100px 150px;height: 100vh; " )
        this.slides.push(str + '</div>')
      })
      console.log(this.slides)
    }
  }
}
</script>

<style scoped>
  .viewer-wrap {
    width: 100vw;
    height: 100vh;
  }

  .viewer-wrap .viewer-carousel {
    width: 100%;
    height: 100%;
  }
  .viewer-carousel.v-item-group.theme--dark.v-carousel {
    height: 100%;
  }

  .viewer-wrap .viewer-carousel-item {
    width: 100%;
    height: 100%;
  }

  .viewer-carousel-item  div {
    width: 100vw;
    height: 100vh;
  }
</style>