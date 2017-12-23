<template> 
  <div class="cq-option">
      <input v-model="option.text"></input>
  </div>
</template>

<script>
export default {
  name: 'CqEditableOption',
  props: {
    option: {
      type: Object,
      default: function () {
        return {
          text: '-',
          correct: true
        }
      }
    }
  },
  watch: {
    option: function (val) {
      this.option = val
    }
  },
  methods: {
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
input {
  width: 100%;
  padding: 0;
  border: 0;
  height: 100%;
}
.cq-option {
  border: 1px solid black;
  width: 49%;
  height: 70px;
  font-style: normal;
  font-size: 16px;
  font-family: "Verdana";
  text-align: center;
  display: table-cell;
  vertical-align: middle;
}
.cq-editable-option {
  width: 99%;
  height: 90%;
}
</style>
