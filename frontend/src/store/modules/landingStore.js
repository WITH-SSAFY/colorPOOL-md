const landingStore = {
  namespaced: true,
  state: {
    isPick: false,
    isGet: false,
  },
  getters:{
    GE_IS_PICK: state => state.isPick,
    GE_IS_GET: state => state.isGet,
  },
  mutations: {
    MU_IS_PICK: (state, payload) => {
      console.log('MU_IS_PICK', payload)
      state.isPick = payload
    },
    MU_IS_GET: (state, payload) => {
      console.log('MU_IS_GET', payload)
      state.isGet = payload
   },
  },
  actions:{
    AC_IS_PICK: ({commit}, payload) =>  {
      commit('MU_IS_PICK', payload)
    },
    AC_IS_GET: ({commit}, payload) =>  {
      commit('MU_IS_GET', payload)
    },
  }
}
export default landingStore;