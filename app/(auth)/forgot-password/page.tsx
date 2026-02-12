"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";

export default function ForgotPasswordPage() {
  const router = useRouter();
  const [email, setEmail] = useState("");

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    console.log("Email:", email);
  };

  return (
    <div className="forgot-container">
      <div className="forgot-card">
        {/* Back */}
        <button className="back-btn" onClick={() => router.push("/")}>
          ← Back to Login
        </button>

        {/* Icon */}
        <div className="icon-wrapper">
          🔑
        </div>

        {/* Text */}
        <h1>Forgot Password?</h1>
        <p className="desc">
          Enter your registered email address and we'll send you an OTP to reset
          your password.
        </p>

        {/* Form */}
        <form onSubmit={handleSubmit}>
          <label>Email Address</label>

          <div className="input-box">
            <span className="input-icon">✉️</span>
            <input
              type="email"
              placeholder="name@email.com"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>

          <button type="submit" className="send-btn">
            ✉️ Send OTP
          </button>
        </form>
      </div>
    </div>
  );
}
