import React from "react";
import { Routes, Route } from "react-router-dom";
import Layout from "./components/Layout";
import Home from "./pages/Home";
import Upload from "./pages/Upload";
import PaidInvoice from "./pages/PaidInvoice";
import Account from "./pages/Account";
import Login from "./components/Login";
import Signup from "./components/SignUp";
import ProtectedRoute from "./auth/ProductedRoute";

export default function App() {
  return (
    <Routes>
      {/* Public Routes */}
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<Signup />} />

      {/* Protected Routes */}
      <Route
        path="/"
        element={
          <ProtectedRoute>
            <Layout />
          </ProtectedRoute>
        }
      >
        <Route index element={<Home />} />
        <Route path="upload" element={<Upload />} />
        <Route path="paid-invoices" element={<PaidInvoice/>} />
        <Route path="account" element={<Account />} />
      </Route>
    </Routes>
  );
}
