import Vue from 'vue'
import VueI18n from 'vue-i18n'

Vue.use(VueI18n)

export default new VueI18n({
  locale: navigator.language,
  messages: {
    'bg': {
      'selectable': 'избираем',
      'tipable': 'познаваем',
      'translatable': 'за превод',
      'theme': 'тема',
      'next': 'следващ',
      'delete': 'изтрий',
      'submit': 'изпрати'
    },
    'en-US': {
      'selectable': 'selectable',
      'tipable': 'tipable',
      'translatable': 'translatable',
      'theme': 'theme',
      'next': 'next',
      'delete': 'delete',
      'submit': 'submit'
    }
  }
})
