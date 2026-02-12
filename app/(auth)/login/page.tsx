"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { authService } from "@/services/auth.service";
import { saveAuthData, isLoggedIn, getRole } from "@/lib/auth";

export default function LoginPage() {
  const router = useRouter();

  // 🔐 Redirect if already logged in
  useEffect(() => {
    if (isLoggedIn()) {
      const role = getRole();
      router.replace(
        role === "ADMIN" ? "/admin/dashboard" : "/student/quizzes"
      );
    }
  }, [router]);

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState<"ADMIN" | "STUDENT">("ADMIN");
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

      router.push(
        res.role === "ADMIN"
          ? "/admin/dashboard"
          : "/student/quizzes"
      );
    } catch (err: any) {
      setError(err.response?.data?.message || "Login failed");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <div className="w-full max-w-5xl bg-white rounded-2xl shadow-lg overflow-hidden grid grid-cols-1 md:grid-cols-2">

        {/* 🔵 LEFT PANEL */}
        <div className="relative bg-blue-600 text-white p-10 hidden md:flex flex-col justify-between">
          <div>
            <div className="flex items-center gap-2 mb-6">
              <div className="w-8 h-8 bg-white text-blue-600 rounded-full flex items-center justify-center font-bold">
                🎓
              </div>
              <span className="font-semibold">CBSE AI EdTech</span>
            </div>

            <h1 className="text-3xl font-bold mb-4">
              Manage the Future of Education.
            </h1>

            <p className="text-blue-100">
              Powerful tools for content managers to organize syllabus
              and create engaging learning experiences.
            </p>
          </div>

          <div className="flex gap-3 text-sm text-blue-100">
            <span className="bg-blue-500/40 px-3 py-1 rounded-full">
              Secure SSL
            </span>
            <span className="bg-blue-500/40 px-3 py-1 rounded-full">
              AI Protected
            </span>
          </div>
        </div>

        {/* ⚪ RIGHT PANEL */}
        <div className="p-10">
          <h2 className="text-2xl font-bold mb-2">
            Welcome Back!
          </h2>
          <p className="text-gray-500 mb-6">
            Sign in to your {role.toLowerCase()} account to continue.
          </p>

          {/* Role Toggle */}
          <div className="flex bg-gray-100 rounded-full mb-6 p-1">
            <button
              type="button"
              onClick={() => setRole("STUDENT")}
              className={`flex-1 py-2 rounded-full text-sm font-medium ${
                role === "STUDENT"
                  ? "bg-white shadow text-blue-600"
                  : "text-gray-500"
              }`}
            >
              Student
            </button>
            <button
              type="button"
              onClick={() => setRole("ADMIN")}
              className={`flex-1 py-2 rounded-full text-sm font-medium ${
                role === "ADMIN"
                  ? "bg-white shadow text-blue-600"
                  : "text-gray-500"
              }`}
            >
              Admin
            </button>
          </div>

          <form onSubmit={handleLogin}>
            {/* Email */}
            <div className="mb-4">
              <label className="block text-sm font-medium mb-1">
                Email Address
              </label>
              <input
                type="email"
                placeholder="name@email.com"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="w-full border rounded-lg px-4 py-2 focus:ring-2 focus:ring-blue-500 outline-none"
                required
              />
            </div>

            {/* Password */}
            <div className="mb-2">
              <div className="flex justify-between text-sm mb-1">
                <label className="font-medium">Password</label>
                <a
                  href="/forgot-password"
                  className="text-blue-600 hover:underline"
                >
                  Forgot?
                </a>
              </div>
              <input
                type="password"
                placeholder="••••••••"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className="w-full border rounded-lg px-4 py-2 focus:ring-2 focus:ring-blue-500 outline-none"
                required
              />
            </div>

            {/* Error */}
            {error && (
              <div className="bg-red-100 text-red-700 text-sm p-2 rounded mb-4">
                {error}
              </div>
            )}

            {/* Login Button */}
            <button
              type="submit"
              disabled={loading}
              className="w-full mt-4 bg-blue-600 text-white py-3 rounded-lg font-semibold hover:bg-blue-700 transition disabled:opacity-60"
            >
              {loading ? "Logging in..." : "Login"}
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}
