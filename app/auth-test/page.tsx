"use client";

import { authService } from "@/services/auth.service";
import { saveAuthData } from "@/lib/auth";

export default function TestAuthPage() {
  const testLogin = async () => {
    try {
      const res = await authService.login({
        email: "admin@test.com",
        password: "password",
        role: "ADMIN", // use valid role from backend
      });

      console.log("LOGIN SUCCESS:", res);

      saveAuthData(res.token, res.role, res.userId);

      alert("Login successful, check console & localStorage");
    } catch (err: any) {
      console.error("LOGIN ERROR:", err.response?.data || err);
      alert("Login failed, check console");
    }
  };

  return (
    <div style={{ padding: 40 }}>
      <h1>Auth Test Page</h1>
      <button
        onClick={testLogin}
        style={{
          padding: "10px 20px",
          background: "black",
          color: "white",
          borderRadius: 6,
        }}
      >
        Test Login
      </button>
    </div>
  );
}
