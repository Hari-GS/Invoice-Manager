// src/utils/request.js
import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:8080/invoice-api/v1",
  headers: {
    "Content-Type": "application/json",
  },
});

export const request = {
  get: (url, config = {}) => apiClient.get(url, config),
  post: (url, data, config = {}) => apiClient.post(url, data, config),
  put: (url, data, config = {}) => apiClient.put(url, data, config),
  delete: (url, config = {}) => apiClient.delete(url, config),
};

export default request;
