// src/utils/request.js
import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:8080/invoice-api/v1",
  headers: {
    "Content-Type": "application/json",
  },
});

// Request Interceptor
apiClient.interceptors.request.use(
  (config) => {
    // Don't add token for login or signup requests
    if (!config.url.includes("/login") && !config.url.includes("/signup")) {
      const token = localStorage.getItem("refreshToken"); // or sessionStorage
      if (token) {
        config.headers.Authorization = `Bearer ${token}`;
      }
    }
    return config;
  },
  (error) => Promise.reject(error)
);

export const request = {
  get: (url, config = {}) => apiClient.get(url, config),
  post: (url, data, config = {}) => apiClient.post(url, data, config),
  put: (url, data, config = {}) => apiClient.put(url, data, config),
  delete: (url, config = {}) => apiClient.delete(url, config),
};

export default request;
