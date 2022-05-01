<template>
  <nav class="navbar py-3 navbar-expand-lg navbar-light" style="background-color:whitesmoke; box-shadow: 0 2px 4px 0 rgba(0,0,0,.3);" id="navbar">
    <div class="container-fluid">
      <div class="d-flex col-6 justify-content-start">
        <h5 class="font-italic ps-2 m-0" >Welcome <b>{{ user }}</b>
        </h5>
      </div>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ms-auto d-flex align-items-center gap-3 pe-2">
          <li class="nav-item">
            <a class="nav-link p-1 n" type="button" @click="$router.push('/Home')">Ongoing Races</a>
          </li>
          <li class="nav-item">
            <a class="nav-link p-1 n" type="button" @click="$router.push('/History')">Past Races</a>
          </li>
          <li class="nav-item">
            <a class="nav-link p-1 n" type="button" @click="$router.push('/Bets')">My Bets</a>
          </li>
          <li class="nav-item">
            <a class="nav-link p-1 n" type="button" @click="$router.push('/Horses')">My Horses</a>
          </li>
          <li class="nav-item">
            <a class="nav-link p-1 n" type="button" @click="logout">Log out!</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import router from "@/router";

export default {
  name: "NavBar",
  data() {
    return {
      user: localStorage.getItem("username"),
    }
  },
  methods: {
    async logout() {
      localStorage.clear();
      await router.push("/");
    }
  }
}
</script>

<style type="scss">
  .n {
    color: #524c4c !important;
    text-decoration: none;
    position: relative;
    border-bottom: 1px solid #b0a1a1;
  }
  .n:hover {
    color: #000000 !important;
  }
  .n::after {
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    margin: auto;
    background-color: black;
    color: transparent;
    width: 0;
    content: " ";
    height: 1px;
    transition: all 0.5s;
  }
  .n:hover::after {
    width: 100%;
  }
</style>