webpackJsonp([1],{0:function(t,e){},"2agw":function(t,e){},"9are":function(t,e){},"E8+Z":function(t,e){},EdDX:function(t,e){},HOYQ:function(t,e){},NHnr:function(t,e,n){"use strict";function i(t){n("bKeK")}function s(t){n("PrIp")}function o(t){n("EdDX")}function a(t){n("HOYQ")}function r(t){n("E8+Z")}function c(t){n("9are")}function u(t){n("2agw")}function l(t){n("q5Ry")}function p(t){n("a8xL")}Object.defineProperty(e,"__esModule",{value:!0});var d=n("7+uW"),m={name:"app"},v=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{attrs:{id:"app"}},[n("ul",[n("li",[n("a",{directives:[{name:"t",rawName:"v-t",value:"selectable",expression:"'selectable'"}],staticClass:"active",attrs:{href:"#/selectable"}},[t._v("selectable")])]),t._v(" "),n("li",[n("a",{directives:[{name:"t",rawName:"v-t",value:"tipable",expression:"'tipable'"}],attrs:{href:"#/tipable"}},[t._v("tipable")])]),t._v(" "),n("li",[n("a",{directives:[{name:"t",rawName:"v-t",value:"translatable",expression:"'translatable'"}],attrs:{href:"#/translatable"}},[t._v("translatable")])]),t._v("a\n  ")]),t._v(" "),n("router-view",{key:t.$route.path})],1)},h=[],b={render:v,staticRenderFns:h},q=b,f=n("VU/8"),g=i,w=f(m,q,!1,g,null,null),_=w.exports,x=n("/ocq"),C=n("8+8L"),Q={name:"CqQuestion",props:{question:{type:String,default:function(){return""}}}},O=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"cq-question"},[n("span",[t._v(t._s(t.question))])])},R=[],y={render:O,staticRenderFns:R},P=y,E=n("VU/8"),$=s,k=E(Q,P,!1,$,"data-v-47f09519",null),H=k.exports,N={name:"CqOption",data:function(){return{classObject:{"cq-correct-answer":!1,"cq-bad-answer":!1}}},props:{option:{type:Object,default:function(){return{text:"-",correct:!0}}}},watch:{option:function(t){this.option=t,this.classObject["cq-correct-answer"]=!1,this.classObject["cq-bad-answer"]=!1}},methods:{onClick:function(){this.option.correct?(this.classObject["cq-correct-answer"]=!0,this.$emit("cq-option-correct-answer")):this.classObject["cq-bad-answer"]=!0}}},U=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"cq-option",class:t.classObject,on:{click:t.onClick}},[n("span",[t._v(t._s(t.option.text))])])},T=[],V={render:U,staticRenderFns:T},S=V,j=n("VU/8"),F=o,A=j(N,S,!1,F,"data-v-5fdbac22",null),B=A.exports;d.a.use(C.a),d.a.component("CqQuestion",H),d.a.component("CqOption",B);var I={name:"CqSelectable",data:function(){return{selectable:{options:[{text:"-",correct:!0},{text:"-",correct:!0},{text:"-",correct:!0},{text:"-",correct:!0}],question:"---",id:0},isRequestPending:!1,defaultTimeout:2e3,toHideOptions:!0,url:"selectable"}},created:function(){this.getQuestion()},methods:{getQuestion:function(){var t=this;this.$http.get(this.url).then(function(e){t.selectable=e.body,t.isRequestPending=!1},function(t){console.log(t)})},changeQuestion:function(){this.isRequestPending||(this.isRequestPending=!0,setTimeout(this.getQuestion,this.defaultTimeout))}}},K=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("cq-question",{staticClass:"cq-question",attrs:{question:t.selectable.question}}),t._v(" "),n("input",{directives:[{name:"model",rawName:"v-model",value:t.toHideOptions,expression:"toHideOptions"}],attrs:{type:"checkbox"},domProps:{checked:Array.isArray(t.toHideOptions)?t._i(t.toHideOptions,null)>-1:t.toHideOptions},on:{change:function(e){var n=t.toHideOptions,i=e.target,s=!!i.checked;if(Array.isArray(n)){var o=t._i(n,null);i.checked?o<0&&(t.toHideOptions=n.concat([null])):o>-1&&(t.toHideOptions=n.slice(0,o).concat(n.slice(o+1)))}else t.toHideOptions=s}}}),t._v(" "),n("button",{directives:[{name:"t",rawName:"v-t",value:"next",expression:"'next'"}],on:{click:t.getQuestion}},[t._v("next")]),t._v(" "),n("div",{staticClass:"cq-options-container",class:{"to-hide-options":t.toHideOptions}},[n("div",{staticClass:"cq-row"},[n("cq-option",{attrs:{option:t.selectable.options[0]},on:{"cq-option-correct-answer":t.changeQuestion}}),t._v(" "),n("cq-option",{attrs:{option:t.selectable.options[1]},on:{"cq-option-correct-answer":t.changeQuestion}})],1),t._v(" "),n("div",{staticClass:"cq-row"},[n("cq-option",{attrs:{option:t.selectable.options[2]},on:{"cq-option-correct-answer":t.changeQuestion}}),t._v(" "),n("cq-option",{attrs:{option:t.selectable.options[3]},on:{"cq-option-correct-answer":t.changeQuestion}})],1)])],1)},L=[],X={render:K,staticRenderFns:L},D=X,Y=n("VU/8"),Z=a,J=Y(I,D,!1,Z,"data-v-047ec3e0",null),M=J.exports,W={name:"CqTipAnswer",data:function(){return{input:""}},props:{answer:{type:String,default:function(){return""}}},watch:{answer:function(t){this.answer=t}},methods:{onEnter:function(){this.answer==this.input&&this.$emit("cq-tip-correct-answer"),""==this.input?this.input=this.answer:this.input=""}}},z=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("input",{directives:[{name:"model",rawName:"v-model",value:t.input,expression:"input"}],attrs:{type:"text",autofocus:"true"},domProps:{value:t.input},on:{keyup:function(e){if(!("button"in e)&&t._k(e.keyCode,"enter",13,e.key))return null;t.onEnter(e)},input:function(e){e.target.composing||(t.input=e.target.value)}}})])},G=[],tt={render:z,staticRenderFns:G},et=tt,nt=n("VU/8"),it=r,st=nt(W,et,!1,it,null,null),ot=st.exports;d.a.use(C.a),d.a.component("CqQuestion",H),d.a.component("CqTipAnswer",ot);var at={name:"CqTipable",data:function(){return{question:"---",answer:"",isRequestPending:!1,url:"tipable"}},created:function(){this.getQuestion()},methods:{getQuestion:function(){var t=this;this.$http.get(this.url).then(function(e){t.question=e.body.question,t.answer=e.body.answer,t.isRequestPending=!1},function(t){console.log(t)})},changeQuestion:function(){this.isRequestPending||(this.isRequestPending=!0,this.getQuestion())}}},rt=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("cq-question",{staticClass:"cq-question",attrs:{question:t.question}}),t._v(" "),n("cq-tip-answer",{attrs:{answer:t.answer},on:{"cq-tip-correct-answer":t.changeQuestion}})],1)},ct=[],ut={render:rt,staticRenderFns:ct},lt=ut,pt=n("VU/8"),dt=c,mt=pt(at,lt,!1,dt,"data-v-759d47f4",null),vt=mt.exports,ht={name:"CqEditableOption",props:{option:{type:Object,default:function(){return{text:"-",correct:!0}}}},watch:{option:function(t){this.option=t}},methods:{}},bt=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"cq-option"},[n("input",{directives:[{name:"model",rawName:"v-model",value:t.option.text,expression:"option.text"}],domProps:{value:t.option.text},on:{input:function(e){e.target.composing||t.$set(t.option,"text",e.target.value)}}})])},qt=[],ft={render:bt,staticRenderFns:qt},gt=ft,wt=n("VU/8"),_t=u,xt=wt(ht,gt,!1,_t,"data-v-735eeb42",null),Ct=xt.exports,Qt={name:"CqEditableQuestion",props:{question:{type:String,default:function(){return""}}},methods:{onInput:function(t){this.$emit("cq-editable-question-changed",t)}}},Ot=function(){var t=this,e=t.$createElement;return(t._self._c||e)("textarea",{directives:[{name:"model",rawName:"v-model",value:t.question,expression:"question"}],staticClass:"cq-question",domProps:{value:t.question},on:{input:[function(e){e.target.composing||(t.question=e.target.value)},function(e){t.onInput(e.target.value)}]}})},Rt=[],yt={render:Ot,staticRenderFns:Rt},Pt=yt,Et=n("VU/8"),$t=l,kt=Et(Qt,Pt,!1,$t,"data-v-6a4835b3",null),Ht=kt.exports;d.a.use(C.a),d.a.component("CqQuestion",H),d.a.component("CqEditableOption",Ct),d.a.component("CqEditableQuestion",Ht);var Nt={name:"CqTranslatable",data:function(){return{options:[{text:"-",correct:!0},{text:"-",correct:!1},{text:"-",correct:!1},{text:"-",correct:!1}],translatable:{in:"----",out:"----",id:0},theme:"",isRequestPending:!1,defaultTimeout:2e3,isSubmitButtonVisible:!0,selecatableUrl:"selectable",translatableUrl:"translatable"}},created:function(){this.getQuestion()},methods:{changeOutQuestion:function(t){this.translatable.out=t},getQuestion:function(){var t=this;this.$http.get(this.translatableUrl).then(function(e){t.translatable=e.body,t.isRequestPending=!1,t.isSubmitButtonVisible=!0},function(t){console.log(t)})},changeQuestion:function(){this.isRequestPending||(this.isRequestPending=!0,setTimeout(this.getQuestion,this.defaultTimeout))},submit:function(){var t=this,e={question:this.translatable.out,options:this.options,theme:this.theme,id:this.translatable.id};this.$http.post(this.selecatableUrl,e).then(function(e){t.$http.delete(t.translatableUrl+"/"+t.translatable.id).then(function(e){t.isRequestPending=!1,t.isSubmitButtonVisible=!1},function(t){console.log(t)})},function(t){console.log(t)})}}},Ut=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("cq-question",{staticClass:"cq-question",attrs:{question:t.translatable.in}}),t._v(" "),n("cq-editable-question",{staticClass:"cq-question",attrs:{question:t.translatable.out},on:{"cq-editable-question-changed":t.changeOutQuestion}}),t._v(" "),n("div",{staticClass:"cq-editable-options-container"},[n("div",{staticClass:"cq-row"},[n("cq-editable-option",{attrs:{option:t.options[0]}}),t._v(" "),n("cq-editable-option",{attrs:{option:t.options[1]}})],1),t._v(" "),n("div",{staticClass:"cq-row"},[n("cq-editable-option",{attrs:{option:t.options[2]}}),t._v(" "),n("cq-editable-option",{attrs:{option:t.options[3]}})],1)]),t._v(" "),n("button",{directives:[{name:"show",rawName:"v-show",value:t.isSubmitButtonVisible,expression:"isSubmitButtonVisible"},{name:"t",rawName:"v-t",value:"submit",expression:"'submit'"}],on:{click:t.submit}},[t._v("submit")]),t._v(" "),n("button",{directives:[{name:"t",rawName:"v-t",value:"next",expression:"'next'"}],on:{click:t.getQuestion}},[t._v("next")]),t._v(" "),n("div",{staticClass:"cq-additional-settings"},[n("label",{directives:[{name:"t",rawName:"v-t",value:"theme",expression:"'theme'"}],attrs:{for:"theme"}},[t._v("theme")]),t._v(" "),n("input",{directives:[{name:"model",rawName:"v-model",value:t.theme,expression:"theme"}],attrs:{type:"text",name:"theme"},domProps:{value:t.theme},on:{input:function(e){e.target.composing||(t.theme=e.target.value)}}})])],1)},Tt=[],Vt={render:Ut,staticRenderFns:Tt},St=Vt,jt=n("VU/8"),Ft=p,At=jt(Nt,St,!1,Ft,"data-v-4ad1618c",null),Bt=At.exports;d.a.use(x.a);var It=new x.a({routes:[{path:"/selectable",name:"CqSelectable",component:M},{path:"/tipable",name:"CqTipable",component:vt},{path:"/translatable",name:"CqTranslatable",component:Bt}]}),Kt=n("TXmL");d.a.use(Kt.a);var Lt=new Kt.a({locale:navigator.language,messages:{bg:{selectable:"избираем",tipable:"познаваем",translatable:"за превод",theme:"тема",next:"следващ",delete:"изтрий",submit:"изпрати"},"en-US":{selectable:"selectable",tipable:"tipable",translatable:"translatable",theme:"theme",next:"next",delete:"delete",submit:"submit"}}});d.a.config.productionTip=!1,new d.a({el:"#app",router:It,i18n:Lt,template:"<App/>",components:{App:_}})},PrIp:function(t,e){},a8xL:function(t,e){},bKeK:function(t,e){},q5Ry:function(t,e){}},["NHnr"]);
//# sourceMappingURL=app.1f4ba8c065b92b6cb10e.js.map