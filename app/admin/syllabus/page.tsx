"use client";

import { useEffect, useState } from "react";
import {
  syllabusService,
  Subject,
  StudyMaterial,
} from "@/services/syllabus.service";
import AddSubjectModal from "@/components/modals/AddSubjectModal";
import UploadMaterialModal from "@/components/modals/UploadMaterialModal";

export default function SyllabusPage() {
  const [studentClass, setStudentClass] = useState("10");
  const [subjects, setSubjects] = useState<Subject[]>([]);
  const [materials, setMaterials] = useState<StudyMaterial[]>([]);
  const [selectedSubject, setSelectedSubject] = useState("");
  const [showAddSubject, setShowAddSubject] = useState(false);
  const [showUpload, setShowUpload] = useState(false);

  const loadSubjects = async () => {
    const res = await syllabusService.getSubjects(studentClass);
    setSubjects(res);
  };

  const loadMaterials = async (subject: string) => {
    const res = await syllabusService.getMaterials(studentClass, subject);
    setMaterials(res);
  };

  useEffect(() => {
    loadSubjects();
  }, [studentClass]);

  return (
    <div>
      <div className="flex justify-between mb-6">
        <h1 className="text-2xl font-bold">Syllabus Management</h1>

        <select
          value={studentClass}
          onChange={(e) => setStudentClass(e.target.value)}
          className="border p-2"
        >
          <option value="8">Class 8</option>
          <option value="9">Class 9</option>
          <option value="10">Class 10</option>
          <option value="11">Class 11</option>
          <option value="12">Class 12</option>
        </select>
      </div>

      <div className="flex gap-6">
        {/* Subjects */}
        <div className="w-1/3 bg-white p-4 rounded shadow">
          <div className="flex justify-between mb-3">
            <h3 className="font-bold">Subjects</h3>
            <button
              className="text-sm text-blue-600"
              onClick={() => setShowAddSubject(true)}
            >
              + Add
            </button>
          </div>

          {subjects.map((sub) => (
            <div
              key={sub.id}
              className="p-2 border-b cursor-pointer"
              onClick={() => {
                setSelectedSubject(sub.name);
                loadMaterials(sub.name);
              }}
            >
              {sub.name}
            </div>
          ))}
        </div>

        {/* Materials */}
        <div className="flex-1 bg-white p-4 rounded shadow">
          <div className="flex justify-between mb-3">
            <h3 className="font-bold">
              Materials {selectedSubject && `– ${selectedSubject}`}
            </h3>

            {selectedSubject && (
              <button
                className="text-sm text-blue-600"
                onClick={() => setShowUpload(true)}
              >
                + Upload
              </button>
            )}
          </div>

          {materials.map((m) => (
            <div
              key={m.id}
              className="border-b p-2 flex justify-between"
            >
              <span>{m.fileName}</span>
              <span className="text-sm text-gray-500">
                {m.fileSize}
              </span>
            </div>
          ))}
        </div>
      </div>

      {showAddSubject && (
        <AddSubjectModal
          studentClass={studentClass}
          onClose={() => setShowAddSubject(false)}
          onSuccess={loadSubjects}
        />
      )}

      {showUpload && selectedSubject && (
        <UploadMaterialModal
          studentClass={studentClass}
          subject={selectedSubject}
          onClose={() => setShowUpload(false)}
          onSuccess={() => loadMaterials(selectedSubject)}
        />
      )}
    </div>
  );
}
