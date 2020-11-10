<template>
  <div>
    <v-row class="edit wrap">
      <div class="slide-sidebar wrap"><slide-list-sidebar/></div>
      <div class="editing-area wrap"><editing/></div>
      <div class="button-group">
        <div type="button" class="button">Export to pdf</div>
        <div type="button" class="button">Share with SNS</div>
      </div>
    </v-row>
  </div>
</template>

<script>
import SlideListSidebar from '../components/sidebar/SlideListSidebar'
import Editing from '../components/edit/Editing'

export default {
  name: 'Result',
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