<template>
  <div>
    <v-row class="edit wrap">
      <div class="slide-sidebar wrap"><slide-list-sidebar/></div>
      <div class="editing-area wrap"><editing/></div>
    </v-row>
  </div>
</template>

<script>
import SlideListSidebar from '../components/sidebar/SlideListSidebar'
import Editing from '../components/edit/Editing'

export default {
  name: 'Edit',
  components: {
    SlideListSidebar,
    Editing,
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

.edit.wrap .editing-area.wrap {
  background-color: rgba(238, 238, 238, 0.7);
  width: 77.5%;
}

</style>