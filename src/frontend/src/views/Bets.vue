<template>
  <div style="background-color: darkcyan; min-height: 100vh;">
    <nav-bar/>
    <div class="container d-flex gap-3 mobile" style="background-color: inherit !important;">
      <div class="container p-4 my-3 border rounded" style="height: fit-content">
        <h3 class="text-center">Your Active Bets</h3>
        <table class="table">
          <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Race Name</th>
            <th scope="col">Date</th>
            <th scope="col">Horse Name</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(bet, index) in activeBets" :key="bet.id">
            <th scope="row">{{ index + 1 }}</th>
            <td>{{ bet.first.place }}</td>
            <td>{{ bet.first.date }}</td>
            <td style="width: 10vh; white-space: nowrap">{{ bet.second }}</td>
          </tr>
          </tbody>
        </table>
      </div>
      <div class="container p-4 my-3 border rounded" style="height: fit-content">
        <h3 class="text-center">Your Won Bets</h3>
        <table class="table">
          <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Race Name</th>
            <th scope="col">Date</th>
            <th scope="col">Horse Name</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(bet, index) in wonBets" :key="bet.id">
            <th scope="row">{{ index + 1 }}</th>
            <td>{{ bet.first.place }}</td>
            <td>{{ bet.first.date }}</td>
            <td style="width: 10vh; white-space: nowrap">{{ bet.second }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar";
import router from "@/router";
import axios from "axios";
export default {
  name: "MyBets",
  components: {NavBar},
  data() {
    return {
      user: localStorage.getItem("user"),
      activeBets: null,
      wonBets: null,
    }
  },
  mounted() {
    if (localStorage.getItem("user") == null) router.push("/");
  },
  beforeMount() {
    axios.get(`http://localhost:8080/bet/active/${this.user}`)
    .then((response) => this.activeBets = response.data)
    axios.get(`http://localhost:8080/bet/won/${this.user}`)
        .then((response) => this.wonBets = response.data)
  }
}
</script>

<style scoped>
@media only screen and (max-width: 768px) {
  .mobile {
    flex-direction: column;
    height: auto !important;
  }
}
</style>