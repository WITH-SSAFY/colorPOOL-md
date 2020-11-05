<template>
  <div>
    <v-col class="editing-area">
      <div class="info-section">
        <div class="progress-bar"><progress-bar v-bind:page="2" /></div>
      </div>
      <EditPage v-for="(template, index) in templates" v-bind:page="index" @enterNewPage="createPage()" @exitNewPage="deletePage()" :key="index" v-bind:content_parent="template"></EditPage>
    </v-col>
  </div>
</template>

<script>
  import EditPage from './EditPage'
  import ProgressBar from '../../components/header/ProgressBar'
  require('../../assets/LiveEditStyle.css')
  
  import { mapGetters } from 'vuex'

  const contentStore = 'contentStore'

  export default {
    name: 'Editing',
    components: {
      ProgressBar, EditPage
    },
    data() {
      return {
        templates: [],
      }
    },
    computed : {
      ...mapGetters(contentStore, {storeContents: 'GE_CONTENTS'})
    },
    created() {
      this.templates = this.storeContents;
      // this.templates.push(EditPage)
    },
    methods: {
      createPage() {
        this.templates.push(EditPage)
      },
      deletePage() {
        this.templates.pop(EditPage)
      }
    },
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
</style>