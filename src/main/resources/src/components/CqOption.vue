<template> 
  <div class="cq-option" @click="onClick" :class="classObject"><span>{{option.text}}</span></div>
</template>

<script>
export default {
  name: 'CqOption',
  data: function () {
    return {
      classObject: {
        'cq-correct-answer': false,
        'cq-bad-answer': false
      }
    }
  },
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
      this.classObject['cq-correct-answer'] = false
      this.classObject['cq-bad-answer'] = false
    }
  },
  methods: {
    onClick: function () {
      if (this.option.correct) {
        this.classObject['cq-correct-answer'] = true
        this.$emit('cq-option-correct-answer')
      } else {
        this.classObject['cq-bad-answer'] = true
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.cq-option {
  border: 1px solid black;
  width: 50%;
  height: 70px;
  font-style: normal;
  font-size: 16px;
  font-family: "Verdana";
  text-align: center;
  display: table-cell;
  vertical-align: middle;
}
.cq-bad-answer {
  background: #ab0606;
}

.cq-correct-answer {
  background: #009933;
}
</style>
