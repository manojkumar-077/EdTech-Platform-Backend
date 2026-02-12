"use client";

import { useState } from "react";
import { syllabusService } from "@/services/syllabus.service";

interface Props {
  studentClass: string;
  subject: string;
  onClose: () => void;
  onSuccess: () => void;
}

export default function UploadMaterialModal({
  studentClass,
  subject,
  onClose,
  onSuccess,
}: Props) {
  const [file, setFile] = useState<File | null>(null);
  const [loading, setLoading] = useState(false);

  const handleUpload = async () => {
    if (!file) return alert("Select a file");

    const formData = new FormData();
    formData.append("file", file);
    formData.append("studentClass", studentClass);
    formData.append("subject", subject);

    try {
      setLoading(true);
      await syllabusService.uploadMaterial(formData);
      onSuccess();
      onClose();
    } catch {
      alert("Upload failed");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center">
      <div className="bg-white p-6 rounded w-full max-w-sm">
        <h2 className="font-bold mb-4">
          Upload Material – {subject}
        </h2>

        <input
          type="file"
          onChange={(e) => setFile(e.target.files?.[0] || null)}
          className="mb-4"
        />

        <div className="flex justify-end gap-2">
          <button onClick={onClose}>Cancel</button>
          <button
            onClick={handleUpload}
            className="bg-black text-white px-4 py-2 rounded"
            disabled={loading}
          >
            {loading ? "Uploading..." : "Upload"}
          </button>
        </div>
      </div>
    </div>
  );
}
