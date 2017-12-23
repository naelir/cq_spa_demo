<template> 
  <div>
    <cq-question class="cq-question" :question="translatable.in"></cq-question>
    <cq-editable-question class="cq-question" :question="translatable.out" @cq-editable-question-changed="changeOutQuestion"></cq-editable-question>
    <div class="cq-editable-options-container">
      <div class="cq-row">
        <cq-editable-option :option="options[0]"></cq-editable-option>
        <cq-editable-option :option="options[1]"></cq-editable-option>
      </div>
      <div class="cq-row">
        <cq-editable-option :option="options[2]"></cq-editable-option>
        <cq-editable-option :option="options[3]"></cq-editable-option>
      </div>
    </div>
    <button @click="submit" v-show="isSubmitButtonVisible" v-t="'submit'">submit</button>
    <button @click="getQuestion" v-t="'next'">next</button>
    <div class="cq-additional-settings">
       <label for="theme" v-t="'theme'">theme</label>
       <input type="text" name="theme" v-model="theme">
    </div>
  </div>
</template>

<script>
import VueResource from 'vue-resource'
import Vue from 'vue'
import CqQuestion from '@/components/CqQuestion.vue'
import CqEditableOption from '@/components/CqEditableOption.vue'
import CqEditableQuestion from '@/components/CqEditableQuestion.vue'
Vue.use(VueResource)
Vue.component('CqQuestion', CqQuestion)
Vue.component('CqEditableOption', CqEditableOption)
Vue.component('CqEditableQuestion', CqEditableQuestion)

export default {
  name: 'CqTranslatable',
  data: function () {
    return {
      options: [{
        text: '-',
        correct: true
      }, {
        text: '-',
        correct: false
      }, {
        text: '-',
        correct: false
      }, {
        text: '-',
        correct: false
      }],
      translatable: {
        in: '----',
        out: '----',
        id: 0
      },
      theme: '',
      isRequestPending: false,
      defaultTimeout: 2000,
      isSubmitButtonVisible: true,
      selecatableUrl: 'selectable',
      translatableUrl: 'translatable'
    }
  },
  created: function () {
    this.getQuestion()
  },
  methods: {
    changeOutQuestion: function (value) {
      this.translatable.out = value
    },
    getQuestion: function () {
      this.$http
      .get(this.translatableUrl)
      .then(
      response => {
        this.translatable = response.body
        this.isRequestPending = false
        this.isSubmitButtonVisible = true
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
    },
    submit: function () {
      var body = {
        question: this.translatable.out,
        options: this.options,
        theme: this.theme,
        id: this.translatable.id
      }
      this.$http
      .post(this.selecatableUrl, body)
      .then(
      response => {
        this.$http
        .delete(this.translatableUrl + '/' + this.translatable.id)
        .then(
        response => {
          this.isRequestPending = false
          this.isSubmitButtonVisible = false
        },
        response => {
          console.log(response)
        })
      },
      response => {
        console.log(response)
      })
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.cq-editable-options-container {
  border: 1px solid black;
  width: 100%;
  display: table;
}

.cq-row {
  display: table-row;
}
.cq-question{
  margin: 15px 0 15px 0;
}
.cq-additional-settings {
  text-align: left;
  font-size: 15px;
} 
</style>
