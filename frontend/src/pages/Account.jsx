import { useNavigate } from "react-router-dom";

export default function Account() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("accessToken");
    localStorage.removeItem("refreshToken"); // delete token
    navigate("/login"); // redirect to login
  };

  return (
    <div className="p-6">
      <h1 className="text-2xl font-semibold mb-4">Account</h1>

      <button
        onClick={handleLogout}
        className="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600"
      >
        Logout
      </button>
    </div>
  );
}
