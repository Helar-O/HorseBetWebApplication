<template>
  <!-- Modal -->
  <div class="modal fade" id="newRace" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="staticBackdropLabel">Add New Race</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form>
            <!-- Insert place -->
            <div class="row">
              <div class="form-outline mb-2 d-flex align-items-center">
                <label class="form-label col-1 me-2" for="enterPlace">Place:</label>
                <input type="text" id="enterPlace" class="form-control" v-model="place" />
              </div>
            </div>

            <!-- Insert date -->
            <div class="row">
              <div class="form-outline mb-2 d-flex align-items-center">
                <label class="form-label col-1 me-2" for="enterDate">Date:</label>
                <input type="date" id="enterDate" class="form-control" v-model="date" />
              </div>
            </div>

            <!-- Insert time -->
            <div class="row">
              <div class="form-outline mb-2 d-flex align-items-center">
                <label class="form-label col-1 me-2" for="enterTime">Time:</label>
                <input type="time" id="enterTime" class="form-control" v-model="time"/>
              </div>
            </div>

            <p style="color: green; display: none" id="valid">Race Created Successfully</p>
            <p style="color: darkred; display: none" id="invalid">Invalid race form</p>

          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" @click="addRace">Sumbit</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "NewRaceModal",
  data() {
    return {
      place: "",
      date: "",
      time: "",
    };
  },
  methods: {
    async addRace() {
      const invalid = document.getElementById("invalid");
      const valid = document.getElementById("valid");
      invalid.style.setProperty("display", "none");
      valid.style.setProperty("display", "none");
      if (this.place.length === 0 || this.date.length === 0 || this.time.length === 0) {
        invalid.style.setProperty("display", "block");
      } else {
        axios.post('http://localhost:8080/race/new', {
          "place": this.place,
          "date": this.date,
          "time": this.time
        }).then((response) => {
          this.place = "";
          this.date = "";
          this.time = "";
          valid.style.setProperty("display", "block");
          this.$emit("insertRace", response.data);
        }).catch(() => invalid.style.setProperty("display", "block"));
      }
    }
  }
}
</script>

<style scoped>

</style>