<template>
  <div>
    <v-row class="landing wrap">
      <!-- TODO : 화면 조정 -> 사이드바 fixed, 사이드와 메인이 각자 스크롤 -->
      <div class="theme-sidebar wrap"><demo-theme-sidebar/></div>
      <div class="example-area wrap"><example/></div>
    </v-row>
  </div>
</template>

<script>
import Example from '../components/landing/Example'
import DemoThemeSidebar from '../components/sidebar/DemoThemeSidebar'

export default {
  name: 'Landing',
  components: {
    DemoThemeSidebar,
    Example
  },
  methods: {
    
  }
}
</script>

<style scoped>
.landing.wrap{
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

.example-area.wrap{
  /* background-color: rosybrown; */
  width: 77.5%;
}

</style>