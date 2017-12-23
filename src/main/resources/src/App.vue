<template>
  <div id="app">
    <ul>
      <li><a class="active" href="#/selectable">selectable</a></li>
      <li><a href="#/tipable">tipable</a></li>
    </ul>
    <div id="cq-top"></div>
    <router-view/>
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

#cq-menu {
  display: table;
  table-layout: fixed;
  width: 100%;
}

.cq-menu-item {
  display: table-cell;
}

.cq-menu-item:hover {
  background-color: #aec1c1;
}
a {
  text-decoration: none;
}

#cq-top {
  height: 200px;
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
</style>
