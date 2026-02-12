"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";
import { authService } from "@/services/auth.service";
import { saveAuthData } from "@/lib/auth";

export default function LoginPage() {
  const router = useRouter();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState<"ADMIN" | "STUDENT">("STUDENT");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    setError("");
    setLoading(true);

    try {
      const res = await authService.login({
        email,
        password,
        role,
      });

      saveAuthData(res.token, res.role, res.userId);

      // Redirect based on role
      if (res.role === "ADMIN") {
        router.push("/admin/dashboard");
      } else {
        router.push("/student/quizzes");
      }
    } catch (err: any) {
      setError(err.response?.data?.message || "Login failed");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <form
        onSubmit={handleLogin}
        className="bg-white p-8 rounded-lg shadow-md w-full max-w-md"
      >
        <h1 className="text-2xl font-bold mb-6 text-center">
          Login
        </h1>

        {/* Role Toggle */}
        <div className="flex mb-4">
          <button
            type="button"
            className={`flex-1 py-2 rounded-l ${
              role === "STUDENT"
                ? "bg-black text-white"
                : "bg-gray-200"
            }`}
            onClick={() => setRole("STUDENT")}
          >
            Student
          </button>
          <button
            type="button"
            className={`flex-1 py-2 rounded-r ${
              role === "ADMIN"
                ? "bg-black text-white"
                : "bg-gray-200"
            }`}
            onClick={() => setRole("ADMIN")}
          >
            Admin
          </button>
        </div>

        {/* Email */}
        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
          className="w-full p-2 border rounded mb-4"
        />

        {/* Password */}
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
          className="w-full p-2 border rounded mb-4"
        />

        {/* Error */}
        {error && (
          <p className="text-red-500 text-sm mb-3">
            {error}
          </p>
        )}

        {/* Submit */}
        <button
          type="submit"
          disabled={loading}
          className="w-full bg-black text-white py-2 rounded"
        >
          {loading ? "Logging in..." : "Login"}
        </button>

        {/* Forgot password */}
        <p
          className="text-sm text-center mt-4 text-blue-600 cursor-pointer"
          onClick={() => router.push("/forgot-password")}
        >
          Forgot password?
        </p>
      </form>
    </div>
  );
}
