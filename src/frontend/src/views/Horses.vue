<template>
  <new-horse-modal @insertHorse="insertHorse"/>
  <div style="background-color: darkcyan; min-height: 100vh;">
    <nav-bar/>
    <div class="container d-flex gap-3 mobile" style="background-color: inherit !important;">
      <div class="container p-4 my-3 border rounded" style="height: fit-content">
        <h3 class="text-center">Your Horses</h3>
        <div class="d-flex justify-content-end">
          <button type="button" class="btn btn-primary btn-sm position-absolute" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
            New Horse
          </button>
        </div>
        <table class="table">
            <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Name</th>
              <th scope="col">Wins</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(horse, index) in horses" :key="horse.id">
              <th scope="row">{{ index + 1 }}</th>
              <td>{{ horse.name }}</td>
              <td>{{ win_count(horse.horse_id) }}</td>
              <td>
                <button v-if="win_count(horse.horse_id) === 0" type="button" class="btn btn-danger btn-sm"
                        @click="removeHorse(horse.horse_id, index)">Remove</button>
              </td>
            </tr>
            </tbody>
          </table>
      </div>
      <div class="container p-4 my-3 border rounded" style="height: fit-content">
        <h3 class="text-center">Your Won Horses</h3>
        <table class="table">
          <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Place</th>
            <th scope="col">Date</th>
            <th scope="col">Horse</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(won, index) in wins" :key="won.id">
            <th scope="row">{{ index + 1 }}</th>
            <td>{{ won.place }}</td>
            <td>{{ won.date }}</td>
            <td>{{ won.winner.name }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar";
import axios from "axios";
import router from "@/router";
import NewHorseModal from "@/components/NewHorseModal";

export default {
  name: "MyHorses",
  components: {NewHorseModal, NavBar},
  data() {
    return {
      user: localStorage.getItem("user"),
      horses: null,
      wins: null
    }
  },
  beforeMount() {
    axios.get(`http://localhost:8080/horse/all/${this.user}`)
        .then((response) => this.horses = response.data)
    axios.get(`http://localhost:8080/race/wins/${this.user}`)
        .then((response) => this.wins = response.data)
  },
  mounted() {
    if (this.user == null) router.push("/");
  },
  methods: {
    insertHorse(horse) {
      this.horses.unshift(horse);
    },
    removeHorse(horse_id, index) {
      axios.delete(`http://localhost:8080/horse/${horse_id}`)
          .then(() => this.horses.splice(index, 1))
    },
    win_count(horse) {
      if (this.wins === null) return 0;
      let count = 0;
      Array.from(this.wins).forEach(function(race) {
        race.winner.horse_id === horse ? count++ : 0;
      });
      return count;
    }
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