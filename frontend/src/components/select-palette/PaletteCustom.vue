<template>
  <div>
    <v-dialog
      v-model="dialog"
      width="800"
      persistent
    >
      <v-btn @click="close">
        close
      </v-btn>
      
      <div class="theme-wrap d-flex align-center justify-center" :style="{'background-color' : 'rgb(255, 255, 255)'}">
        <div class="theme color1" :style="{'background-color' : demoColor[0]}"></div>
        <div class="theme color2" :style="{'background-color' : demoColor[1]}"></div>
        <div class="theme color3" :style="{'background-color' : demoColor[2]}"></div>
        <div class="theme color4" :style="{'background-color' : demoColor[3]}"></div>
        <div class="theme color5" :style="{'background-color' : demoColor[4]}"></div>
      </div>

    </v-dialog>

  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
const landingStore = 'landingStore'
const colorStore = 'colorStore'

export default {
  name: 'PaletteCustom',
  computed:{
    ...mapGetters( landingStore, {storeDialog: 'GE_DIALOG'}),
    ...mapGetters( colorStore, {storeDemoColor: 'GE_DEMO_COLOR'})
  },
  data(){
    return{
      dialog: false,
      demoColor: [],
    }
  },
  watch: {
    storeDialog(val){
      this.dialog = val;
    },
    storeDemoColor(val){
      this.demoColor = val;
    }
  },
  created(){
    this.dialog = this.storeDialog;
  },
  destroyed(){
    this.AC_DIALOG(false)
  },
  methods: {
    ...mapActions(landingStore, ['AC_DIALOG']),
    close(){
      this.AC_DIALOG(false);
    }
  }
}
</script>

<style scoped>
.theme-wrap{
  width: 50%;
  height: 105px;
  border: 4px solid #707070;
}

.theme {
  width: 65px;
  height: 65px;
  border-radius: 75px;
  border: 4px solid #707070;
}

.theme.color1{
  z-index: 50;
}

.theme.color2{
  margin-left: -30px;
  z-index: 51;
}

.theme.color3{
  margin-left: -30px;
  z-index: 52;
}

.theme.color4{
  margin-left: -30px;
  z-index: 53;
}

.theme.color5{
  margin-left: -30px;
  z-index: 54;
}
</style>