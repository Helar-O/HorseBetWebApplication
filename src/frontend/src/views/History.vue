<template>
  <div style="background-color: darkcyan; min-height: 100vh">
    <nav-bar/>
    <div class="container p-4 my-3 border rounded">
      <table class="table">
        <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Place</th>
          <th scope="col">Date</th>
          <th scope="col">Time</th>
          <th scope="col">winner</th>
        </tr>
        </thead>
        <tbody v-if="colors !== null">
        <tr v-for="(race, index) in history" :key="race.id">
          <th scope="row">{{race.race_id}}</th>
          <td>{{ race.place }}</td>
          <td>{{ race.date }}</td>
          <td>{{ race.time }}</td>
          <td style="width: 10vh; white-space: nowrap">{{ colors[index] + " " + race.winner.name }}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar";
import axios from "axios";
import router from "@/router";
export default {
  name: "HistoryList",
  components: {NavBar},
  data() {
    return {
      user: localStorage.getItem("user"),
      history: null,
      colors: null
    }
  },
  mounted() {
    if (this.user == null) router.push("/");
  },
  async beforeMount() {
    const raceHistory = await axios.get('http://localhost:8080/race/history');
    this.history = raceHistory.data;
    const participantColor = await axios.get('http://localhost:8080/participant/color');
    this.colors = participantColor.data;
  }
}
</script>

<style scoped>

</style>