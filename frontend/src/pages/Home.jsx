import React from "react";
import { FiRefreshCw } from "react-icons/fi";

const Home = () => {
  const invoices = [
    {
      id: 1,
      invoiceId: "A0011",
      vendor: "Venkat Cements",
      amount: "10000 Rs.",
      dueDate: "12/11/2025",
      daysLeft: "Overdue",
      daysColor: "text-red-500",
    },
    {
      id: 2,
      invoiceId: "A0012",
      vendor: "Bharat Steels",
      amount: "20000 Rs.",
      dueDate: "12/11/2025",
      daysLeft: "1 Day",
      daysColor: "text-blue-600",
    },
    {
      id: 3,
      invoiceId: "A0013",
      vendor: "Master Sand",
      amount: "2000 Rs.",
      dueDate: "12/11/2025",
      daysLeft: "5 Days",
      daysColor: "text-blue-600",
    },
    {
      id: 4,
      invoiceId: "A0014",
      vendor: "KR Tiles",
      amount: "6000 Rs.",
      dueDate: "12/11/2025",
      daysLeft: "4 Days",
      daysColor: "text-blue-600",
    },
  ];

  return (
    <div className="p-6">
      {/* Header */}
      <div className="flex items-center justify-between mb-4">
        <h1 className="text-lg font-medium">My Invoices</h1>
        <button className="bg-blue-500 hover:bg-blue-600 text-white p-2 rounded-full shadow-md transition">
          <FiRefreshCw size={20} />
        </button>
      </div>

      {/* Heading Row */}
      <div className="rounded-lg shadow-sm flex items-center p-4 text-gray-600 font-bold text-sm">
        <div className="w-20">S.No</div>
        <div className="w-32">Invoice ID</div>
        <div className="w-56">Vendor Name</div>
        <div className="w-48">Total Amount</div>
        <div className="w-36">Due Date</div>
        <div className="w-40">Days Left</div>
        <div className="w-10">Actions</div>
      </div>

      {/* Card-style rows */}
      <div className="space-y-4">
        {invoices.map((invoice, index) => (
          <div
            key={invoice.id}
            className="bg-gray-100 rounded-lg shadow-md flex items-center justify-between text-sm p-4 py-2"
          >
            {/* S.No */}
            <div className="w-10 text-gray-700">{index + 1}</div>

            {/* Invoice ID */}
            <div className="w-20">{invoice.invoiceId}</div>

            {/* Vendor */}
            <div className="w-48 text-gray-700">{invoice.vendor}</div>

            {/* Amount */}
            <div className="w-28">{invoice.amount}</div>

            {/* Due Date */}
            <div className="w-28">{invoice.dueDate}</div>

            {/* Days Left */}
            <div className={`w-20 font-semibold ${invoice.daysColor}`}>
              {invoice.daysLeft}
            </div>

            {/* Actions */}
            <div className="w-32 flex gap-2">
              <button className="bg-red-500 text-white px-3 py-1 rounded-md hover:bg-red-700">
                Remove
              </button>
              <button className="bg-green-400 text-white px-3 py-1 rounded-md hover:bg-green-500">
                Paid
              </button>
            </div>
          </div>
        ))}
      </div>


      {/* Floating Add Button */}
      <button className="fixed bottom-6 right-6 bg-blue-500 hover:bg-blue-600 text-white rounded-full w-12 h-12 flex items-center justify-center text-2xl shadow-lg transition">
        +
      </button>
    </div>
  );
};

export default Home;
