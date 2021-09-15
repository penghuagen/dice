// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
/* import Vue from 'vue' */
// 通用main
import Vue from 'vue'
import App from './App'
import router from '@/router/index'

/*
 所有项目公用
*/
// 引入vant样式
import 'vant/lib/index.css'
// 全局样式
import 'babel-polyfill'
// px转rem
import 'lib-flexible/flexible'
// 接口请求
import http from '@/assets/js/axios'
// 全局方法
import common from '@/assets/js/common'
// 全局变量
import Global from '@/assets/js/global'
import myPub from '@/assets/js/mypub'
import { vueBaberrage } from 'vue-baberrage'

// vant按需引入
import {
  AddressEdit,
  ActionSheet,
  AddressList,
  Button,
  Cell,
  CellGroup,
  Checkbox,
  CheckboxGroup,
  DatetimePicker,
  Dialog,
  DropdownMenu,
  DropdownItem,
  Field,
  Grid,
  GridItem,
  Icon,
  ImagePreview,
  Image,
  Info,
  IndexBar,
  IndexAnchor,
  Lazyload,
  List,
  Loading,
  Locale,
  Overlay,
  NumberKeyboard,
  Picker,
  Popup,
  /*  Progress,
  PullRefresh, */
  Radio,
  RadioGroup,
  /*  Rate, */
  Row,
  Search,
  Sku,
  Slider,
  Step,
  Steps,
  Swipe,
  SwipeCell,
  SwipeItem,
  Switch,
  SwitchCell,
  Tab,
  Tabbar,
  TabbarItem,
  Tabs,
  Tag,
  Toast,
  Uploader
} from 'vant'
Vue.use(vueBaberrage)
Vue.prototype.GLOBAL = Global
Vue.prototype.$mypub = myPub
Vue.prototype.$http = http

Vue.config.productionTip = false
Vue.use(common)
Vue.prototype.GLOBAL = Global

Vue.use(AddressEdit)
  .use(ActionSheet)
  .use(AddressList)
  .use(Button)
  .use(Cell)
  .use(CellGroup)
  .use(Checkbox)
  .use(CheckboxGroup)
  .use(DatetimePicker)
  .use(Dialog)
  .use(DropdownMenu)
  .use(DropdownItem)
  .use(Field)
  .use(Grid)
  .use(GridItem)
  .use(Icon)
  .use(ImagePreview)
  .use(Image)
  .use(Info)
  .use(IndexBar)
  .use(IndexAnchor)
  .use(Lazyload)
  .use(List)
  .use(Loading)
  .use(Locale)
  .use(Overlay)
  .use(NumberKeyboard)
  .use(Picker)
  .use(Popup)
  .use(Radio)
  .use(RadioGroup)
  .use(Row)
  .use(Search)
  .use(Sku)
  .use(Slider)
  .use(Step)
  .use(Steps)
  .use(Swipe)
  .use(SwipeCell)
  .use(SwipeItem)
  .use(Switch)
  .use(SwitchCell)
  .use(Tab)
  .use(Tabbar)
  .use(TabbarItem)
  .use(Tabs)
  .use(Tag)
  .use(Toast)
  .use(Uploader)

// eslint-disable-next-line no-new
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
