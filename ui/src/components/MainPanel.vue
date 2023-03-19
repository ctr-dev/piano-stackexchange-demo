<template>
  <div class="main-panel">
    <div class="settings">
      <div class="input-row">Query<input type="text" v-model="query"></div>
      <div class="input-row">Limit<input type="number" v-model="limit"></div>
      <button v-on:click="load()">Process</button>
    </div>
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
      limit: 20,
      items: []
    }
  },
  methods: {
    load() {
      fetch('/stack-exchange/search?query=' + this.query + '&limit=' + this.limit)
        .then(response => {
          if (response.ok) {
            return response.json()
          } else {
            alert('Error')
            throw new Error(response.statusText)
          }
        })
        .then(items => {
          if (items.length == 0) {
            alert('No results')
          } else {
            this.items = items
          }
        })
    }
  }
}
</script>

<style scoped>
.settings {
  text-align: left;
  width: 100%;
  padding: 0.3em;
  margin: 0.3em;
}

.input-row {
  font-size: 1.2em;
  padding-top: 0.3em;
  padding-bottom: 0.3em;
}

.input-row input {
  display: block;
  font-size: 1em;
}

.settings button {
  border: 1px solid rgb(18, 90, 60);
  border-radius: 0.5em;
  font-size: 1em;
  padding: 0.5em;
  background-color: #7ff1be;
  transition: 0.3s;
}

.settings button:hover {
  transition: 0.3s;
  background-color: #b5fadb;
}

.answered {
  font-weight: bold;
  color: #325f4b;
}

.link {
  font-weight: normal;
  color: #315e4a;
}

table {
  width: 100%;
  background-color: #fcfcfc;
}

table,
th,
td {
  padding: 0.3em;
  border: 1px solid #87f0c1;
  border-collapse: collapse;
}</style>
