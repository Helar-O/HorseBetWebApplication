<template>
  <!-- Modal -->
  <div class="modal fade" id="participate" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="staticBackdropLabel">Select Your Horse</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form>
            <!-- Insert Horse -->
            <div class="row">
              <div class="d-flex gap-2 align-items-center form-outline mb-2 col-8">
                <label class="form-label" for="enterHorse">Horse:</label>
                <select id="enterHorse" class="form-select" v-model="selectedHorse">
                  <option :value="null" selected disabled>Please select one</option>
                  <option :value="horse.horse_id" v-for="horse in horses" :key="horse.id">
                    {{ horse.name }}
                  </option>
                </select>
              </div>

              <!-- Insert Color -->
              <div class="d-flex gap-2 align-items-center form-outline mb-2 col-4">
                <label class="form-label" for="enterColor">Color:</label>
                <input type="color" id="enterColor" class="form-control-color" v-model="color"/>
              </div>
            </div>

            <p style="color: green; display: none" id="valid_p">Good Luck!</p>
            <p style="color: darkred; display: none" id="invalid_p"></p>

          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" @click="participate">Sumbit</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { GetColorName } from 'hex-color-to-color-name';

export default {
  name: "ParticipateRace",
  props: ["raceId"],
  data() {
    return {
      user: localStorage.getItem("user"),
      horses: null,
      selectedHorse: null,
      color: "#000000"
    }
  },
  beforeMount() {
    axios.get(`http://localhost:8080/horse/all/${this.user}`)
        .then((response) => this.horses = response.data)
  },
  methods: {
    participate() {
      const valid = document.getElementById("valid_p");
      const invalid = document.getElementById("invalid_p");
      valid.style.setProperty("display", "none");
      invalid.style.setProperty("display", "none");
      if (this.selectedHorse === null) {
        invalid.innerHTML = "Something went wrong";
        invalid.style.setProperty("display","block");
      } else {
        axios.post('http://localhost:8080/participant/new', {
          "raceid": {
            "race_id": this.raceId
          },
          "horseid": {
            "horse_id": this.selectedHorse
          },
          color: GetColorName(this.color)
        }).then(() => {
          valid.style.setProperty("display", "block")
          this.selectedHorse = null;
        })
        .catch(() => {
          invalid.innerHTML = "Horse Already In Race";
          invalid.style.setProperty("display", "block");
        })
      }
    }
  }
}
</script>

<style scoped>

</style>