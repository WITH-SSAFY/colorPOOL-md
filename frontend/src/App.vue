<template>
  <v-app>
    <!-- <v-app-bar
      color="transparent"
      flat
      fixed
      style="z-index: 0;"
    >
      <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>
    </v-app-bar> -->
    <v-btn v-if="!drawer" icon text fixed style="margin: 10px 10px auto; z-index: 99;" @click="drawer = true">
      <v-icon size="45" color="#232a46">mdi-menu</v-icon>
    </v-btn>
    <v-btn v-if="drawer" icon text fixed style="margin: 10px 10px auto; z-index: 101;" @click="drawer = false">
      <v-icon size="45" color="#ffffff">mdi-menu</v-icon>
    </v-btn>
    <v-navigation-drawer
      v-model="drawer"
      fixed
      temporary
      style="z-index: 99; width: 22.5%; height: 100%; background-color: #232a46;"
      dark
    >
      <v-list
        nav
        dense
      >
        <v-list-item-group
          v-model="group"
          active-class="red lighten-2--text text--accent-4"
          style="padding-top: 50%;"
        >
          <v-list-item style="padding: 2.5%;" @click="goHome">
            <v-list-item-icon>
              <v-icon size="30">mdi-home</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="font-weight: bolder; font-size: 2rem; line-height: 2rem;">Home</v-list-item-title>
          </v-list-item>

          <v-list-item style="padding: 2.5%;" @click="goPickColor">
            <v-list-item-icon>
              <p style="font-weight: bolder; font-size: 2rem; line-height: 2rem;">1</p>
            </v-list-item-icon>
            <v-list-item-title style="font-weight: bolder; font-size: 2rem; line-height: 2rem;">Select Color</v-list-item-title>
          </v-list-item>

          <v-list-item style="padding: 2.5%;" @click="goEditing">
            <v-list-item-icon>
              <p style="font-weight: bolder; font-size: 2rem; line-height: 2rem;">2</p>
            </v-list-item-icon>
            <v-list-item-title style="font-weight: bolder; font-size: 2rem; line-height: 2rem;">Create Markdown</v-list-item-title>
          </v-list-item>

          <v-list-item style="padding: 2.5%;" @click="goColoring">
            <v-list-item-icon>
              <p style="font-weight: bolder; font-size: 2rem; line-height: 2rem;">3</p>
            </v-list-item-icon>
            <v-list-item-title style="font-weight: bolder; font-size: 2rem; line-height: 2rem;">Coloring</v-list-item-title>
          </v-list-item>

          <v-list-item style="padding: 2.5%;" @click="goFinal">
            <v-list-item-icon>
              <p style="font-weight: bolder; font-size: 2rem; line-height: 2rem;">4</p>
            </v-list-item-icon>
            <v-list-item-title style="font-weight: bolder; font-size: 2rem; line-height: 2rem;">Final</v-list-item-title>
          </v-list-item>

        </v-list-item-group>
      </v-list>

    </v-navigation-drawer>
    <router-view/>

    <!-- 화면 크기 제한 -->
    <div class="resize">
      <div class="ground">
        <p>colorPOOL.md는 웹 환경에서 지원합니다</p>
        <p>발표자료 제작을 위해 화면의 크기를 좀 더 키워주세요</p>
      </div>
      <div class="frame">
        <div class="shadow-top"></div>
        <div class="shadow-bottom"></div>
        <div class="canvas">
          <div class="workeasy">
            Work
            Easy
          </div>
          <div class="and">&</div>
          <div class="benice">
            Be nice
            to People
          </div>
          <div class="line"></div>
          <div class="sign">
            colorPOOL.md
          </div>
        </div>
      </div>
    </div>

  </v-app>
</template>

<script>
// import HelloWorld from './components/HelloWorld';
import { mapActions } from 'vuex'
const landingStore = 'landingStore'

export default {
  name: 'App',
  components: {
    // HelloWorld,
  },
  data: () => ({
    //
    drawer: false,
    group: null,
  }),
  methods: {
    ...mapActions(landingStore, ['AC_IS_PICK', 'AC_IS_GET']),
    goHome () {
      this.$router.push({name: 'Landing'});
    },
    goPickColor () {
      this.AC_IS_PICK(true);
      this.AC_IS_GET(false);
      this.$router.push({name: 'Select'});
    },
    goEditing () {
      this.$router.push({name : 'Edit'})
      this.AC_PAGE(0);
    },
    goColoring () {
      this.$router.push({name : 'Edit'})
      this.AC_PAGE(1);
    },
    goFinal () {
      this.$router.push({name: 'Result'});
    },
  }
};
</script>
<style>
  *{ font-family: 'Proxima Nova Alt', 'Spoqa Han Sans'; }

  /*
  =======================================================
  화면 크기 제한
  =======================================================
  */
  .resize {
    display: none;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 9999;
    padding: 0;
    background: #eee;
    overflow: hidden;
    text-align: center;
  }

  @media (max-width: 1199px) {
    .resize {
      display: block;
    }
  }

  .frame {
    position: relative;
    width: 230px;
    height: 380px;
    padding: 6px;
    background: #fff;
    margin: 80px auto;
  }
  
  .shadow-top {
    position: absolute;
    left: -50px;
    width: 0;
    height: 0;
    border-bottom: 366px solid #bbb;
    border-left: 50px solid transparent;
  }

  .shadow-bottom {
    position: absolute;
    left: -50px;
    bottom: 0;
    width: 0;
    height: 0;
    border-top: 20px solid #aaa;
    border-left: 50px solid transparent;
  }

  .canvas {
    padding: 30px;
    width: 100%;
    height: 100%;
    /* width: 170px;
    height: 320px; */
    background: #ee7771;
    box-shadow: inset -2px 2px 0 #777;
  }

  .ground {
    position: absolute;
    top: 450px;
    width: 100%;
    min-width: 250px;
    height: 800px;
    padding: 200px 0;
    background: #ddd;
    overflow: hidden;
  }

  .ground p {
    font-size: 1.5rem;
    padding: 1rem;
  }

  .workeasy, .and, .benice {
    text-transform: uppercase;
    font-weight: 600;
    font-size: 55px;
    line-height: 70px;
  }

  .and {
    font-size: 40px;
  }

  .benice {
    margin: 0 auto;
    width: 150px;
    font-size: 36px;
    line-height: 40px;
  }

  .line {
    width: 90px;
    height: 1px;
    background: #111;
    margin: 30px auto 0;
  }

  .sign {
    font-size: 10px;
    margin-top: 5px;
  }

  a, .ground p {
    color: #222;
    line-height: 0;
  }

  .mysite {
    font-size: 18px;
    text-decoration:  none;
    color: rgba(0,0,0,0.5);
    border-bottom: solid 1px rgba(0,0,0,0.5);
    position: fixed;
    bottom: 10px;
    right: 10px;
    line-height: 20px;
  }

</style>