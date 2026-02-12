"use client";

import { useState } from "react";
import { studentService } from "@/services/student.service";

interface Props {
  onClose: () => void;
  onSuccess: () => void;
}

export default function AddStudentModal({ onClose, onSuccess }: Props) {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [studentClass, setStudentClass] = useState("");
  const [school, setSchool] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSubmit = async () => {
    try {
      setLoading(true);
      await studentService.createStudent({
        name,
        email,
        studentClass,
        school,
      });
      onSuccess();
      onClose();
    } catch (err) {
      alert("Failed to create student");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center">
      <div className="bg-white p-6 rounded w-full max-w-md">
        <h2 className="text-lg font-bold mb-4">Add Student</h2>

        <input
          className="w-full border p-2 mb-3"
          placeholder="Student Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />

        <input
          className="w-full border p-2 mb-3"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />

        <input
          className="w-full border p-2 mb-3"
          placeholder="Class (e.g. 10)"
          value={studentClass}
          onChange={(e) => setStudentClass(e.target.value)}
        />

        <input
          className="w-full border p-2 mb-4"
          placeholder="School Name"
          value={school}
          onChange={(e) => setSchool(e.target.value)}
        />

        <div className="flex justify-end gap-2">
          <button onClick={onClose}>Cancel</button>
          <button
            onClick={handleSubmit}
            className="bg-black text-white px-4 py-2 rounded"
            disabled={loading}
          >
            {loading ? "Creating..." : "Create"}
          </button>
        </div>
      </div>
    </div>
  );
}
