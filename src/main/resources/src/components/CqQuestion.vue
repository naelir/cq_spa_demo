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
        return ''
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
