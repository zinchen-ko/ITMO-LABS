<template>
  <div id="wrapper">
    <div name="login-form" class="login-form">

      <div class="header">
        <h1>Авторизация</h1>
        <span>Введите ваши регистрационные данные для входа в ваш личный кабинет. </span>
      </div>

      <div class="content">
        <input v-bind:value="login" @input="login=$event.target.value" placeholder="Логин" name="username" type="text" class="input username" />
        <input v-bind:value="password" @input="password=$event.target.value" placeholder="Пароль" name="password" type="password" class="input password"/>
      </div>

      <div class="footer">
        <input v-on:click="sign" type="submit" name="submit" value="ВОЙТИ" class="button" />
        <input v-on:click="register" type="submit" name="submit" value="Регистрация" class="register" />
      </div>
    </div>
  </div>
</template>

<script>

export default {

  data() {
    return {
      login: "",
      password: ""
    }
  },

  methods: {
    sign() {
      const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          "username": this.login,
          "password": this.password
        })
      };
      fetch("http://localhost:45220/web-lab4/api/auth/login", requestOptions)
          .then(response => response.json())
          .then(data => (
              localStorage.setItem("jwtToken", JSON.stringify(data.token))
          ));
      localStorage.setItem("signIs","true")
    },

    register() {
      const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          "username": this.login,
          "password": this.password
        })
      };
      fetch("http://localhost:45220/web-lab4/api/auth/register", requestOptions)
          .then(response => response.json())
          .then(data => (
              data
          ));
    }
  }

}
</script>

<style>

.login-form {
  width: 300px;
  margin: 0 auto;
  position: relative;
  z-index:5;
  margin-top: 150px;

  background: #f3f3f3;
  border: 1px solid #fff;
  border-radius: 5px;

  box-shadow: 0 1px 3px rgba(0,0,0,0.5);
  -moz-box-shadow: 0 1px 3px rgba(0,0,0,0.5);
  -webkit-box-shadow: 0 1px 3px rgba(0,0,0,0.5);
}

/*******************
HEADER
*******************/

.login-form .header {
  padding: 40px 30px 30px 30px;
}

.login-form .header h1 {
  font-family: 'Bree Serif', serif;
  font-weight: 300;
  font-size: 28px;
  line-height:34px;
  color: #414848;
  text-shadow: 1px 1px 0 rgba(256,256,256,1.0);
  margin-bottom: 10px;
}

.login-form .header span {
  font-size: 11px;
  line-height: 16px;
  color: #678889;
  text-shadow: 1px 1px 0 rgba(256,256,256,1.0);
}

/*******************
CONTENT
*******************/

.login-form .content {
  padding: 0 30px 25px 30px;
}

/* Input field */
.login-form .content .input {
  width: 188px;
  padding: 15px 25px;

  font-family: "HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue", Helvetica, Arial, "Lucida Grande", sans-serif;
  font-weight: 400;
  font-size: 14px;
  color: #9d9e9e;
  text-shadow: 1px 1px 0 rgba(256,256,256,1.0);

  background: #fff;
  border: 1px solid #fff;
  border-radius: 5px;

  box-shadow: inset 0 1px 3px rgba(0,0,0,0.50);
  -moz-box-shadow: inset 0 1px 3px rgba(0,0,0,0.50);
  -webkit-box-shadow: inset 0 1px 3px rgba(0,0,0,0.50);
}

/* Second input field */
.login-form .content .password, .login-form .content .pass-icon {
  margin-top: 25px;
}

.login-form .content .input:hover {
  background: #dfe9ec;
  color: #414848;
}

.login-form .content .input:focus {
  background: #dfe9ec;
  color: #414848;

  box-shadow: inset 0 1px 2px rgba(0,0,0,0.25);
  -moz-box-shadow: inset 0 1px 2px rgba(0,0,0,0.25);
  -webkit-box-shadow: inset 0 1px 2px rgba(0,0,0,0.25);
}

.user-icon, .pass-icon {
  width: 46px;
  height: 47px;
  display: block;
  position: absolute;
  left: 0px;
  padding-right: 2px;
  z-index: 3;

  -moz-border-radius-topleft: 5px;
  -moz-border-radius-bottomleft: 5px;
  -webkit-border-top-left-radius: 5px;
  -webkit-border-bottom-left-radius: 5px;
}

/* Animation */
.input, .button, .register {
  transition: all 0.5s;
  -moz-transition: all 0.5s;
  -webkit-transition: all 0.5s;
  -o-transition: all 0.5s;
  -ms-transition: all 0.5s;
  outline:none;
}

/*******************
FOOTER
*******************/

.login-form .footer {
  padding: 25px 30px 40px 30px;
  overflow: auto;

  background: #d4dedf;
  border-top: 1px solid #fff;

  box-shadow: inset 0 1px 0 rgba(0,0,0,0.15);
  -moz-box-shadow: inset 0 1px 0 rgba(0,0,0,0.15);
  -webkit-box-shadow: inset 0 1px 0 rgba(0,0,0,0.15);
}

/* Login button */
.login-form .footer .button {
  float:right;
  padding: 11px 25px;

  font-family: 'Bree Serif', serif;
  font-weight: 300;
  font-size: 18px;
  color: #fff;
  text-shadow: 0px 1px 0 rgba(0,0,0,0.25);

  background: #56c2e1;
  border: 1px solid #46b3d3;
  border-radius: 5px;
  cursor: pointer;

  box-shadow: inset 0 0 2px rgba(256,256,256,0.75);
  -moz-box-shadow: inset 0 0 2px rgba(256,256,256,0.75);
  -webkit-box-shadow: inset 0 0 2px rgba(256,256,256,0.75);
}

.login-form .footer .button:hover {
  background: #3f9db8;
  border: 1px solid rgba(256,256,256,0.75);

  box-shadow: inset 0 1px 3px rgba(0,0,0,0.5);
  -moz-box-shadow: inset 0 1px 3px rgba(0,0,0,0.5);
  -webkit-box-shadow: inset 0 1px 3px rgba(0,0,0,0.5);
}

.login-form .footer .button:focus {
  position: relative;
  bottom: -1px;

  background: #56c2e1;

  box-shadow: inset 0 1px 6px rgba(256,256,256,0.75);
  -moz-box-shadow: inset 0 1px 6px rgba(256,256,256,0.75);
  -webkit-box-shadow: inset 0 1px 6px rgba(256,256,256,0.75);
}

/* Register button */
.login-form .footer .register {
  display: block;
  float: right;
  padding: 10px 5px 10px 0;
  margin-right: 20px;

  background: none;
  border: none;
  cursor: pointer;

  font-family: 'Bree Serif', serif;
  font-weight: 300;
  font-size: 18px;
  color: #414848;
  text-shadow: 0px 1px 0 rgba(256,256,256,0.5);
}

.login-form .footer .register:hover {
  color: #3f9db8;
}

.login-form .footer .register:focus {
  position: relative;
  bottom: -1px;
}
</style>