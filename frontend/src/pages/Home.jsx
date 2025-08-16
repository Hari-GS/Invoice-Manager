import React, { useEffect, useState } from "react";
import { FiRefreshCw, FiPlus } from "react-icons/fi";
import { useNavigate } from "react-router-dom";  // ✅ import navigation
import request from "../utils/request";

const Home = () => {
  const [invoices, setInvoices] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate(); // ✅ hook for navigation

  const fetchInvoices = async () => {
    try {
      setLoading(true);
      const res = await request.get("/invoices/user");
      setInvoices(res.data);
    } catch (error) {
      console.error("Error fetching invoices: ", error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchInvoices();
  }, []);

  return (
    <div className="p-6">
      {/* Header */}
      <div className="flex items-center justify-between mb-4">
        <h1 className="text-lg font-medium">My Invoices</h1>
        <button
          className="bg-blue-500 hover:bg-blue-600 text-white p-1.5 rounded-full shadow-md transition"
          onClick={fetchInvoices}
        >
          <FiRefreshCw size={14} />
        </button>
      </div>

      {/* Content */}
      {loading ? (
        <p className="text-gray-500 text-center mt-10">Loading invoices...</p>
      ) : invoices.length === 0 ? (
        <p className="text-gray-500 text-center mt-10">
          No invoices found. Click the + button to upload a new invoice.
        </p>
      ) : (
        <>

        {/* Heading Row */}
        <div className="rounded-lg shadow-sm flex items-center p-4 text-gray-600 font-bold text-sm">
          <div className="w-16 ml-6">S.No</div>
          <div className="w-36">Invoice ID</div>
          <div className="w-64">Vendor Name</div>
          <div className="w-40">Total Amount</div>
          <div className="w-36">Due Date</div>
          <div className="w-36">Days Left</div>
          <div className="w-40 text-center">Actions</div>
        </div>

      {/* Card-style rows */}
      <div className="space-y-4">
        {invoices.map((invoice, index) => (
          <div
            key={invoice.id}
            className="bg-gray-100 rounded-lg shadow-md flex items-center text-sm p-4 py-2"
          >
            <div className="w-16 text-gray-700 ml-6">{index + 1}</div>
            <div className="w-36">{invoice.invoiceId}</div>
            <div className="w-64 text-gray-700 truncate">{invoice.vendorName}</div>
            <div className="w-40">{invoice.totalAmount}</div>
            <div className="w-36">{invoice.dueDate}</div>
            <div className={`w-36 font-semibold ${invoice.daysColor}`}>
              {invoice.daysLeft}
            </div>
            <div className="w-40 flex gap-6">
              <button className="bg-red-500 text-white px-4 py-2 w-30 rounded-md hover:bg-red-700">
                Remove
              </button>
              <button className="bg-green-400 text-white px-4 py-2 w-30 rounded-md hover:bg-green-500">
                Paid
              </button>
            </div>
          </div>
        ))}
      </div>
      </>
      )}

      {/* Floating Add Button */}
      <button
        onClick={() => navigate("/upload")} // ✅ navigate to upload page
        className="fixed bottom-6 right-6 bg-blue-500 hover:bg-blue-600 text-white rounded-full w-12 h-12 flex items-center justify-center shadow-lg transition"
      >
        <FiPlus size={28} />
      </button>
    </div>
  );
};

export default Home;
