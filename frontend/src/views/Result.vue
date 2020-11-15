<template>
  <div>
    <v-row class="edit wrap">
      <div class="slide-sidebar wrap"><slide-list-sidebar/></div>
      <div class="demo-result-area wrap"><DemoResult/></div> 
      <div class="button-group">
        <div type="button" class="button" v-if="!storeIsSent" @click="sendServer">Save to Server</div>
        <div type="button" class="button" v-if="storeIsSent" @click="showPDF">Export to pdf</div>
        <div type="button" class="button" v-if="storeIsSent" @click="copyMD">Copy My Markdown</div>
        <div type="button" class="button" v-if="storeIsSent" @click="copyLink">Copy My Link</div>
        <div type="button" class="button" v-if="storeIsSent" @click="openViewer">Open in Viewer</div>
      <input id="md_reader" style="user-select: none; opacity: 0;" v-model="storeContents" type="text"/>
      <input id="link_reader" style="user-select: none; opacity: 0;" v-model="url" type="text"/>
      </div>
    </v-row>
  </div>
</template>

<script>
import SlideListSidebar from '../components/sidebar/FinalSlideListSidebar'
import DemoResult from '../components/result/DemoResult'
import { mapActions, mapGetters } from 'vuex'

const pdfStore = 'pdfStore'

export default {
  name: 'Result',
  components: {
    SlideListSidebar, DemoResult
  },
  computed: {
    ...mapGetters(pdfStore, {storeIsSent : 'GE_IS_SENT', storeContents: 'GE_CONTENTS', storePdfUrl : 'GE_PDF_URL', storeLink : 'GE_LINK_URL'})
  },
  data() {
    return {
      url: '',
    }
  },
  created() {

  },
  mounted() {
    this.url = 'http://k3a501.p.ssafy.io/viewer/' + this.storeLink;
  },
  watch: {
    storeIsSent(){},
    storeContents(){},
    storePdfUrl(val){console.log(val)},
    storeLink(){}
  },
  methods: {
    ...mapActions(pdfStore, ['AC_PDF_URL']),
    sendServer  () {
      const payload = {
        'contents': this.storeContents
      }
      this.AC_PDF_URL(payload)
    },
    showPDF () {
      window.open(this.storePdfUrl)
    },
    copyMD () {
      console.log(document.getElementById('md_reader').value)
      document.getElementById('md_reader').select();
      document.execCommand('copy')
      alert('클립보드에 복사되었습니다')
    },
    copyLink() {
      document.getElementById('link_reader').select();
      document.execCommand('copy')
      alert('클립보드에 복사되었습니다')
    },
    openViewer() {
      window.open('/viewer/' + this.storeLink, 'window','location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, width=1920,height=1080,left=0, top=0, scrollbars=yes');
    }
  },
}
</script>

<style scoped>
  .edit.wrap {
    width: 100%;
    height: 100vh;
    margin: 0;
    padding: 0;
  }

  .edit.wrap .slide-sidebar.wrap {
    background-color: #eeeeee;
    width: 22.5%;
  }


  .demo-result-area.wrap {
    background-color: rgba(238, 238, 238, 0.7);
    width: 50%;
  }

  .edit.wrap .button-group{
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    width: 27.5%;
    height: 100%;
    user-select: none;
    background-color: rgba(238, 238, 238, 0.7);
  }

  .edit.wrap .button-group .button{
    height: 70px;
    width: 80%;
    margin-bottom: 5%;
    padding: 4%;
    background-color: #ee7771;
    font-size: 1.5rem;
    font-weight: bolder;
    color: #232a46;
    box-shadow:inset 2px 2px 2px 0px rgba(255,255,255,.5),
    7px 7px 20px 0px rgba(0,0,0,.1),
    4px 4px 5px 0px rgba(0,0,0,.1);
  }

  .edit.wrap .button:hover{
    background-color: #d16a64;
  }
</style>