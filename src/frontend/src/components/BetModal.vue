<template>
  <!-- Modal -->
  <div class="modal fade" id="bet" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="staticBackdropLabel">Place Your Bet</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form>
            <!-- Bet On Horse -->
            <div class="d-flex gap-2 align-items-center form-outline mb-2">
              <label class="form-label" for="betHorse">Horse:</label>
              <select id="betHorse" class="form-select" v-model="selectedHorse">
                <option :value="null" selected disabled>Please select one</option>
                <option :value="participant" v-for="participant in participants" :key="participant.id">
                  {{ participant.color + " " + participant.horseid.name }}
                </option>
              </select>
            </div>

            <p style="color: green; display: none" id="valid_b">Bet Placed Successfully</p>
            <p style="color: darkred; display: none" id="invalid_b"></p>

          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" @click="placeBet">Sumbit</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import axios from "axios";

export default {
  name: "BetModal",
  props: ["participants"],
  data() {
    return {
      user: localStorage.getItem("user"),
      selectedHorse: null
    }
  },
  methods: {
    placeBet() {
      const valid_b = document.getElementById("valid_b");
      const invalid_b = document.getElementById("invalid_b");
      valid_b.style.setProperty("display", "none");
      invalid_b.style.setProperty("display", "none");
      if (this.selectedHorse.participant_id === null) {
        invalid_b.style.setProperty("display", "block");
        invalid_b.innerHTML = "Please select a horse"
      } else {
        axios.post('http://localhost:8080/bet/new', {
          "userid": {
            "user_id": this.user
          },
          "participantid": {
            "participant_id": this.selectedHorse.participant_id
          }
        }).then(() => {
          valid_b.style.setProperty("display", "block");
          this.$emit("insertBet", this.selectedHorse.raceid.race_id, (this.selectedHorse.color + " " + this.selectedHorse.horseid.name))
        })
        .catch(() => {
          invalid_b.style.setProperty("display", "block");
          invalid_b.innerHTML = "Already bet on this race"
        })
      }
    }
  }
}
</script>

<style scoped>

</style>