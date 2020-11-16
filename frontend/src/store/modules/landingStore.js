const landingStore = {
  namespaced: true,
  state: {
    isPick: false,
    isGet: false,
    dialog: false,
  },
  getters:{
    GE_IS_PICK: state => state.isPick,
    GE_IS_GET: state => state.isGet,
    GE_DIALOG: state => state.dialog,
  },
  mutations: {
    MU_IS_PICK: (state, payload) => {
      // console.log('MU_IS_PICK', payload)
      state.isPick = payload
    },
    MU_IS_GET: (state, payload) => {
      // console.log('MU_IS_GET', payload)
      state.isGet = payload
   },
   MU_DIALOG: (state, payload) => {
    console.log('MU_DIALOG', payload)
     state.dialog = payload
   }
  },
  actions:{
    AC_IS_PICK: ({commit}, payload) =>  {
      localStorage.setItem('isPick', payload)
      commit('MU_IS_PICK', payload)
    },
    AC_IS_GET: ({commit}, payload) =>  {
      localStorage.setItem('isGet', payload)
      commit('MU_IS_GET', payload)
    },
    AC_DIALOG: ({commit}, payload) => {
      commit('MU_DIALOG', payload)
    }
  }
}
export default landingStore;