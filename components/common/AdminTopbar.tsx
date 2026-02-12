"use client";

import { useRouter } from "next/navigation";
import { clearAuthData } from "@/lib/auth";

export default function AdminTopbar() {
  const router = useRouter();

  const handleLogout = () => {
    clearAuthData();
    router.push("/login");
  };

  return (
    <header className="h-16 bg-white border-b flex items-center justify-between px-6">
      <h1 className="font-semibold text-lg">Admin Dashboard</h1>

      <button
        onClick={handleLogout}
        className="text-sm bg-black text-white px-4 py-2 rounded"
      >
        Logout
      </button>
    </header>
  );
}
