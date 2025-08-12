import React from "react";
import { FiSearch } from "react-icons/fi";

const Navbar = () => {
  return (
    <nav className="bg-blue-600 px-4 py-2 flex justify-between items-center shadow-md">
      {/* Left side: App Name */}
      <h1 className="ml-8 text-white font-light text-lg">
        Invoice Scanner & Tracker
      </h1>

      {/* Right side: Search Box */}
      <div className="relative mr-9">
        <FiSearch className="absolute left-3 top-1/2 -translate-y-1/2 text-gray-400" />
        <input
          type="text"
          placeholder="Search Saved Invoices"
          className="pl-10 pr-4 py-2 rounded-full text-sm text-center border-none outline-none w-80"
        />
      </div>
    </nav>
  );
};

export default Navbar;
