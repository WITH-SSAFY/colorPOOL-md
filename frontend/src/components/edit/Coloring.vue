<template>
  <div>
    <v-col class="editing-area">
      <div class="info-section">
        <div class="progress-bar"><progress-bar v-bind:page="3" /></div>
      </div>
      <section id="container"  class="editor" :class="'item' + index"  v-for="(item, index) in contents" :key="index">
        <div class="editor__content" v-html="item"></div>
      </section>
    </v-col>
  </div>
</template>

<script>
import ProgressBar from '../../components/header/ProgressBar'
import { mapGetters } from 'vuex'
require('../../assets/LiveEditStyle.css')
const contentStore = 'contentStore'
const customStore = 'customStore'

export default {
  name: 'Coloring',
  components: {
    ProgressBar
  },
  computed: {
    ...mapGetters(contentStore, {storeContents: 'GE_CONTENTS'}),
    ...mapGetters(customStore, {storeFinalTheme: 'GE_FINAL_THEME'})
  },
  data() {
    return {
      contents: [],
      colors: []
    }
  },
  created() {
    this.contents = this.storeContents;
    this.colors = this.storeFinalTheme;
    console.log(this.storeContents);
  }
}
</script>

<style scoped>
  .editing-area.col {
    padding: 0;
  }

  .editing-area.col .info-section {
    margin: 0;
    height: 20vh;
    padding-left: 3rem;
  }

  .editor {
    width: 100vw;
    height: 375px;
    margin-left: 20%;
    padding: 40px 80px;
    box-shadow: 8px 8px 23px 0px rgba(0,0,0,0.75);
  }
</style>