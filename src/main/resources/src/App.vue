<template>
  <div id="app">
    <ul>
      <li><a href="#/selectable" class="active" v-t="'selectable'">selectable</a></li>
      <li><a href="#/tipable" v-t="'tipable'">tipable</a></li>
      <li><a href="#/translatable" v-t="'translatable'">translatable</a></li>a
    </ul>
    <router-view :key="$route.path"/>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}

a {
  text-decoration: none;
}

ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #333;
}

li {
    float: left;
}

li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover {
    background-color: #111;
}

.cq-question {
  background-color: #FFFFFF;
  font-style: normal;
  font-family: "Verdana";
  text-align: center;
  border: 1px solid #000;
  font-size: 20px;
  width: 100%;
  padding: 0;
  margin: 15px 0 50px 0;
}
</style>
