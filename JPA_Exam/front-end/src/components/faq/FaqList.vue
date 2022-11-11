<template>
<!-- 3) -->
<!-- 검색어 -->
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by title"
          v-model="title"/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button"
            @click="searchTitle"
          >
            Search
          </button>
        </div>
      </div>
    </div>

<!-- 전체 목록 -->
    <div class="col-md-6">
      <h4>Faq List</h4>
      <ul class="list-group">
        <li class="list-group-item"
          :class="{ active: index == currentIndex }"
          v-for="(data, index) in faq"
          :key="index"
          @click="setActiveFaq(data, index)"
        >
          {{ data.title }}
        </li>
      </ul>

      <button class="m-3 btn btn-sm btn-danger" @click="removeAllFaq">
        Remove All
      </button>
    </div>

    <!-- 상세 목록 -->
    <div class="col-md-6">
      <div v-if="currentFaq">
        <h4>Faq</h4>
        <div>
          <label><strong>Title:</strong></label> {{ currentFaq.title }}
        </div>
        <div>
          <label><strong>Content:</strong></label> {{ currentFaq.content }}
        </div>

        <router-link :to="'/faq/' + currentFaq.no"><span class="badge bg-success">Edit</span></router-link>
      </div>
      <div v-else>
        <br />
        <p>Please click on a Faq...</p>
      </div>
    </div>
    
  </div>
</template>

<script>
import FaqDataService from "../../services/FaqDataService";

export default {
  data() {
    return {
      faq: [],
      currentFaq: null,
      currentIndex: -1,
      title: ""
    };
  },
  methods: {
    retrieveFaq() {
      FaqDataService.getAll()
        .then(response => {
          this.faq = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    refreshList() {
      this.retrieveFaq();
      this.currentFaq = null;
      this.currentIndex = -1;
    },

    setActiveFaq(faq, index) {
      this.currentFaq = faq;
      this.currentIndex = index;
    },

    removeAllFaq() {
      FaqDataService.deleteAll()
        .then(response => {
          console.log(response.data);
          this.refreshList();
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    searchTitle() {
      FaqDataService.findByTitle(this.title)
        .then(response => {
          this.faq = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.retrieveFaq();
  }
};
</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>
