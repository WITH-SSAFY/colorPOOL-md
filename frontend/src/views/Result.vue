<template>
  <div>
    <v-row class="edit wrap">
      <div class="slide-sidebar wrap"><slide-list-sidebar/></div>
      <div class="demo-result-area wrap"><DemoResult/></div> 
      <div class="button-group">
        <div type="button" class="button">Export to pdf</div>
        <div type="button" class="button">Share with SNS</div>
      </div>
    </v-row>
  </div>
</template>

<script>
import SlideListSidebar from '../components/sidebar/FinalSlideListSidebar'
import DemoResult from '../components/result/DemoResult'

export default {
  name: 'Result',
  components: {
    SlideListSidebar, DemoResult
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
  }

  .edit.wrap .button:hover{
    background-color: #d16a64;
  }
</style>