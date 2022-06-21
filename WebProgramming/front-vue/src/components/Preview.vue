<template>
  <div id="pre-view">
    <div id="info">
      <br>
      Зинченко Константин <br>
      Лабораторная работа №4 <br>
      Вариант №15106
    </div>
    <div id="nav">
      <router-link to="/">SignPage</router-link> |
      <router-link to="/main">ApplicationPage</router-link>
      <br> <br>
      <div id="autoInfo">
        {{auto}}
      </div>
    </div>
    <div>
      <br> <br> <br>
      <button id="unLogBut" v-on:click="unLog">Выйти из аккаунта</button>
    </div>
  </div>
</template>

<script>
export default {

  data () {
    return{
      auto: localStorage.getItem("loginInfo")
    }
  },

  created() {
    this.onCreate();
  },

  methods: {
    onCreate() {
      if (localStorage.getItem("signIs") === "true") {
        localStorage.setItem("loginInfo", "Пользователь: авторизирован")
        this.auto = "Пользователь: авторизирован"
      } else {
        localStorage.setItem("loginInfo", "Пользователь: не авторизирован")
        this.auto = "Пользователь: не авторизирован"
      }
    },
    unLog() {
      this.auto = "Пользователь: не авторизирован";
      localStorage.setItem("loginInfo", "Пользователь: не авторизирован");
      localStorage.setItem("signIs", "false");
      localStorage.setItem("jwtToken","");
      this.$router.push("/")
    }
  }
}
</script>

<style>
#nav {
  padding: 30px;
  width: 350px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
  text-decoration: none;
  font-size: 20px;
}

#nav a.router-link-exact-active {
  color: indigo;
}

#pre-view {
  display: flex;
  justify-content: space-between;
  width: 700px;
  margin: auto;
}

#info {
  width: 350px;
  font-size: 20px;
}

#unLogBut {
  width: 150px;
  min-height: 25px;
}
</style>