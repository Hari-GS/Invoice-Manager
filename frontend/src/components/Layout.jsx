import React from "react";
import Navbar from "./Navbar";
import Dashboard from "./Dashboard";
import { Outlet } from "react-router-dom";

const Layout = () => {
  return (
    <div className="flex flex-col h-screen">
      {/* Top Navbar */}
      <Navbar />

      {/* Sidebar + Page Content */}
      <div className="flex flex-1">
        <Dashboard />
        <div className="flex-1 p-6 bg-white overflow-auto">
          <Outlet /> {/* Page content goes here */}
        </div>
      </div>
    </div>
  );
};

export default Layout;
