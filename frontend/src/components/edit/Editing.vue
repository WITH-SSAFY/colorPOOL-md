<template>
  <div>
    <v-col class="editing-area">
      <div class="info-section">
        <div class="progress-bar"><progress-bar v-bind:page="storePage + 2" /></div>
      </div>
      <EditPage v-for="(template, index) in templates" v-bind:page="index" @enterNewPage="createPage()" @exitNewPage="deletePage()" :key="index" v-bind:content_parent="template"></EditPage>
    </v-col>
    <button @click="sendPDF">보내기</button>
  </div>
</template>

<script>
  import EditPage from './EditPage'
  import ProgressBar from '../../components/header/ProgressBar'
  import axios from '../../api/axiosCommon'
  // import CustomStyle from "../../assets/CustomStyle";
  require('../../assets/LiveEditStyle.css')
  
  import { mapGetters } from 'vuex'

  const contentStore = 'contentStore'
  const customStore = 'customStore'
  const boxStore = 'boxStore'
  const editStore = 'editStore'

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
      }
    },
    computed : {
      ...mapGetters(contentStore, {storeContents: 'GE_CONTENTS'}),
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
      
      // setInterval(() => {
      //   document.querySelectorAll('.editor__content span').forEach(node => {
      //     if(node.classList.length != 0) {
      //       console.log(node);
      //       if(node.className == 'color1') node.style.color = this.colors[0];
      //       else if(node.className == 'color2') node.style.color = this.colors[1];
      //       else if(node.className == 'color3') node.style.color = this.colors[2];
      //       else if(node.className == 'color4') node.style.color = this.colors[3];
      //       else if(node.className == 'color5') node.style.color = this.colors[4];
      //     }
      //     node.classList = [];
      //   })
      // }, 3000);
    },
    methods: {
      createPage() {
        this.templates.push(EditPage)
      },
      deletePage() {
        this.templates.pop(EditPage)
      },
      handleDrag() {
        // if(!e.target.parentElement.className.includes('ProseMirror')) return;
        // console.log(e);
        // this.isToolBoxShow = true;
        // e.target.style.color="red"
      },
      /* TEST PDF */
      sendPDF() {
        console.log(this.templates)
        let str = ""
        this.templates.forEach(template => {
          template = template.replaceAll('"', "'");
          template = template.replaceAll('<br>', '<br/>');
          str += template;
        })
        console.log(str);
        // const payload = {
        //   path : '',
        //   contents: str
        // }
        axios.post('/pdf', {'contents': str}).then((res) => console.log(res)).catch((err) => console.log(err));
      }
    },
    watch: {
      storeIsBox (val) {
        this.isToolBoxShow = val;
      },
      storePage () {

      }
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