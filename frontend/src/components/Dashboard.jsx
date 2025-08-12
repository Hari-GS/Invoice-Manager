import React from "react";
import { Link, useLocation } from "react-router-dom";
import { FaHome, FaUpload, FaFileInvoiceDollar, FaUser } from "react-icons/fa";

const Dashboard = () => {
  const location = useLocation();

  const menuItems = [
    { name: "Home", icon: <FaHome size={16} />, path: "/" },
    { name: "Upload", icon: <FaUpload size={16} />, path: "/upload" },
    { name: "Paid Invoices", icon: <FaFileInvoiceDollar size={16} />, path: "/paid-invoices" },
    { name: "Account", icon: <FaUser size={16} />, path: "/account" },
  ];

  return (
    <div className="bg-gray-100 h-screen w-56 p-4">
      {/* Dashboard Title */}
      <h2 className="text-gray-500 font-medium mb-6 text-center">Dashboard</h2>

      {/* Menu List */}
      <ul className="space-y-2">
        {menuItems.map((item) => (
          <li key={item.name}>
            <Link
              to={item.path}
              className={`flex items-center gap-2 px-4 py-2 rounded-lg transition-all
                ${
                  location.pathname === item.path
                    ? "bg-blue-600 text-white shadow-md"
                    : "text-gray-600 hover:bg-gray-200"
                }`}
            >
              {item.icon}
              <span className="font-medium">{item.name}</span>
            </Link>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Dashboard;
