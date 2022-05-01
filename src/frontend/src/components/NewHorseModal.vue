<template>
  <!-- Modal -->
  <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="staticBackdropLabel">Register New Horse</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form>
            <!-- Insert name -->
            <div class="form-outline mb-2">
              <input type="text" id="enterPlace" class="form-control" v-model="horse_name" />
              <label class="form-label" for="enterPlace">Horse Name</label>
            </div>

            <p style="color: green; display: none" id="valid">Horse added Successfully</p>
            <p style="color: darkred; display: none" id="invalid">Invalid horse</p>

          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" @click="addHorse">Sumbit</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "NewHorseModal",
  data() {
    return {
      horse_name: ""
    };
  },
  methods: {
    async addHorse() {
      const invalid = document.getElementById("invalid");
      const valid = document.getElementById("valid");
      invalid.style.setProperty("display", "none");
      valid.style.setProperty("display", "none");
      if (this.horse_name.length === 0) {
        invalid.style.setProperty("display", "block");
      } else {
        axios.post('http://localhost:8080/horse/new', {
          "name": this.horse_name,
          "userid": {
            "user_id": localStorage.getItem("user")
          }
        }).then((response) => {
          this.horse_name = "";
          valid.style.setProperty("display", "block");
          this.$emit("insertHorse", response.data);
        }).catch(() => invalid.style.setProperty("display", "block"));
      }
    }
  }
}
</script>

<style scoped>

</style>