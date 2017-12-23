<template> 
  <textarea class="cq-question" v-model="question" @input="onInput($event.target.value)"></textarea>
</template>

<script>

export default {
  name: 'CqEditableQuestion',
  props: {
    question: {
      type: String,
      default: function () {
        return ''
      }
    }
  },
  methods: {
    onInput: function (value) {
      this.$emit('cq-editable-question-changed', value)
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
  height: 200px;
  overflow: hidden;
  padding: 0;
  border: 1px solid #000;
}
</style>