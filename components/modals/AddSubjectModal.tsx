"use client";

import { useState } from "react";
import { syllabusService } from "@/services/syllabus.service";

interface Props {
  studentClass: string;     // ✅ REQUIRED
  onClose: () => void;
  onSuccess: () => void | Promise<void>;
}

export default function AddSubjectModal({
  studentClass,
  onClose,
  onSuccess,
}: Props) {
  const [name, setName] = useState("");
  const [loading, setLoading] = useState(false);

  const handleAdd = async () => {
    try {
      setLoading(true);
      await syllabusService.addSubject(studentClass, name);
      await onSuccess();
      onClose();
    } catch {
      alert("Failed to add subject");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center">
      <div className="bg-white p-6 rounded w-full max-w-sm">
        <h2 className="font-bold mb-4">
          Add Subject (Class {studentClass})
        </h2>

        <input
          className="w-full border p-2 mb-4"
          placeholder="Subject name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />

        <div className="flex justify-end gap-2">
          <button onClick={onClose}>Cancel</button>
          <button
            onClick={handleAdd}
            className="bg-black text-white px-4 py-2 rounded"
            disabled={loading}
          >
            {loading ? "Adding..." : "Add"}
          </button>
        </div>
      </div>
    </div>
  );
}
