<template>
  <div>
    <v-col class="editing-area">
      <div class="info-section">
        <div class="progress-bar"><progress-bar v-bind:page="storePage + 2" /></div>
      </div>
      <EditPage v-for="(template, index) in templates" v-bind:page="index" @enterNewPage="createPage()" @exitNewPage="deletePage()" :key="index" v-bind:content_parent="template"></EditPage>
    </v-col>
  </div>
</template>

<script>
  import EditPage from './EditPage'
  import ProgressBar from '../../components/header/ProgressBar'
  // import axios from '../../api/axiosCommon'
  require('../../assets/LiveEditStyle.css')
  
  import { mapActions, mapGetters } from 'vuex'

  const contentStore = 'contentStore'
  const customStore = 'customStore'
  const boxStore = 'boxStore'
  const editStore = 'editStore'
  const pdfStore = 'pdfStore'

  export default {
    name: 'Editing',
    components: {
      ProgressBar, EditPage
    },
    data() {
      return {
        templates: [],
        isToolBoxShow: false,
        colors: [],
        parent: 0,
        handler: null,
        handler2: null,
      }
    },
    computed : {
      ...mapGetters(contentStore, {storeContents: 'GE_CONTENTS', storeChange: 'GE_CHANGE', storeChangeAll: 'GE_CHANGE_ALL'}),
      ...mapGetters(boxStore, {storeIsBox: 'GE_IS_BOX'}),
      ...mapGetters(customStore, {storeFinalTheme: 'GE_FINAL_THEME'}),
      ...mapGetters(editStore, {storePage: 'GE_PAGE'}),
    },
    created() {
      this.templates = this.storeContents;
      if(this.templates.length == 0) {
        this.templates.push(EditPage)
      }
      this.colors = this.storeFinalTheme;
    },
    mounted() {
      document.addEventListener('click', this.handleDrag);
      console.log(this.templates);
      this.handler = setInterval(() => {
        this.setFinalContents();
      }, 3000);

      this.handler2 = document.addEventListener('keydown', (e) => {
        if(e.altKey && e.keyCode == 13) this.createPage();
        if(e.altKey && e.key == 'Backspace') this.deletePage();
      })
    },
    beforeDestroy() {
      clearInterval(this.handler)
      clearInterval(this.handler2)
    },
    methods: {
      ...mapActions(pdfStore,['AC_CONTENTS']),
      createPage() {
        this.templates.push(EditPage)
      },
      deletePage() {
        this.templates.pop(EditPage)
      },
      handleDrag() {
      },
      setFinalContents() {
        let str = ""
        this.templates.forEach(template => {
          template = template.replaceAll('"', "'");
          template = template.replaceAll('<br>', '<br/>');
          template = template.replaceAll('<hr>', '<hr/>');
          let i = 0;
          // console.log(template.indexOf('<img', 42))
          while(template.indexOf('<img', i) != -1) {
            i = template.indexOf('<img', i);
            for(var j = i; j < template.length; j++) {
              if(template[j] == '>') {
                template = template.substring(0, j) + '/' + template.substring(j)
                i = j;
                break;
              }
            }
          }
          str += template;
        })
        this.AC_CONTENTS(str);
      }
    },
    watch: {
      storeIsBox (val) {
        this.isToolBoxShow = val;
      },
      storePage () {
        
      },
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


/* =================== TOOL BOX COLOR =================== */
  .toolbox {
    position: absolute;
    width: 200px;
    height: 40px;
    opacity: 0;
    visibility: hidden;
    transition-duration: 300ms;
  }

  .toolbox.show {
    opacity: 1;
    z-index: 95;
    visibility: visible;
  }

  .toolbox button {
    border: 2px solid slategray;
  }
/* ====================================================== */
</style>
