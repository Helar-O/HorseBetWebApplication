<template>
  <div class="container p-5 w-50 border rounded">
    <!-- Pills navs -->
    <ul class="nav nav-pills nav-justified mb-4 mx-auto w-75" id="ex1" role="tablist">
      <li class="nav-item px-3" role="presentation">
        <a class="nav-link text-black active" id="tab-login" data-bs-toggle="pill" href="#pills-login" role="tab"
           aria-controls="pills-login" aria-selected="true">Login</a>
      </li>
      <li class="nav-item px-3" role="presentation">
        <a class="nav-link text-black" id="tab-register" data-bs-toggle="pill" href="#pills-register" role="tab"
           aria-controls="pills-register" aria-selected="false">Register</a>
      </li>
    </ul>
    <!-- Pills navs -->

    <!-- Pills content -->
    <div class="tab-content">
      <div class="tab-pane fade show active" id="pills-login" role="tabpanel" aria-labelledby="tab-login">
        <form @sumbit.prevent="login">
          <!-- Email input -->
          <div class="form-outline mb-2">
            <input type="text" id="loginName" class="form-control" v-model="username" />
            <label class="form-label" for="loginName">Username</label>
          </div>

          <!-- Password input -->
          <div class="form-outline mb-2">
            <input type="password" id="loginPassword" class="form-control" v-model="password" />
            <label class="form-label" for="loginPassword">Password</label>
          </div>

          <!-- Submit button -->
          <button type="button" class="btn btn-primary btn-block mb-4" @click="login">Sign in</button>
          <p id="log-er" class="error-msg">Invalid login Credentials</p>
        </form>
      </div>
      <div class="tab-pane fade" id="pills-register" role="tabpanel" aria-labelledby="tab-register">
        <form @sumbit.prevent="register">
          <!-- Username input -->
          <div class="form-outline mb-2">
            <input type="text" id="registerUsername" class="form-control" v-model="username" />
            <label class="form-label" for="registerUsername">Username</label>
          </div>

          <!-- Password input -->
          <div class="form-outline mb-2">
            <input type="password" id="registerPassword" class="form-control" v-model="password" />
            <label class="form-label" for="registerPassword">Password</label>
          </div>

          <!-- Repeat Password input -->
          <div class="form-outline mb-2">
            <input type="password" id="registerRepeatPassword" class="form-control" v-model="repassword" />
            <label class="form-label" for="registerRepeatPassword">Repeat password</label>
          </div>

          <!-- Submit button -->
          <button type="button" class="btn btn-primary btn-block mb-3" @click="register">Sign up</button>
          <p id="sc" class="success-msg">Registration successful</p>
          <p id="er" class="error-msg"></p>
        </form>
      </div>
    </div>
    <!-- Pills content -->
  </div>
</template>

<script>
import axios from "axios";
import router from "@/router";

export default {
  name: "SignForm",
  data() {
    return {
      username: "",
      password: "",
      repassword: ""
    };
  },
  methods: {
    async login() {
      const {username, password} = this;
      axios.post('http://localhost:8080/user/login',
          {
          "username": username,
          "password": password
          }).then((response) => {
            localStorage.setItem("user", response.data.user_id);
            localStorage.setItem("username", username);
            router.push("/Home");
      }).catch(() => document.getElementById("log-er").style.setProperty("display", "block"));
    },
    async register() {
      const {username, password, repassword} = this;
      const sc = document.getElementById("sc");
      const er = document.getElementById("er");
      sc.style.setProperty("display","none");
      er.style.setProperty("display","none");
      if (password !== repassword) {
        er.innerHTML = "Passwords don't match";
        er.style.setProperty("display","block");
      }
      else if (username.length === 0) {
        er.innerHTML = "Username is empty";
        er.style.setProperty("display", "block");
      }
      else if (password.length === 0 || repassword.length === 0) {
        er.innerHTML = "Password is empty";
        er.style.setProperty("display","block");
      }
      else {
        axios.post('http://localhost:8080/user/register',
            {
              "username": username,
              "password": password
            }).then(() => {
              sc.style.setProperty("display","block");
              this.username = "";
              this.password = "";
              this.repassword = "";
            })
            .catch((response) => {
              if (response.status !== 200) {
                er.innerHTML = "Username is taken";
                er.style.setProperty("display","block");
              }
            });
      }
    }
  }
}
</script>

<style lang="scss">
  .error-msg {
    color: darkred;
    display: none;
  }
  .success-msg {
    color: green;
    display: none;
  }

  @media only screen and (max-width: 768px)  {
    .container {
      width: 100% !important;
    }
  }
</style>