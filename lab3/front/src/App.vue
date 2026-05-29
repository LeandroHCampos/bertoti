<script setup>
import { ref, onMounted } from 'vue'

const coffees = ref([])
const newName = ref('')
const editingId = ref(null)
const editingName = ref('')
const API = 'http://localhost:8080/coffees'

async function load() {
  const r = await fetch(API)
  coffees.value = await r.json()
}

async function addCoffee() {
  if (!newName.value.trim()) return
  await fetch(API, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ name: newName.value })
  })
  newName.value = ''
  load()
}

async function saveEdit(id) {
  await fetch(`${API}/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ name: editingName.value })
  })
  editingId.value = null
  load()
}

async function deleteCoffee(id) {
  await fetch(`${API}/${id}`, { method: 'DELETE' })
  load()
}

onMounted(load)
</script>

<template>
  <div class="container">
    <h1>☕ Cafés</h1>

    <div class="form">
      <input v-model="newName" placeholder="Nome do café..." @keyup.enter="addCoffee" />
      <button @click="addCoffee">Adicionar</button>
    </div>

    <div class="list">
      <p v-if="coffees.length === 0" class="empty">Nenhum café cadastrado.</p>

      <div v-for="c in coffees" :key="c.id" class="item">
        <template v-if="editingId === c.id">
          <input v-model="editingName" class="edit-input" @keyup.enter="saveEdit(c.id)" />
          <button @click="saveEdit(c.id)">✓</button>
          <button @click="editingId = null">✕</button>
        </template>
        <template v-else>
          <span class="name">{{ c.name }}</span>
          <button @click="editingId = c.id; editingName = c.name">Editar</button>
          <button class="danger" @click="deleteCoffee(c.id)">Apagar</button>
        </template>
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  max-width: 520px;
  margin: 4rem auto;
  padding: 2rem;
  border: 1px solid #e0e0e0;
  border-radius: 12px;
  font-family: sans-serif;
}
h1 { margin-bottom: 1.5rem; font-size: 1.4rem; }
.form { display: flex; gap: 8px; margin-bottom: 1.5rem; }
.form input { flex: 1; padding: 8px 12px; border: 1px solid #ccc; border-radius: 8px; font-size: 14px; }
.list { display: flex; flex-direction: column; gap: 8px; }
.item { display: flex; align-items: center; gap: 8px; padding: 10px 12px; border: 1px solid #eee; border-radius: 8px; }
.name { flex: 1; font-size: 15px; }
.edit-input { flex: 1; padding: 6px 10px; border: 1px solid #ccc; border-radius: 6px; font-size: 14px; }
.empty { color: #aaa; text-align: center; padding: 1rem 0; }
button { padding: 6px 12px; border: 1px solid #ff4d00; border-radius: 6px; cursor: pointer; background: rgb(255, 102, 0); font-size: 13px; }
button:hover { background: #720000; }
button.danger { color: #ff1900; border-color: #e0b0b0; }
button.danger:hover { background: #fff0f0; }
</style>