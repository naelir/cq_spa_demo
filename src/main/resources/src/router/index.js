import Vue from 'vue'
import Router from 'vue-router'
import CqSelectable from '@/components/CqSelectable'
import CqTipable from '@/components/CqTipable'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/selectable',
      name: 'CqSelectable',
      component: CqSelectable
    },
    {
      path: '/tipable',
      name: 'CqTipable',
      component: CqTipable
    }
  ]
})
