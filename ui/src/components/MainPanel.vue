<template>
  <div class="main-panel">
    <div>Query: <input type="text" v-model="query"></div>
    <div>Limit: <input type="number" v-model="limit"></div>
    <button v-on:click="load()">Process</button>
    <table v-if="this.items.length > 0">
      <tr>
        <th></th>
        <th>Title</th>
        <th>Author</th>
        <th>Date</th>
      </tr>
      <tr v-for="(item, index) in items" v-bind:key="item" :class="{ answered: item.answered }">
        <td>{{ index + 1 }}</td>
        <td>{{ item.title }}</td>
        <td>{{ item.author }}</td>
        <td>{{ item.creationDate }}</td>
        <td><a class="link" v-bind:href="item.link">To Stackoverflow</a></td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  name: 'MainPanel',
  props: {
    msg: String
  },
  data() {
    return {
      query: '',
      limit: 5,
      items: []
    }
  },
  methods: {
    load() {
      fetch('/stack-exchange/search?query=' + this.query + '&limit=' + this.limit)
        .then(response => response.json())
        .then(items => this.items = items)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.answered {
  font-weight: bold;
  color: #325f4b;
}
.link {
  font-weight: normal;
  color: #325f4b;
}
table {
  background-color: #fcfcfc;
}

h3 {
  margin: 40px 0 0;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}
</style>
