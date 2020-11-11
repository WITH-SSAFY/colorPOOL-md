<template>
  <div>
    <v-col class="result-demo-area">
      <div class="info-section">
        <div class="progress-bar"><ProgressBar v-bind:page="4"></ProgressBar></div>
      </div>
      <div class="demo-section" style="overflow-y: scroll; width: 100%; height: 80vh">
        <div class="demo-page" v-for="(slide, index) in slides" :key="index">
          <div v-html="slide"></div>
        </div>
      </div>
    </v-col>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import ProgressBar from '../header/ProgressBar'
// import axios from '../../api/axiosCommon'
const pdfStore = 'pdfStore'

require('../../assets/DemoEditStyle.css')

export default {
  name: 'DemoResult',
  components: {
    ProgressBar
  },
  data() {
    return {
      slides: [],
      totalContent: '',
    }
  },
  computed: {
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
    document.querySelectorAll('.demo-page').forEach(node => {
      let color = node.firstChild.firstChild.style.backgroundColor;
      node.style.backgroundColor = color;
    })
  }
}
</script>

<style scoped>
  .result-demo-area.col {
    padding: 0;
  }

  .result-demo-area.col .info-section {
    margin: 0;
    height: 20vh;
    width: 77.5%;
    padding-left: 3rem;
  }

  .demo-page {
    position: relative;
    margin-left: 7.5%;
    width: 85%;
    margin-right: 0;
    box-shadow: 5px 5px 10px -5px rgba(0,0,0,0.75);
    margin-bottom: 20px;
  }

  .demo-page:before {
    content: "";
    display: block;
    padding-top: 56.25%;
  }

  .demo-page div {
    position: absolute;
    padding: 5% 10%;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    border: solid 2.5px #999;
  }


</style>