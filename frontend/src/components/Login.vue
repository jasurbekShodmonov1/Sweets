<script setup>
import { ref } from "vue";
import axios from "axios";

const username = ref("");
const password = ref("");
const error = ref("");

async function login() {
  try {
    const res = await api.post("/auth/v1/login", {
      username: username.value,
      password: password.value,
    });

    // Save token
    localStorage.setItem("token", res.data.token);
    localStorage.setItem("username", res.data.username);

    alert("Login successful!");
  } catch (err) {
    error.value = err.response?.data?.message || "Login failed";
  }
}
</script>

<template>
  <div>
    <h1>Login</h1>
    <input v-model="username" placeholder="Username" />
    <input v-model="password" type="password" placeholder="Password" />
    <button @click="login">Login</button>
    <p v-if="error" style="color: red">{{ error }}</p>
  </div>
</template>
