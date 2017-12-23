<template> 
  <div>
    <cq-question class="cq-question" :question="question"></cq-question>
    <div class="cq-options-container">
      <div class="cq-row">
        <cq-option @cq-option-correct-answer="changeQuestion" :option="options[0]"></cq-option>
        <cq-option @cq-option-correct-answer="changeQuestion" :option="options[1]"></cq-option>
      </div>
      <div class="cq-row">
        <cq-option @cq-option-correct-answer="changeQuestion" :option="options[2]"></cq-option>
        <cq-option @cq-option-correct-answer="changeQuestion" :option="options[3]"></cq-option>
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
      options: [{
        text: '',
        correct: true
      }, {
        text: '',
        correct: true
      }, {
        text: '',
        correct: true
      }, {
        text: '',
        correct: true
      }],
      question: '',
      isRequestPending: false,
      defaultTimeout: 2000,
      apiEndpoint: '/question'
    }
  },
  created: function () {
    this.getQuestion()
  },
  methods: {
    getQuestion: function () {
      this.$http
      .get(this.apiEndpoint)
      .then(
      response => {
        this.question = response.body.question
        this.options = response.body.options
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
  width: 80%;
  margin-left: auto;
  margin-right: auto;
  display: table;
}

.cq-row {
  display: table-row;
}

.cq-question{
  margin-top: 50px;
  margin-bottom: 50px;
}
</style>
