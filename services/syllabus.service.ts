import api from "./api";

export interface Subject {
  id: number;
  name: string;
  studentClass: string;
}

export interface StudyMaterial {
  id: number;
  subject: string;
  fileName: string;
  fileSize: string;
  uploadedAt: string;
}

// Get subjects by class
const getSubjects = async (studentClass: string): Promise<Subject[]> => {
  const res = await api.get(`/admin/syllabus/${studentClass}`);
  return res.data;
};

// Add subject
const addSubject = async (studentClass: string, name: string): Promise<void> => {
  await api.post("/admin/syllabus/subject", { studentClass, name });
};

// Upload material
const uploadMaterial = async (formData: FormData): Promise<void> => {
  await api.post("/admin/syllabus/material", formData, {
    headers: { "Content-Type": "multipart/form-data" },
  });
};

// Get materials
const getMaterials = async (
  studentClass: string,
  subject: string
): Promise<StudyMaterial[]> => {
  const res = await api.get(
    `/admin/syllabus/materials?class=${studentClass}&subject=${subject}`
  );
  return res.data;
};

// ================= STUDENT SIDE =================

export interface StudentMaterial {
  id: number;
  subject: string;
  fileName: string;
  fileSize: string;
  downloadUrl: string;
  uploadedAt: string;
}

// Get materials for student (class inferred from backend)
const getStudentMaterials = async (
  subject?: string
): Promise<StudentMaterial[]> => {
  const res = await api.get("/student/materials", {
    params: { subject },
  });
  return res.data;
};

export const syllabusService = {
  // admin
  getSubjects,
  addSubject,
  uploadMaterial,
  getMaterials,

  // student
  getStudentMaterials,
};
