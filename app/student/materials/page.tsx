"use client";

import { useEffect, useState } from "react";
import {
  syllabusService,
  StudentMaterial,
} from "@/services/syllabus.service";

export default function StudentMaterialsPage() {
  const [materials, setMaterials] = useState<StudentMaterial[]>([]);
  const [subject, setSubject] = useState("");
  const [loading, setLoading] = useState(true);

  const loadMaterials = async () => {
    setLoading(true);
    const res = await syllabusService.getStudentMaterials(
      subject || undefined
    );
    setMaterials(res);
    setLoading(false);
  };

  useEffect(() => {
    loadMaterials();
  }, [subject]);

  return (
    <div>
      <h1 className="text-2xl font-bold mb-6">
        Study Materials
      </h1>

      {/* Filters */}
      <div className="flex gap-4 mb-6">
        <input
          className="border p-2"
          placeholder="Filter by subject"
          value={subject}
          onChange={(e) => setSubject(e.target.value)}
        />
      </div>

      {loading ? (
        <p>Loading...</p>
      ) : materials.length === 0 ? (
        <p>No materials available</p>
      ) : (
        <div className="grid gap-4">
          {materials.map((m) => (
            <div
              key={m.id}
              className="bg-white p-4 rounded shadow flex justify-between items-center"
            >
              <div>
                <p className="font-semibold">{m.fileName}</p>
                <p className="text-sm text-gray-500">
                  {m.subject} • {m.fileSize}
                </p>
              </div>

              <a
                href={m.downloadUrl}
                target="_blank"
                className="text-blue-600 text-sm"
              >
                Download
              </a>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
