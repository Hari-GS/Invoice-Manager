import axios from "axios";
import request from "../utils/request";
import React, { useState } from "react";

const ExtractedInvoiceForm = ({ extractedData, onCancel }) => {
  // Pre-fill form with extracted data
  const [form, setForm] = useState({
    invoiceId: extractedData?.invoiceId || "",
    vendorName: extractedData?.vendorName || "",
    totalAmount: extractedData?.totalAmount || "",
    dueDate: extractedData?.dueDate || "",
    daysLeft: extractedData?.daysLeft || "",
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSave = async () => {
     // Send data back to backend
     try{
        const response = await request.post("/invoices/save", form);
        console.log("save successfully", response.data);
        alert("Invoice saved successfully")
     }catch(err){
        console.error("Error saving invoice",err);
        alert("Failed to save invoice");
     }
  };

  return (
    <div className="bg-gray-50 p-6 rounded-lg shadow-md w-96">
      <h2 className="text-center text-gray-700 font-medium mb-4">
        Extracted Invoice
      </h2>

      {/* Form Rows */}
      <div className="space-y-3 text-sm">
        <div className="flex justify-between items-center">
          <label className="text-gray-600 w-32">Invoice ID :</label>
          <input
            type="text"
            name="invoiceId"
            value={form.invoiceId}
            onChange={handleChange}
            className="border rounded px-2 py-1 flex-1"
          />
        </div>

        <div className="flex justify-between items-center">
          <label className="text-gray-600 w-32">Vendor Name :</label>
          <input
            type="text"
            name="vendorName"
            value={form.vendorName}
            onChange={handleChange}
            className="border rounded px-2 py-1 flex-1"
          />
        </div>

        <div className="flex justify-between items-center">
          <label className="text-gray-600 w-32">Total Amount :</label>
          <input
            type="text"
            name="totalAmount"
            value={form.totalAmount}
            onChange={handleChange}
            className="border rounded px-2 py-1 flex-1"
          />
        </div>

        <div className="flex justify-between items-center">
          <label className="text-gray-600 w-32">Due Date :</label>
          <input
            type="text"
            name="dueDate"
            value={form.dueDate}
            onChange={handleChange}
            className="border rounded px-2 py-1 flex-1"
          />
        </div>

        <div className="flex justify-between items-center">
          <label className="text-gray-600 w-32">Days Left :</label>
          <input
            type="text"
            name="daysLeft"
            value={form.daysLeft}
            onChange={handleChange}
            className="border rounded px-2 py-1 flex-1"
          />
        </div>
      </div>

      {/* Action Buttons */}
      <div className="flex justify-center gap-4 mt-6">
        <button
          onClick={handleSave}
          className="bg-blue-600 hover:bg-blue-700 text-white px-6 py-2 rounded shadow"
        >
          Save
        </button>
        <button
          onClick={onCancel}
          className="bg-gray-200 hover:bg-gray-300 text-gray-700 px-6 py-2 rounded shadow"
        >
          Cancel
        </button>
      </div>
    </div>
  );
};

export default ExtractedInvoiceForm;
