<template>
  <div>
    <v-row class="select-palettes wrap">
      <div class="theme-sidebar wrap"><theme-sidebar/></div>
      <div class="pick-color-area wrap" v-if="isPick"><pick-color/></div>
      <div class="get-color-area wrap" v-if="isGet"><get-color/></div>
      <div class="palette-stack-area wrap"><palette-stack/></div>
    </v-row>
  </div>
</template>

<script>
import ThemeSidebar from '../components/sidebar/ThemeSidebar'
import PickColor from '../components/select-palette/PickColor'
import GetColor from '../components/select-palette/GetColor'
import paletteStack from '../components/select-palette/PaletteStack'
import { mapGetters } from 'vuex'
const landingStore = 'landingStore'

export default {
  name: 'SelectPalettes',
  components: {
    ThemeSidebar,
    PickColor,
    GetColor,
    paletteStack
  },
  computed:{
    ...mapGetters(landingStore, {storeIsPick: 'GE_IS_PICK', storeIsGet: 'GE_IS_GET'})
  },
  data(){
    return {
      isPick: true,
      isGet: false,
    }
  },
  created(){
    this.isPick = this.storeIsPick;
    this.isGet = this.storeIsGet;
  },
  watch: {
    storeIsPick(val){
      this.isPick = val;
    },
    storeIsGet(val){
      this.isGet = val;
    }
  }
}
</script>

<style scoped>
.select-palettes.wrap{
  width: 100vw;
  height: 100vh;
  margin: 0;
  padding: 0;
}

.theme-sidebar.wrap{
  /* background-color: papayawhip; */
  padding: 0;
  margin: 0;
  height: 100vh;
  width: 22.5%;
}

.get-color-area.wrap{
  height: 100vh;
  width : 53.5%;
  background-color: honeydew;
}

.pick-color-area.wrap{
  height: 100vh;
  width : 53.5%; 
  background-color: lightpink;
}

.palette-stack-area.wrap{
  height: 100vh;
  width : 24%;
  background-color: khaki;
}

</style>