<template> 
  <div>
    <cq-question class="cq-question" :question="question"></cq-question>
    <cq-tip-answer @cq-tip-correct-answer="changeQuestion" :answer="answer"></cq-tip-answer>
  </div>
</template>

<script>
import VueResource from 'vue-resource'
import Vue from 'vue'
import CqQuestion from '@/components/CqQuestion.vue'
import CqTipAnswer from '@/components/CqTipAnswer.vue'

Vue.use(VueResource)
Vue.component('CqQuestion', CqQuestion)
Vue.component('CqTipAnswer', CqTipAnswer)

export default {
  name: 'CqTipable',
  data: function () {
    return {
      question: '---',
      answer: '',
      isRequestPending: false,
      url: 'tipable'
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
        this.question = response.body.question
        this.answer = response.body.answer
        this.isRequestPending = false
      },
      response => {
        console.log(response)
      })
    },
    changeQuestion: function () {
      if (!this.isRequestPending) {
        this.isRequestPending = true
        this.getQuestion()
      }
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
