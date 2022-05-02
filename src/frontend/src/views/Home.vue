<template>
  <new-race-modal @insertRace="insertRace"/>
  <participate-race v-bind:raceId="raceId"/>
  <bet-modal v-bind:participants="participants" @insertBet="insertBet"/>
  <div style="background-color: darkcyan; min-height: 100vh;">
    <nav-bar/>
      <div class="container p-4 my-3 border rounded">
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#newRace">
          New Race
        </button>
        <table class="table">
          <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Place</th>
            <th scope="col">Date</th>
            <th scope="col">Time</th>
            <th scope="col">Your Bet</th>
            <th scope="col">Actions</th>
          </tr>
          </thead>
          <tbody v-if="this.bets != null" >
          <tr v-for="race in races" :key="race.id">
            <th scope="row">{{race.race_id}}</th>
            <td>{{ race.place }}</td>
            <td>{{ race.date }}</td>
            <td>{{ race.time.substring(0, race.time.lastIndexOf(":")) }}</td>
            <td>{{ this.bets[race.race_id] }}</td>
            <td>
              <div class="d-flex gap-2">
                <button type="button" class="btn btn-primary btn-sm"
                        data-bs-toggle="modal" data-bs-target="#bet"
                        @click="setParticipatingHorses(race.race_id)">Bet</button>
                <button type="button" class="btn btn-primary btn-sm"
                        data-bs-toggle="modal" data-bs-target="#participate"
                        @click="setRaceId(race.race_id)">Participate</button>
              </div>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
  </div>
</template>

<script>
import router from "@/router";
import NavBar from "@/components/NavBar";
import axios from "axios";
import ParticipateRace from "@/components/Participate";
import NewRaceModal from "@/components/NewRaceModal";
import BetModal from "@/components/BetModal";

export default {
  name: "HomePage",
  components: {BetModal, NewRaceModal, ParticipateRace, NavBar},
  data() {
    return {
      user: localStorage.getItem("user"),
      races: null,
      raceId: null,
      participants: null,
      bets: null
    }
  },
  methods: {
    insertRace(race) {
      this.races.unshift(race);
    },
    insertBet(betId, horse) {
      this.bets[betId] = horse;
    },
    setRaceId(raceId) {
      this.raceId = raceId;
    },
    setParticipatingHorses(raceId) {
      axios.get(`http://localhost:8080/participant/race/${raceId}`)
      .then((response) => this.participants = response.data)
    }
  },
  mounted() {
    if (this.user == null) router.push("/");
  },
  beforeMount() {
    axios.get('http://localhost:8080/race/ongoing')
    .then((response) => this.races = response.data);
    axios.get(`http://localhost:8080/bet/${this.user}`)
    .then((response) => this.bets = response.data)
  }
}
</script>

<style scoped>

</style>