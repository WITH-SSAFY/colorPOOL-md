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
      absolute
      temporary
      style="z-index: 99; width: 22.5%; background-color: #232a46;"
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
      this.$router.push({name: 'Editing'});
    },
    goColoring () {
      this.$router.push({name: 'Coloring'});
    },
    goFinal () {
      this.$router.push({name: 'Result'});
    },
  }
};
</script>
<style>
  *{ font-family: 'Proxima Nova Alt', 'Spoqa Han Sans'; }
</style>