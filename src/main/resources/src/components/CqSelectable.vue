<template> 
  <div>
    <cq-question class="cq-question" :question="selectable.question"></cq-question>
    <input type="checkbox" v-model="toHideOptions">
    <button @click="getQuestion" v-t="'next'">next</button>
    <div class="cq-options-container" :class="{'to-hide-options':toHideOptions}">
      <div class="cq-row">
        <cq-option @cq-option-correct-answer="changeQuestion" :option="selectable.options[0]"></cq-option>
        <cq-option @cq-option-correct-answer="changeQuestion" :option="selectable.options[1]"></cq-option>
      </div>
      <div class="cq-row">
        <cq-option @cq-option-correct-answer="changeQuestion" :option="selectable.options[2]"></cq-option>
        <cq-option @cq-option-correct-answer="changeQuestion" :option="selectable.options[3]"></cq-option>
      </div>
    </div>
  </div>
</template>

<script>
import VueResource from 'vue-resource'
import Vue from 'vue'
import CqQuestion from '@/components/CqQuestion.vue'
import CqOption from '@/components/CqOption.vue'
Vue.use(VueResource)
Vue.component('CqQuestion', CqQuestion)
Vue.component('CqOption', CqOption)

export default {
  name: 'CqSelectable',
  data: function () {
    return {
      selectable: {
        options: [{
          text: '-',
          correct: true
        }, {
          text: '-',
          correct: true
        }, {
          text: '-',
          correct: true
        }, {
          text: '-',
          correct: true
        }],
        question: '---',
        id: 0
      },
      isRequestPending: false,
      defaultTimeout: 2000,
      toHideOptions: true,
      url: 'selectable'
    }
  },
  created: function () {
    this.getQuestion()
  },
  methods: {
    getQuestion: function () {
      this.$http
      .get(this.url)
      .then(
      response => {
        this.selectable = response.body
        this.isRequestPending = false
      },
      response => {
        console.log(response)
      })
    },
    changeQuestion: function () {
      if (!this.isRequestPending) {
        this.isRequestPending = true
        setTimeout(this.getQuestion, this.defaultTimeout)
      }
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.cq-options-container {
  border: 1px solid black;
  width: 100%;
  display: table;
}

.cq-row {
  display: table-row;
}

.to-hide-options{
  display: none;
}
</style>
