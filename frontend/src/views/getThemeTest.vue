<template>
<div>
  <div style="height: 300px; overflow: scroll;">
    <div style="height: 100px; width: 80%; margin: 20px;" v-for="(color, index) in materialColors" :key="index">
      <div class="color-chip" style="height: 100%; width: 10%; display: inline-block" v-for="(variation, index) in color.variations" :key="index"
            :style="{'background-color': variation.hex}" @click="getThemes(variation)"></div>
    </div>
  </div>
  <div>
    <div style="height: 100px; width: 80%; margin: 20px;" v-for="(theme, index) in themes" :key="index">
      <div style="height: 100%; width: 10%; display: inline-block" :style="{'background-color': 'rgb('+theme.red1+','+theme.green1+','+theme.blue1+')'}"></div>
      <div style="height: 100%; width: 10%; display: inline-block" :style="{'background-color': 'rgb('+theme.red2+','+theme.green2+','+theme.blue2+')'}"></div>
      <div style="height: 100%; width: 10%; display: inline-block" :style="{'background-color': 'rgb('+theme.red3+','+theme.green3+','+theme.blue3+')'}"></div>
      <div style="height: 100%; width: 10%; display: inline-block" :style="{'background-color': 'rgb('+theme.red4+','+theme.green4+','+theme.blue4+')'}"></div>
      <div style="height: 100%; width: 10%; display: inline-block" :style="{'background-color': 'rgb('+theme.red5+','+theme.green5+','+theme.blue5+')'}"></div>
    </div>
  </div>
</div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import materialColors from '../assets/colorList'
const themeStore = 'themeStore'

export default {
  name: 'getThemeTest',
  computed: {
    ...mapGetters(themeStore, { storeThemeList: 'GE_THEME_LIST'})
  },
  data(){
    return {
      materialColors: materialColors,
      themes: [],
    }
  },
  watch:{
    storeThemeList(val){
      this.themes = val;
    }
  },
  created(){
    this.themes = this.storeThemeList;
  },
  methods: {
    ...mapActions(themeStore, ['AC_THEME_LIST']),
    getThemes(variation){
      console.log('click!', variation.id)
      this.AC_THEME_LIST(variation.id);
    }
  }
}
</script>

<style scoped>

.color-chip:hover{
  border: 3px solid;
  border-color: rgba(0, 0, 0, 0.5);
}

</style>