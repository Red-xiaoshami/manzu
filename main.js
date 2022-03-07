import Vue from 'vue'
import App from './App'
import uniRequest from 'uni-request';
import pageHead from './components/page-head.vue'
import loading from './components/loading.vue'
import store from './store'
import api from './api/api';
import dev from './api/dev';
import utils from './utils';
// uniRequest.defaults.baseURL = 'http://comic.3gi.cc/api/v1/book/';
// uniRequest.defaults.headers.common['Authorization'] = AUTH_TOKEN;
// uniRequest.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
// uniRequest.defaults.headers.post['X-Requested-With'] = 'XMLHttpRequest';
uniRequest.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
// uniRequest.defaults.headers.post['Access-Control-Allow-Origin'] = 'http://comic.3gi.cc';
Vue.prototype.$axios=uniRequest;
Vue.component('page-head', pageHead)
Vue.component('Loading', loading)
Vue.config.productionTip = false
App.mpType = 'app'
Vue.prototype.$api=api;
Vue.prototype.$dev= dev;
Vue.prototype.$utils= utils;
Vue.prototype.$store= store;
const app = new Vue({
	store:store,
    ...App
})
app.$mount()
