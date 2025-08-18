import React, { useState } from "react";
import { FiUploadCloud, FiArrowRight } from "react-icons/fi";
import request from "../utils/request"; // ✅ use your axios helper
import ExtractedInvoiceForm from "../components/ExtractedInvoiceForm";

const InvoiceUpload = () => {
  const [file, setFile] = useState(null);
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
    setResult(null); // clear previous result if new file is selected
  };

  const handleExtract = async () => {
    if (!file) {
      alert("Please upload an invoice first!");
      return;
    }

    const formData = new FormData();
    formData.append("file", file);

    try {
      setLoading(true);
      setResult(null);

      // ✅ call your backend endpoint
      const res = await request.post("/invoices/upload", formData, {
        headers: { "Content-Type": "multipart/form-data" },
      });

      setResult(res.data); // backend should return extracted JSON
    } catch (error) {
      console.error("Error extracting invoice:", error);
      setResult({ error: "Failed to extract invoice details!" });
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="bg-white flex items-center justify-center">
      <div className="w-full px-6">
        {/* Heading */}
        <h2 className="text-2xl font-semibold text-gray-800">Upload Invoice</h2>
        <p className="text-gray-500 text-m mt-2 mb-8">
          Upload invoice photo from your device to extract and save in your
          account digitally.
        </p>

        {/* Upload Section */}
        <div className="flex flex-col md:flex-row items-center gap-16">
          {/* Upload Box */}
          <div className="flex-1 mt-12">
            <label
              htmlFor="file-upload"
              className="w-full flex flex-col items-center justify-center h-64 border-2 border-dashed border-blue-400 rounded-lg cursor-pointer hover:bg-blue-50 transition"
            >
              <div className="flex flex-col items-center justify-center space-y-2">
                <FiUploadCloud className="h-12 w-12 text-gray-400" />
                <p className="text-gray-500 text-sm">
                  Drag & drop your invoice here
                </p>
                <p className="text-blue-600 underline text-sm">
                  or click to select file
                </p>
                {file && (
                  <p className="text-sm text-gray-600 mt-2">{file.name}</p>
                )}
              </div>
              <input
                id="file-upload"
                type="file"
                className="hidden"
                accept="image/*"
                onChange={handleFileChange}
              />
            </label>
          </div>

          {/* Extract Button */}
          <div className="flex-shrink-0 mt-8">
            <button
              onClick={handleExtract}
              disabled={loading}
              className="bg-blue-600 hover:bg-blue-700 disabled:bg-gray-400 text-white px-3 py-1 rounded-lg font-medium shadow-md flex items-center gap-2 transition"
            >
              {loading ? "Extracting..." : "Extract"}
              {!loading && <FiArrowRight className="h-5 w-5" />}
            </button>
          </div>

         {/* Extracted Details Section */}
          {loading ? (
            <div className="flex-1 h-64 flex items-center justify-center border rounded-lg bg-gray-50 text-sm text-gray-700 mt-12 p-4 overflow-auto">
              <p className="text-gray-400">Processing invoice...</p>
            </div>
          ) : result ? (
            <ExtractedInvoiceForm
              extractedData={result}
              onCancel={() => setResult(null)}
            />
          ) : (
            <div className="flex-1 h-64 flex items-center justify-center border rounded-lg bg-gray-50 text-sm text-gray-700 mt-12 p-4 overflow-auto">
              <p className="text-gray-400">Extracted details will appear here</p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default InvoiceUpload;
