import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Layout from "./components/Layout";

const Home = () => <h1>Home Page</h1>;
const Upload = () => <h1>Upload Page</h1>;
const PaidInvoices = () => <h1>Paid Invoices Page</h1>;
const Account = () => <h1>Account Page</h1>;

const App = () => {
  return (
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="upload" element={<Upload />} />
          <Route path="paid-invoices" element={<PaidInvoices />} />
          <Route path="account" element={<Account />} />
        </Route>
      </Routes>
  );
};

export default App;
