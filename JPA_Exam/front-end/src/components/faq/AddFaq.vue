<template>
<!-- 4) -->
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="title">Title</label>
        <input
          type="text"
          class="form-control"
          id="title"
          required
          v-model="faq.title"
          name="title"
        />
      </div>

      <div class="form-group">
        <label for="content">Content</label>
        <input
          class="form-control"
          id="content"
          required
          v-model="faq.content"
          name="content"
        />
      </div>

      <button @click="saveFaq" class="btn btn-success">Submit</button>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newFaq">Add</button>
    </div>
  </div>
</template>

<script>
import FaqDataService from "../../services/FaqDataService";

export default {
  data() {
    return {
      faq: {
        no: null,
        title: "",
        content: ""
      },
      submitted: false
    };
  },
  methods: {
    saveFaq() {
      let data = {
        title: this.faq.title,
        content: this.faq.content
      };

      FaqDataService.create(data)
        .then(response => {
          this.faq.no = response.data.no;
          console.log(response.data);
          this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    newFaq() {
      this.submitted = false;
      this.faq = {};
    }
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>
