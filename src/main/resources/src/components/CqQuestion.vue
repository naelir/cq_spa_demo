<template> 
  <div class="cq-question"><span>{{question}}</span></div>
</template>

<script>

export default {
  name: 'CqQuestion',
  props: {
    question: {
      type: String,
      default: function () {
        return '-'
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.cq-question {
  background-color: #FFFFFF;
  font-style: normal;
  font-size: 20px;
  font-family: "Verdana";
  text-align: center;
  width: 100%;
}
</style>
