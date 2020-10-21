<template>
  <div>
    <v-col class="example-area">
      <div class="toggle-button-group">
        <v-btn large text>toggle themes</v-btn>
        <v-btn large text>toggle palette</v-btn>
      </div>
      <div class="example-md">
        <div class="example-content"><example-content/></div>
        <div class="button-group">
          <h1>Create Presentation</h1>
          <v-btn block large elevation="0" color="#EE7771" tile height="68px" >With Team Color</v-btn>
          <v-btn block large elevation="0" color="#EE7771" tile height="68px">with Main Keyword</v-btn>
        </div>
      </div>
      <div class="example-palette">
        <h1>Palettes in Markdown</h1>
      </div>
    </v-col>
  </div>
</template>

<script>
import ExampleContent from './ExampleContent'

export default {
  name: 'Example',
  components: {
    ExampleContent,
  }
}
</script>

<style scope>

.example-area.col{
  padding: 0;
}

.toggle-button-group{
  padding: 1% 5%;
  height: 8vh;
  width: 100%;
}

.toggle-button-group button{
  float: right;
}

.example-md{
  margin: 0;
  padding: 2%;
  height: 62vh;
  background-color: cadetblue;
}

.button-group{
  float: left;
  padding-top: 20%;
  padding-right: 2%;
  height: 62vh;
  width: 30%;
  height: 100%;
  background-color: darkslateblue;
}

.button-group h1{
  padding-left: 10%;
  margin-bottom: 3%;
}

.button-group button{
  margin-right: 5%; 
  margin-left: 5%; 
  margin-bottom: 7%; 
  display: block;
  font-size: 1.5rem;
}

.example-content{
  float: left;
  width: 70%;
  height: 100%;
  background-color: darksalmon;
}

.example-palette{
  margin: 0;
  padding-left: 3%;
}

</style>