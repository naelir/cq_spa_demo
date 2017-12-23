<template> 
  <div> 
   <input type="text" @keyup.enter="onEnter" v-model="input" autofocus="true">
</div>
</template>

<script>
export default {
  name: 'CqTipAnswer',
  data: function () {
    return {
      input: ''
    }
  },
  props: {
    answer: {
      type: String,
      default: function () {
        return ''
      }
    }
  },
  watch: {
    answer: function (val) {
      this.answer = val
    }
  },
  methods: {
    onEnter: function () {
      /* eslint-disable */
      if (this.answer == this.input) {
        this.$emit('cq-tip-correct-answer')
      }
      if (this.input == '') {
        this.input = this.answer;
      } else {
        this.input = ''
      }
    }
  }
}
</script>
<style>
input {
  text-align:right;
}  
</style>
